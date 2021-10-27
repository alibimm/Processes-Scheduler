package Project;

import java.util.*;

public class FCFS implements Algorithm {
    private ArrayList<Process> ready_queue;
    private ArrayList<Process> block_queue_K;
    private ArrayList<Process> processes_done;
    private int complete_num = 0;
    private int dispatched_tick = 0;
    private int cur_process_id = -1, prev_process_id = -1;
    private int MAX_LOOP=1000;


    //TODO ADD System_helper.java class

    public Result schedule(ArrayList<Process> processes){
    	
    	//storage of loggers for each process
    	HashMap<Integer, ProcessInCPU> logger_map = new HashMap<Integer, ProcessInCPU>();
    	
    	//Create new logger for each process
    	for(int i=0; i<processes.size(); i++) {
    		ProcessInCPU logger = new ProcessInCPU(processes.get(i));
    		logger_map.put(processes.get(i).getId(), logger);
    	}
    	
        // main loop
        for(int cur_tick = 0; cur_tick < MAX_LOOP; cur_tick++){
            // long term scheduler
            for (int i = 0; i < processes.size(); i++)
            {
                if (processes.get(i).getArrivalTime() == cur_tick)
                { // process arrives at current tick
                    ready_queue.add(processes.get(i));
                }
            }

             // keyboard I/O device scheduling
            if (!block_queue_K.isEmpty())
            {
                Process cur_io_process = block_queue_K.get(0); // always provide service to the first process in block queue
                if (cur_io_process.cur_service_tick >= cur_io_process.getCurService().getServiceTime())
                { // I/O service is completed
                    cur_io_process.proceedToNextService();
                    System_helper.move_process_from(block_queue_K, ready_queue);
                }
                cur_io_process.cur_service_tick++; // increment the num of ticks that have been spent on current service
            }

            // CPU scheduling
            if (ready_queue.isEmpty())
            {                         // no process for scheduling
                prev_process_id = -1; // reset the previous dispatched process ID to empty
            }
            else
            {
                Process cur_process = ready_queue.get(0); // always dispatch the first process in ready queue
                cur_process_id = cur_process.getId();
                if (cur_process_id != prev_process_id)
                { // store the tick when current process is dispatched
                    dispatched_tick = cur_tick;
                }
                cur_process.cur_service_tick++; // increment the num of ticks that have been spent on current service
                if (cur_process.cur_service_tick >= cur_process.getCurSurviceTime())
                { // current service is completed
                    Manage_Next_Service_FCFS.manageNextServiceFcfs(cur_process, complete_num, dispatched_tick, cur_tick, ready_queue,
                                            processes_done, block_queue_K, logger_map.get(cur_process.getId())); // look for next service
                }
                prev_process_id = cur_process_id; // log the previous dispatched process ID
            }
            if (complete_num == processes.size())
            { // all process completed
                break;
            }
        }
        
//        write_file(processes_done, res); // write output
        
        //Generate result
    	Result res = new Result(processes);
        res.setSequence(logger_map);
        
        return res;

    }
}
