package Project;

import java.util.ArrayList;
import java.util.HashMap;

public class FB implements Algorithm {
	
	private ArrayList<ArrayList<Process>> allReadyQueues;
	
	
    private ArrayList<Process> blockQueueK;
    private ArrayList<Process> processes_done;
    
    private int complete_num;
    private int dispatched_tick;
    private int cur_process_id, prev_process_id;
    private int MAX_LOOP;
    private int CLOCK; // Interrupt
    private HashMap<Process, Integer> priority;
	
	
	// Implementing Singleton
	// Creating Instance
	private static FB instance = new FB();
	
	// Constructor
	private FB() {
		
		allReadyQueues = new ArrayList<ArrayList<Process>>();
		
		blockQueueK = new ArrayList<>();
		processes_done = new ArrayList<>();
		
		complete_num = 0;
	    dispatched_tick = 0;
	    cur_process_id = -1;
	    prev_process_id = -1;
	    MAX_LOOP=1000;
	    CLOCK = 5; // Interrupt occurs every 5 ticks
	    priority = new HashMap<Process, Integer>();
	    
	}
	
	// Getting Instance
	public static FB getInstance() {
		return instance;
	}
	
	@Override
	public Result schedule(ArrayList<Process> processes) {
		
		// Adding all ready queues into one arraylist
		for (int i = 0; i < 3; ++i)
			allReadyQueues.add(new ArrayList<Process>());

		//storage of loggers for each process
    	HashMap<Integer, ProcessInCPU> loggerMap = new HashMap<Integer, ProcessInCPU>();
    	
    	//Create new logger for each process
    	for(int i=0; i<processes.size(); i++) {
    		ProcessInCPU logger = new ProcessInCPU(processes.get(i));
    		loggerMap.put(processes.get(i).getId(), logger);
    	}
    	
    	// Main Loop
        for(int cur_tick = 0; cur_tick < MAX_LOOP; cur_tick++) {
        	
        	// Put process into ready queue if it arrived at current tick
        	for (int i = 0; i < processes.size(); i++) {
                if (processes.get(i).getArrivalTime() == cur_tick) { // process arrives at current tick
                	priority.put(processes.get(i), 0); // Set priority 0, since it just came
                	allReadyQueues.get(0).add(processes.get(i)); // Add to the first prority queue
                }
            }
        	
        	// Keyboard I/O device scheduling
        	if (!blockQueueK.isEmpty()) { // If it is not empty
        		
        		Process cur_io_process = blockQueueK.get(0); // always provide service to the first process in block queue
                
        		if (cur_io_process.cur_service_tick >= cur_io_process.getCurServiceTime()) { // I/O service is completed
        			
        			cur_io_process.proceedToNextService();
        			SystemHelper.moveProcessFrom(blockQueueK, allReadyQueues.get(priority.get(cur_io_process)));
        			
        			if (!blockQueueK.isEmpty()) cur_io_process = blockQueueK.get(0);
        			else cur_io_process = null;
        			
        		}
        		
        		for (int i=1; i<blockQueueK.size(); i++) {
//                	block_queue_K.get(i).updateQueueingTime();
                	loggerMap.get(blockQueueK.get(i).getId()).updateQueueingTime();
                }
        		
        		if (cur_io_process != null) cur_io_process.cur_service_tick++; // increment the num of ticks that have been spent on current service
        		
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
            	prev_process_id = -1; // Reset the previous dispatched process ID to empty
            else {
            	
            	boolean service_completed = false; // flag to track if cur_process done the CPU service in 5 ticks
            	boolean logged_working = false; // flag to track if cur_process.log_working() already occured
            	
            	Process cur_process = allReadyQueues.get(not_empty_queue_priority_index).get(0);
            	cur_process_id = cur_process.getId();
            	
            	if (cur_process_id != prev_process_id) // store the tick when current process is dispatched
            		dispatched_tick = cur_tick;
            	
            	cur_process.cur_service_tick++; // increment the num of ticks that have been spent on current service
            	
            	// TODO
                // for all other processes in queue, except for current, increment p_queuing_time: For loop from index 1 to end
            	for(int j=0; j<allReadyQueues.size(); j++) {
            		for (int i=1; i<allReadyQueues.get(j).size(); i++) {
//                    	ready_queue.get(i).updateQueueingTime();
            			if(allReadyQueues.get(j).get(i).getId()!=cur_process.getId()) {
                        	loggerMap.get(allReadyQueues.get(j).get(i).getId()).updateQueueingTime();
            			}
                    }
            	}
            		
            	prev_process_id = cur_process_id; // log the previous dispatched process ID
            	
            	if (cur_process.isCurServiceOver()) { // current service is completed
            		
            		service_completed = true; // the service is done
            		
            		ManageNextServiceFB.manageNextServiceFb(cur_process, complete_num, dispatched_tick, cur_tick, allReadyQueues.get(not_empty_queue_priority_index),
                            processes_done, blockQueueK, loggerMap.get(cur_process.getId())); // look for next service
            		
            		logged_working = true;
            	}
            	
            	if (cur_tick + 1 - dispatched_tick >= CLOCK && !service_completed) { // Check clock interrupts and if interrupt should occur but service is completed - ignore
            		
            		if (!logged_working) { //check if logWorking already occured in manageNextServiceFb(...)
            			
            			loggerMap.get(cur_process.getId()).logWorking(dispatched_tick, cur_tick + 1);
            			
            			if (not_empty_queue_priority_index != 2) {  // if process was not in a queue with lowest priority
            				int temp_priority = priority.get(cur_process);
            				priority.put(cur_process, temp_priority + 1); // Increase priority to the next one
            				SystemHelper.moveProcessFrom(allReadyQueues.get(not_empty_queue_priority_index), allReadyQueues.get(not_empty_queue_priority_index + 1));
            			} else // if process is already in a queue with lowest priority put it to the tail of that queue
            				SystemHelper.moveProcessFrom(allReadyQueues.get(2), allReadyQueues.get(2)); 
            			
            			prev_process_id = -1; // reset the previous dispatched process ID to empty
            			
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

}
