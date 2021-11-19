package Project;

import java.util.ArrayList;
import java.util.Collections;

public class SPN extends Algorithm {
	private final AlgorithmType type = AlgorithmType.SPN;
	private ArrayList<ProcessInCPU> readyQueue;
    private ArrayList<ProcessInCPU> blockQueueIO;
    private ArrayList<ProcessInCPU> completedProcesses;
    private int dispatchedTick;
    private int curProcessID, prevProcessID;
    private boolean isBusy; // change later
	
	private SPN() {
		readyQueue = new ArrayList<ProcessInCPU>();
        blockQueueIO = new ArrayList<ProcessInCPU>();
        completedProcesses = new ArrayList<ProcessInCPU>();
        dispatchedTick = 0;
        curProcessID = -1;
        prevProcessID = -1;
        isBusy = false;
	}
	
	private static SPN instance = new SPN();
	public static SPN getInstance() { return instance; }

	@Override
	public ArrayList<ProcessInCPU> schedule(ArrayList<Process> processes) {
		reset();
		
        // main loop
        for(int tick = 0; tick < Constants.MAX_LOOP; tick++) {
        	
            // long term scheduler
            for (int i = 0; i < processes.size(); i++) {
                if (processes.get(i).getArrivalTime() == tick) { // process arrives at current tick
                	ProcessInCPU logger = ProcessInCPU.create(processes.get(i));
                    readyQueue.add(logger);
                }
            }

             // keyboard I/O device scheduling
            if (!blockQueueIO.isEmpty()) {
				ProcessInCPU curIOProcess = blockQueueIO.get(0);
				
				if (curIOProcess.isCurServiceOver()) {
					curIOProcess.proceedToNextService();
					ProcessInCPU.moveProcessFrom(blockQueueIO, readyQueue);
				}
				
				if (!blockQueueIO.isEmpty()) blockQueueIO.get(0).incrementCurServiceTick();
                ProcessInCPU.updateQueingTime(blockQueueIO, 1, blockQueueIO.size());
			}

            // CPU scheduling
            if (readyQueue.isEmpty()) {
                prevProcessID = -1; // reset the previous dispatched process ID to empty no process for scheduling
            } else {
            	if (!isBusy) {
            		int shortestIndex = ProcessInCPU.findShortestServiceNextProcess(readyQueue);
            		Collections.swap(readyQueue, 0, shortestIndex);
            		isBusy = true;
            	}
            	ProcessInCPU curProcess = readyQueue.get(0);
                curProcessID = curProcess.getId();
                if (curProcessID != prevProcessID) { // store the tick when current process is dispatched
                    dispatchedTick = tick;
                }
                
                curProcess.incrementCurServiceTick(); // increment the num of ticks that have been spent on current service
                ProcessInCPU.updateQueingTime(readyQueue, 1, readyQueue.size());
                
                if (curProcess.isCurServiceOver()) { // current service is completed
                    manageCurrentCPUProcess(tick);
                }
                
                prevProcessID = curProcessID; // log the previous dispatched process ID
            }
            
            if (completedProcesses.size() == processes.size()) break;
        }
        
        return completedProcesses;
	}

	@Override
	protected void manageCurrentCPUProcess(int curTick) {
    	ProcessInCPU process = readyQueue.get(0);
    	process.logWorking(dispatchedTick, curTick + 1);
    	isBusy = false;
    	
        boolean processCompleted = process.proceedToNextService();
        if (processCompleted) {
        	ProcessInCPU.moveProcessFrom(readyQueue, completedProcesses); // remove current process from ready queue
        } else if (process.getCurServiceType() == ServiceType.Keyboard) { // next service is keyboard input, block current process
        	ProcessInCPU.moveProcessFrom(readyQueue, blockQueueIO);
        }
    }
	
	private void reset() {
		readyQueue.clear();
        blockQueueIO.clear();
        completedProcesses.clear();
        dispatchedTick = 0;
        curProcessID = -1;
        prevProcessID = -1;
        isBusy = false;
	}
	
	@Override
	public AlgorithmType getType() {
		return type;
	}
}
