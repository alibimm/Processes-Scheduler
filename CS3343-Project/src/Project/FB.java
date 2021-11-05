package Project;

import java.util.ArrayList;
import java.util.HashMap;

public class FB extends Algorithm {
	
	private ArrayList<ArrayList<ProcessInCPU>> allReadyQueues;
	
	
    private ArrayList<ProcessInCPU> blockQueueIO;
    private ArrayList<ProcessInCPU> completedProcesses;
    
    private int complete_num;
    private int dispatchedTick;
    private int curProcessID, prevProcessID;
    private int MAX_LOOP;
    private int CLOCK; // Interrupt
    private HashMap<ProcessInCPU, Integer> priority;
	
	
	// Implementing Singleton
	// Creating Instance
	private static FB instance = new FB();
	
	// Constructor
	private FB() {
		
		allReadyQueues = new ArrayList<ArrayList<ProcessInCPU>>();
		
		blockQueueIO = new ArrayList<>();
		completedProcesses = new ArrayList<>();
		
		complete_num = 0;
	    dispatchedTick = 0;
	    curProcessID = -1;
	    prevProcessID = -1;
	    MAX_LOOP = 1000;
	    CLOCK = 5; // Interrupt occurs every 5 ticks
	    priority = new HashMap<ProcessInCPU, Integer>();
	    
	}
	
	// Getting Instance
	public static FB getInstance() {
		return instance;
	}
	
	@Override
	public Result schedule(ArrayList<Process> processes) {
		
		// Adding all ready queues into one arraylist
		for (int i = 0; i < 3; ++i)
			allReadyQueues.add(new ArrayList<ProcessInCPU>());

		//storage of loggers for each process
    	HashMap<Integer, ProcessInCPU> loggerMap = new HashMap<Integer, ProcessInCPU>();
    	
    	//Create new logger for each process
    	for(int i=0; i<processes.size(); i++) {
    		
    		ProcessInCPU logger = new ProcessInCPU(processes.get(i));
    		loggerMap.put(processes.get(i).getId(), logger);
    	}
    	
    	// Main Loop
        for(int currentTick = 0; currentTick < MAX_LOOP; currentTick++) {
        	
        	// Put process into ready queue if it arrived at current tick
        	for (int i = 0; i < processes.size(); i++) {
                if (processes.get(i).getArrivalTime() == currentTick) { // process arrives at current tick
                	ProcessInCPU logger = ProcessInCPU.create(processes.get(i));
                	loggerMap.put(processes.get(i).getId(), logger);
                	allReadyQueues.get(0).add(logger);
                	priority.put(logger, 0); // Set priority 0, since it just came
                }
            }
        	
        	// Keyboard I/O device scheduling
        	if (!blockQueueIO.isEmpty()) { // If it is not empty
        		
        		ProcessInCPU curIOProcess = blockQueueIO.get(0); // always provide service to the first process in block queue
                
        		if (curIOProcess.isCurServiceOver()) { // I/O service is completed
        			
        			curIOProcess.proceedToNextService();
        			SystemHelper.moveProcessFrom(blockQueueIO, allReadyQueues.get(priority.get(curIOProcess)));
        			
        			if (!blockQueueIO.isEmpty()) curIOProcess = blockQueueIO.get(0);
        			else curIOProcess = null;
        			
        		}
        		
        		for (int i=1; i<blockQueueIO.size(); i++) {
//                	block_queue_K.get(i).updateQueueingTime();
                	loggerMap.get(blockQueueIO.get(i).getId()).updateQueueingTime();
                }
        		
        		if (curIOProcess != null) curIOProcess.incrementCurServiceTick(); // increment the num of ticks that have been spent on current service
        		
        		// TODO
                // for all other processes in queue, except for current, increment k_queuing_time: For loop from index 1 to end
        		
        	}
        	
        	// CPU scheduling
        	boolean are_all_queues_empty = true; // flag to track if there is any process in any of ready queues
        	int not_empty_queue_priority_index = -1; // index of a queue that is not empty, if empty default value = -1
        	
            for (int i = 0; i < 3; ++i) { // loop checks if there is any process in any of ready queues
            	if (!allReadyQueues.get(i).isEmpty()) {
            		are_all_queues_empty = false;
            		not_empty_queue_priority_index = i;
            		break;
            	}
            }
            
            if (are_all_queues_empty) // no process for scheduling 
            	prevProcessID = -1; // Reset the previous dispatched process ID to empty
            else {
            	
            	boolean service_completed = false; // flag to track if curProcess done the CPU service in 5 ticks
            	boolean logged_working = false; // flag to track if curProcess.log_working() already occured
            	
            	ProcessInCPU curProcess = allReadyQueues.get(not_empty_queue_priority_index).get(0);
            	curProcessID = curProcess.getId();
            	
            	if (curProcessID != prevProcessID) // store the tick when current process is dispatched
            		dispatchedTick = currentTick;
            	
            	curProcess.incrementCurServiceTick(); // increment the num of ticks that have been spent on current service
            	
            	// TODO
                // for all other processes in queue, except for current, increment p_queuing_time: For loop from index 1 to end
            	for(int j=0; j<allReadyQueues.size(); j++) {
            		for (int i=1; i<allReadyQueues.get(j).size(); i++) {
//                    	ready_queue.get(i).updateQueueingTime();
            			if(allReadyQueues.get(j).get(i).getId()!=curProcess.getId()) {
                        	loggerMap.get(allReadyQueues.get(j).get(i).getId()).updateQueueingTime();
            			}
                    }
            	}
            		
            	prevProcessID = curProcessID; // log the previous dispatched process ID
            	
            	if (curProcess.isCurServiceOver()) { // current service is completed
            		
            		service_completed = true; // the service is done
            		
            		manageCurrentProcess(currentTick);
            		logged_working = true;
            	}
            	
            	if (currentTick + 1 - dispatchedTick >= CLOCK && !service_completed) { // Check clock interrupts and if interrupt should occur but service is completed - ignore
            		
            		if (!logged_working) { //check if logWorking already occured in manageNextServiceFb(...)
            			
            			loggerMap.get(curProcess.getId()).logWorking(dispatchedTick, currentTick + 1);
            			
            			if (not_empty_queue_priority_index != 2) {  // if process was not in a queue with lowest priority
            				int temp_priority = priority.get(curProcess);
            				priority.put(curProcess, temp_priority + 1); // Increase priority to the next one
            				SystemHelper.moveProcessFrom(allReadyQueues.get(not_empty_queue_priority_index), allReadyQueues.get(not_empty_queue_priority_index + 1));
            			} else // if process is already in a queue with lowest priority put it to the tail of that queue
            				SystemHelper.moveProcessFrom(allReadyQueues.get(2), allReadyQueues.get(2)); 
            			
            			prevProcessID = -1; // reset the previous dispatched process ID to empty
            			
            		}
            		
            	}
            	
            }
            
            if (complete_num == processes.size()) break; // all process completed
        	
        }   
        
        //Generate result
    	Result res = new Result(processes);
        res.setSequence(loggerMap);
        res.printStats();
//        res.printSequences();
        return res;
		
	}
	
	@Override
    protected void manageCurrentProcess(int curTick) {
		int nonEmptyQueueIndex = 0;
		while (allReadyQueues.get(nonEmptyQueueIndex).isEmpty()) {
			nonEmptyQueueIndex++;
		}
		
		ArrayList<ProcessInCPU> readyQueue = allReadyQueues.get(nonEmptyQueueIndex);
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
