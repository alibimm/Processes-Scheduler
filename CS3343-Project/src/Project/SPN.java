package Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class SPN implements Algorithm {
	private ArrayList<Process> readyQueue = new ArrayList<>();
    private ArrayList<Process> blockQueueK = new ArrayList<>();
    private ArrayList<Process> processes_done = new ArrayList<>();
    private int complete_num = 0;
    private int dispatched_tick = 0;
    private int cur_process_id = -1, prev_process_id = -1;
    private int MAX_LOOP=1000;
    private boolean flag = false; // change later
    
	private static SPN instance = new SPN();
	
	private SPN() {}
	
	public static SPN getInstance() {
		return instance;
	}
	
	private Process shortestProcess(ArrayList<Process> processes) {
		Process shortest = processes.get(0);
		int shortestInd = 0;
		for (int i = 0; i < processes.size(); i++) {
			
			if (processes.get(i).getCurServiceTime() < shortest.getCurServiceTime()) {
				shortest = processes.get(i);
				shortestInd = i;
			}
		}
		Collections.swap(processes, 0, shortestInd);
		flag = true;
		return shortest;
	}

	@Override
	public Result schedule(ArrayList<Process> processes) {


    	//storage of loggers for each process
    	HashMap<Integer, ProcessInCPU> loggerMap = new HashMap<Integer, ProcessInCPU>();
    	
    	//Create new logger for each process
    	for(int i=0; i<processes.size(); i++) {
    		ProcessInCPU logger = new ProcessInCPU(processes.get(i));
    		loggerMap.put(processes.get(i).getId(), logger);
    	}
    	
        // main loop
        for(int cur_tick = 0; cur_tick < MAX_LOOP; cur_tick++){
            // long term scheduler
            for (int i = 0; i < processes.size(); i++)
            {
                if (processes.get(i).getArrivalTime() == cur_tick)
                { // process arrives at current tick
                    readyQueue.add(processes.get(i));
                }
            }

             // keyboard I/O device scheduling
            if (!blockQueueK.isEmpty())
            {
                Process cur_io_process = blockQueueK.get(0); // always provide service to the first process in block queue
                // AYAN TODO
                // for all other processes in queue, except for current, increment k_queuing_time: For loop from index 1 to end
                
                if (cur_io_process.cur_service_tick >= cur_io_process.getCurServiceTime())
                { // I/O service is completed
                    cur_io_process.proceedToNextService();
                    SystemHelper.moveProcessFrom(blockQueueK, readyQueue);
                    
                    if (!blockQueueK.isEmpty()) cur_io_process = blockQueueK.get(0); 
                    else cur_io_process = null;
                }
                
                for (int i=1; i<blockQueueK.size(); i++) {
//                	blockQueueK.get(i).updateQueueingTime();
                	loggerMap.get(blockQueueK.get(i).getId()).updateQueueingTime();
                }
                if (cur_io_process != null) cur_io_process.cur_service_tick++; // increment the num of ticks that have been spent on current service
                
                
            }

            // CPU scheduling
            if (readyQueue.isEmpty())
            {                         // no process for scheduling
                prev_process_id = -1; // reset the previous dispatched process ID to empty
            }
            else
            {
            	Process cur_process = flag ? readyQueue.get(0) : shortestProcess(readyQueue);
//            	if (flag) {
//            		
//            	}
//                Process cur_process = shortestProcess(ready_queue); // always dispatch the first process in ready queue
                
                cur_process_id = cur_process.getId();
                if (cur_process_id != prev_process_id)
                { // store the tick when current process is dispatched
                    dispatched_tick = cur_tick;
                }
                
                cur_process.cur_service_tick++; // increment the num of ticks that have been spent on current service
                // AYAN TODO
                // for all other processes in queue, except for current, increment p_queuing_time: For loop from index 1 to end
                for (int i=1; i<readyQueue.size(); i++) {
//                	readyQueue.get(i).updateQueueingTime();
                	loggerMap.get(readyQueue.get(i).getId()).updateQueueingTime();
                }
                
                if (cur_process.cur_service_tick >= cur_process.getCurServiceTime())
                { // current service is completed
                	flag = false;
                    ManageNextServiceFCFS.manageNextServiceFcfs(cur_process, complete_num, dispatched_tick, cur_tick, readyQueue,
                                            processes_done, blockQueueK, loggerMap.get(cur_process.getId())); // look for next service
                }
                
                prev_process_id = cur_process_id; // log the previous dispatched process ID
            }
            if (complete_num == processes.size())
            { // all process completed
                break;
            }
        }
        
        
        //Generate result
    	Result res = new Result(processes);
        res.setSequence(loggerMap);
        
//        res.printSequences();
//        res.printQueueingTimes();
        res.printStats();
        
        return res;
	}

}
