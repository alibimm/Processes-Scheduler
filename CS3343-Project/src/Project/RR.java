package Project;

import java.util.ArrayList;
import java.util.HashMap;

public class RR implements Algorithm {
	private ArrayList<Process> readyQueue = new ArrayList<>();
	private ArrayList<Process> blockQueueK = new ArrayList<>();
	private ArrayList<Process> processesDone = new ArrayList<>();
	private int completeNum = 0;
	private int dispatchedTick = 0;
	private int curProcessId = -1;
	private int prevProcessId = -1;
	private int maxLoop = 1000;
	private int CLOCK = 5;
	
	private static RR instance = new RR();
	
	private RR() {}
	
	public static RR getInstance() {
		return instance;
	}

	@Override
	public Result schedule(ArrayList<Process> processes) {
		// storage of loggers for each process
		HashMap<Integer, ProcessInCPU> loggerMap = new HashMap<Integer, ProcessInCPU>();
		
		// create new logger for each session
		for (int i=0; i<processes.size(); i++) {
			ProcessInCPU logger = new ProcessInCPU(processes.get(i));
			loggerMap.put(processes.get(i).getId(), logger);
		}
		
		
		// main loop
		for (int curTick=0; curTick < maxLoop; curTick++) {
			// long term scheduler 
			for (int i=0; i<processes.size(); i++) {
				if (processes.get(i).getArrivalTime() == curTick) {
					readyQueue.add(processes.get(i));
				}
				
			}
			
			// keyboard I/O device scheduling
			if (!blockQueueK.isEmpty()) {
				Process curIoProcess = blockQueueK.get(0);
				
				if (curIoProcess.cur_service_tick >= curIoProcess.getCurServiceTime()) {
					curIoProcess.proceedToNextService();
					SystemHelper.moveProcessFrom(blockQueueK, readyQueue);
					if (!blockQueueK.isEmpty()) {
						curIoProcess = blockQueueK.get(0);
					} 
					else {
						curIoProcess = null;
					}
				}
				
				if (curIoProcess != null) {
					curIoProcess.cur_service_tick++;
				}
			}
			
			// cpu scheduling
			if (readyQueue.isEmpty()) {
				prevProcessId = -1;
			} 
			else {
				boolean loggedWorking = false;
				Process curProcess = readyQueue.get(0);
				curProcessId = curProcess.getId();
				if (curProcessId != prevProcessId) {
					dispatchedTick = curTick;
				}
				
				curProcess.cur_service_tick++;
				if (curProcess.isCurServiceOver()) {
					ManageNextServiceFCFS.manageNextServiceFcfs(curProcess, completeNum, dispatchedTick, curTick, readyQueue,
							processesDone, blockQueueK, loggerMap.get(curProcess.getId()));
					loggedWorking = true;
				}
				
				if (curTick + 1 - dispatchedTick >= CLOCK) {
					if (loggedWorking == false) {
						loggerMap.get(curProcess.getId()).logWorking(dispatchedTick, curTick + 1);
						SystemHelper.moveProcessFrom(readyQueue, readyQueue);
						prevProcessId = -1;
						dispatchedTick = curTick + 1;
					}
				}
				prevProcessId = curProcessId;
			}
			if (completeNum == processes.size()) {
				break;
			}
		}
		
		Result res = new Result(processes);
		res.setSequence(loggerMap);
		
		res.printSequences();
		
		return res;
	}
	
}
