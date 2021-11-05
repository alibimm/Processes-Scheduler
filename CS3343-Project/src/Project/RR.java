package Project;

import java.util.ArrayList;
import java.util.HashMap;

public class RR extends Algorithm {
	private ArrayList<ProcessInCPU> readyQueue;
	private ArrayList<ProcessInCPU> blockQueueIO;
	private ArrayList<ProcessInCPU> completedProcesses;
//	private int completeNum;
	private int dispatchedTick;
	private int curProcessId, prevProcessId;
	private int MAX_LOOP;
	private int CLOCK;
	
	private static RR instance = new RR();
	
	private RR() {
		readyQueue = new ArrayList<>();
		blockQueueIO = new ArrayList<>();
		completedProcesses = new ArrayList<>();
//		completeNum = 0;
		dispatchedTick = 0;
		curProcessId = -1;
		prevProcessId = -1;
		MAX_LOOP = 1000;
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
//		for (int i=0; i<processes.size(); i++) {
//			ProcessInCPU logger = new ProcessInCPU(processes.get(i));
//			loggerMap.put(processes.get(i).getId(), logger);
//		}
//		
		
		// main loop
		for (int currentTick = 0; currentTick < MAX_LOOP; currentTick++) {
			// long term scheduler 
			for (int i = 0; i < processes.size(); i++) {
				if (processes.get(i).getArrivalTime() == currentTick) {
					ProcessInCPU logger = ProcessInCPU.create(processes.get(i));
                	loggerMap.put(processes.get(i).getId(), logger);
                    readyQueue.add(logger);
				}
				
			}
			
			// keyboard I/O device scheduling
			if (!blockQueueIO.isEmpty()) {
				ProcessInCPU curIOProcess = blockQueueIO.get(0);
				
				if (curIOProcess.isCurServiceOver()) {
					curIOProcess.proceedToNextService();
					SystemHelper.moveProcessFrom(blockQueueIO, readyQueue);
					if (!blockQueueIO.isEmpty()) {
						curIOProcess = blockQueueIO.get(0);
					} else {
						curIOProcess = null;
					}
				}
				for (int i = 1; i < blockQueueIO.size(); i++) {
//                	blockQueueIO.get(i).updateQueueingTime();
                	loggerMap.get(blockQueueIO.get(i).getId()).updateQueueingTime();
                }
				
				if (curIOProcess != null) curIOProcess.incrementCurServiceTick();
			}
			
			// cpu scheduling
			if (readyQueue.isEmpty()) {
				prevProcessId = -1;
			} 
			else {
				boolean loggedWorking = false;
				ProcessInCPU curProcess = readyQueue.get(0);
				curProcessId = curProcess.getId();
				if (curProcessId != prevProcessId) {
					dispatchedTick = currentTick;
				}
				
				curProcess.incrementCurServiceTick();
				for (int i = 1; i < readyQueue.size(); i++) {
//                	readyQueue.get(i).updateQueueingTime();
                	loggerMap.get(readyQueue.get(i).getId()).updateQueueingTime();
                }
				if (curProcess.isCurServiceOver()) {
					manageCurrentProcess(currentTick);
					loggedWorking = true;
				}
				
				if (currentTick + 1 - dispatchedTick >= CLOCK) {
					if (loggedWorking == false) {
						loggerMap.get(curProcess.getId()).logWorking(dispatchedTick, currentTick + 1);
						SystemHelper.moveProcessFrom(readyQueue, readyQueue);
						prevProcessId = -1;
						dispatchedTick = currentTick + 1;
					}
				}
				prevProcessId = curProcessId;
			}
			
			if (completedProcesses.size() == processes.size()) break;
		}
		
		Result res = new Result(processes);
		res.setSequence(loggerMap);
		
//		res.printSequences();
//        res.printQueueingTimes();
		res.printStats();
		return res;
	}
	
	@Override
	protected void manageCurrentProcess(int curTick) {
    	ProcessInCPU process = readyQueue.get(0);
        boolean processCompleted = process.proceedToNextService();
        
        if (processCompleted) { 
            process.logWorking(dispatchedTick, curTick + 1);
            SystemHelper.moveProcessFrom(readyQueue, completedProcesses); // remove current process from ready queue
        } else if (process.getCurServiceType() == ServiceType.Keyboard) { // next service is keyboard input, block current process
            process.logWorking(dispatchedTick, curTick + 1);
            SystemHelper.moveProcessFrom(readyQueue, blockQueueIO);
        }
    }
	
}
