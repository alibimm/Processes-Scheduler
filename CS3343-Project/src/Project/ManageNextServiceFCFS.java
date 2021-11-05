package Project;

import java.util.ArrayList;

public class ManageNextServiceFCFS {
    public static void manageNextServiceFcfs(ProcessInCPU cur_process, int complete_num, int dispatched_tick,
    int cur_tick, ArrayList<ProcessInCPU> ready_queue, ArrayList<ProcessInCPU> processes_done, ArrayList<ProcessInCPU> block_queue_K,
    ProcessInCPU logger)
    {
        boolean process_completed = cur_process.proceedToNextService();
        if (process_completed)
        { // the whole process is completed
            complete_num++;
            logger.logWorking(dispatched_tick, cur_tick + 1);
            SystemHelper.moveProcessFrom(ready_queue, processes_done); // remove current process from ready queue
        }
        else if (cur_process.getCurServiceType() == ServiceType.Keyboard)
        { // next service is keyboard input, block current process
//            cur_process.logWorking(dispatched_tick, cur_tick + 1);
            logger.logWorking(dispatched_tick, cur_tick + 1);
            SystemHelper.moveProcessFrom(ready_queue, block_queue_K);
        }
    }
}

