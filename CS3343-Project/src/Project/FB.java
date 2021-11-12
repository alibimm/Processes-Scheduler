package Project;

import java.util.ArrayList;
import java.util.HashMap;

public class FB extends Algorithm {
	private final AlgorithmType type = AlgorithmType.FB;
	private ArrayList<ArrayList<ProcessInCPU>> allReadyQueues;
    private ArrayList<ProcessInCPU> blockQueueIO;
    private ArrayList<ProcessInCPU> completedProcesses;
    private int dispatchedTick;
    private int curProcessID, prevProcessID;
    private HashMap<Integer, Integer> priorityMap;
    
	// Constructor
	private FB() {
		allReadyQueues = new ArrayList<ArrayList<ProcessInCPU>>();
		blockQueueIO = new ArrayList<ProcessInCPU>();
		completedProcesses = new ArrayList<ProcessInCPU>();
	    dispatchedTick = 0;
	    curProcessID = -1;
	    prevProcessID = -1;
	    priorityMap = new HashMap<Integer, Integer>();
	}
	
	private static FB instance = new FB();
	public static FB getInstance() { return instance; }
	
	@Override
	public ArrayList<ProcessInCPU> schedule(ArrayList<Process> processes) {

		// Adding all ready queues into one arraylist
		for (int i = 0; i < Constants.PRIORITY_COUNT; i++) {
			allReadyQueues.add(new ArrayList<ProcessInCPU>());
		}
		
    	// Main Loop
        for(int tick = 0; tick < Constants.MAX_LOOP; tick++) {
        	// Put process into ready queue if it arrived at current tick
        	for (int i = 0; i < processes.size(); i++) {
                if (processes.get(i).getArrivalTime() == tick) { // process arrives at current tick
                	ProcessInCPU logger = ProcessInCPU.create(processes.get(i));
                	allReadyQueues.get(0).add(logger);
                	priorityMap.put(logger.getId(), 0); // Set priority 0, since it just came
                }
            }
        	
        	// Keyboard I/O device scheduling
        	if (!blockQueueIO.isEmpty()) { // If it is not empty
        		ProcessInCPU curIOProcess = blockQueueIO.get(0); // always provide service to the first process in block queue
                
        		if (curIOProcess.isCurServiceOver()) { // I/O service is completed
        			curIOProcess.proceedToNextService();
        			int processPriority = priorityMap.get(curIOProcess.getId());
        			Util.moveProcessFrom(blockQueueIO, allReadyQueues.get(processPriority));
        			
        			
        		}
        		if (!blockQueueIO.isEmpty()) blockQueueIO.get(0).incrementCurServiceTick();
        		Util.updateQueingTime(blockQueueIO, 1, blockQueueIO.size());
        	}
        	
        	// CPU scheduling
            int firstQueue = firstNonEmptyQueue(allReadyQueues); // TODO: Change the var name
            
            if (firstQueue == -1) // no process for scheduling 
            	prevProcessID = -1; // Reset the previous dispatched process ID to empty
            else {
            	ArrayList<ProcessInCPU> readyQueue = allReadyQueues.get(firstQueue);
            	ProcessInCPU curProcess = readyQueue.get(0);
            	curProcessID = curProcess.getId();
            	
            	if (curProcessID != prevProcessID) // store the tick when current process is dispatched
            		dispatchedTick = tick;
            	
            	curProcess.incrementCurServiceTick(); // increment the num of ticks that have been spent on current service
            	Util.updateQueingTime(readyQueue, 1, readyQueue.size());
            	for (int i = firstQueue + 1; i < allReadyQueues.size(); i++) {
            		Util.updateQueingTime(allReadyQueues.get(i), 0, allReadyQueues.get(i).size());
            	}
            	
            	if (curProcess.isCurServiceOver() || tick + 1 - dispatchedTick >= Constants.CLOCK) { // current service is completed
            		manageCurrentCPUProcess(tick);
            	}
            	
            	prevProcessID = curProcessID; // log the previous dispatched process ID
            }
            
            if (completedProcesses.size() == processes.size()) break; // all process completed
        }   
        
        return completedProcesses;
		
	}
	
	@Override
    protected void manageCurrentCPUProcess(int curTick) {
		int firstQueue = firstNonEmptyQueue(allReadyQueues);
		
		ArrayList<ProcessInCPU> readyQueue = allReadyQueues.get(firstQueue);
    	ProcessInCPU process = readyQueue.get(0);
        process.logWorking(dispatchedTick, curTick + 1);
    	
    	if (process.isCurServiceOver()) {
    		boolean processCompleted = process.proceedToNextService();
            if (processCompleted) { 
                Util.moveProcessFrom(readyQueue, completedProcesses); // remove current process from ready queue
            } else if (process.getCurServiceType() == ServiceType.Keyboard) { // next service is keyboard input, block current process
                Util.moveProcessFrom(readyQueue, blockQueueIO);
            }
    	} else {
			if (firstQueue == Constants.PRIORITY_COUNT - 1) {
				Util.moveProcessFrom(readyQueue, readyQueue);
			} else {
				priorityMap.put(process.getId(), firstQueue + 1);
				Util.moveProcessFrom(readyQueue, allReadyQueues.get(firstQueue + 1));
			}
			dispatchedTick = curTick + 1; // reset the previous dispatched process ID to empty
    	}
    }
	
	private int firstNonEmptyQueue(ArrayList<ArrayList<ProcessInCPU>> queues) {
		for (int i = 0; i < queues.size(); i++) {
			if (!queues.get(i).isEmpty()) return i;
		}
		return -1;
	}

	@Override
	public AlgorithmType getType() {
		return type;
	}
}
