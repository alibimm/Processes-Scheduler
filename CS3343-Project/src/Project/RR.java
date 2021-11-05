package Project;

import java.util.ArrayList;
import java.util.HashMap;

public class RR implements Algorithm {
	private ArrayList<Process> readyQueue;
	private ArrayList<Process> blockQueueK;
	private ArrayList<Process> processesDone;
	private int completeNum;
	private int dispatchedTick;
	private int curProcessId;
	private int prevProcessId;
	private int maxLoop;
	private int CLOCK;
	
	private static RR instance = new RR();
	
	private RR() {
		readyQueue = new ArrayList<>();
		blockQueueK = new ArrayList<>();
		processesDone = new ArrayList<>();
		completeNum = 0;
		dispatchedTick = 0;
		curProcessId = -1;
		prevProcessId = -1;
		maxLoop = 1000;
		CLOCK = 5;
	}
	
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
				for (int i=1; i<blockQueueK.size(); i++) {
//                	blockQueueK.get(i).updateQueueingTime();
                	loggerMap.get(blockQueueK.get(i).getId()).updateQueueingTime();
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
				for (int i=1; i<readyQueue.size(); i++) {
//                	readyQueue.get(i).updateQueueingTime();
                	loggerMap.get(readyQueue.get(i).getId()).updateQueueingTime();
                }
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
		
//		res.printSequences();
//        res.printQueueingTimes();
		res.printStats();
		return res;
	}
	
}
