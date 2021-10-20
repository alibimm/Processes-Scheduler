package Project;

public class Manage_Next_Service_FCFS {
    public void manage_next_service_fcfs(Process cur_process, int complete_num, int dispatched_tick,
    int cur_tick, ArrayList<Process> ready_queue, ArrayList<Process> processes_done, ArrayList<Process> block_queue_K)
    {
        boolean process_completed = cur_process.proceed_to_next_service();
        if (process_completed)
        { // the whole process is completed
            complete_num++;
            cur_process.log_working(dispatched_tick, cur_tick + 1);
            System_helper.move_process_from(ready_queue, processes_done); // remove current process from ready queue
        }
        else if (cur_process.cur_service.type == "K")
        { // next service is keyboard input, block current process
            cur_process.log_working(dispatched_tick, cur_tick + 1);
            System_helper.move_process_from(ready_queue, block_queue_K);
        }
    }
}

