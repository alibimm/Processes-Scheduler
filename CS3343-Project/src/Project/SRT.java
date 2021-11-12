package Project;

import java.util.ArrayList;
import java.util.Collections;

public class SRT extends Algorithm {
	private final AlgorithmType type = AlgorithmType.SRT;
	private ArrayList<ProcessInCPU> readyQueue;
    private ArrayList<ProcessInCPU> blockQueueIO;
    private ArrayList<ProcessInCPU> completedProcesses;
    private int dispatchedTick;
    private int curProcessID, prevProcessID;
	
	private SRT() {
		readyQueue = new ArrayList<ProcessInCPU>();
        blockQueueIO = new ArrayList<ProcessInCPU>();
        completedProcesses = new ArrayList<ProcessInCPU>();
        dispatchedTick = 0;
        curProcessID = -1;
        prevProcessID = -1;
	}

	private static SRT instance = new SRT();
	public static SRT getInstance() { return instance; }
	
	@Override
	public ArrayList<ProcessInCPU> schedule(ArrayList<Process> processes) {
		// main loop
        for (int tick = 0; tick < Constants.MAX_LOOP; tick++) {
        	
            // long term scheduler
            for (int i = 0; i < processes.size(); i++) {
                if (processes.get(i).getArrivalTime() == tick) { // process arrives at current tick
                	ProcessInCPU logger = ProcessInCPU.create(processes.get(i));
                    readyQueue.add(logger);
                }
            }

             // keyboard I/O device scheduling
            if (!blockQueueIO.isEmpty()) {
                ProcessInCPU curIOProcess = blockQueueIO.get(0); // always provide service to the first process in block queue
                
                if (curIOProcess.isCurServiceOver()) { // I/O service is completed
                    curIOProcess.proceedToNextService();
                    Util.moveProcessFrom(blockQueueIO, readyQueue);
                }
                
                if (!blockQueueIO.isEmpty()) blockQueueIO.get(0).incrementCurServiceTick();
                Util.updateQueingTime(blockQueueIO, 1, blockQueueIO.size());
            }

            // CPU scheduling
            if (readyQueue.isEmpty()) {
                prevProcessID = -1; // reset the previous dispatched process ID to empty, no process for scheduling
            } else {
        		int shortestRemainingIndex = ProcessInCPU.findShortestRemainingTimeProcess(readyQueue);
        		Collections.swap(readyQueue, 0,	shortestRemainingIndex);
                ProcessInCPU curProcess = readyQueue.get(0); // always dispatch the first process in ready queue
                curProcessID = curProcess.getId();
                if (curProcessID != prevProcessID) { // store the tick when current process is dispatched
                    dispatchedTick = tick;
                }
                
                curProcess.incrementCurServiceTick();
                Util.updateQueingTime(readyQueue, 1, readyQueue.size());
                
                if (curProcess.isCurServiceOver() || tick + 1 - dispatchedTick >= Constants.CLOCK) {
                    manageCurrentCPUProcess(tick);
                }
                
                prevProcessID = curProcessID;
            }
            
            if (completedProcesses.size() == processes.size()) break;
        }

        return completedProcesses;
	}

	@Override
	protected void manageCurrentCPUProcess(int curTick) {
		ProcessInCPU process = readyQueue.get(0);
    	process.logWorking(dispatchedTick, curTick + 1);
        
    	if (process.isCurServiceOver()) {
    		boolean processCompleted = process.proceedToNextService();
            if (processCompleted) {
                Util.moveProcessFrom(readyQueue, completedProcesses); // remove current process from ready queue
            } else if (process.getCurServiceType() == ServiceType.Keyboard) { 
                Util.moveProcessFrom(readyQueue, blockQueueIO); // next service is keyboard input, block current process
            }
    	} else {
			Util.moveProcessFrom(readyQueue, readyQueue);
			dispatchedTick = curTick + 1;
    	}
	}

	@Override
	public AlgorithmType getType() {
		return type;
	}
}
