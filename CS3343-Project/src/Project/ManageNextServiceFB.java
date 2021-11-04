package Project;

import java.util.ArrayList;

public class ManageNextServiceFB {

	public static void manageNextServiceFb(Process cur_process, 
			int complete_num, int dispatched_tick, int cur_tick, 
			ArrayList<Process> ready_queue, ArrayList<Process> processes_done, ArrayList<Process> block_queue_K,
		    ProcessInCPU logger, boolean logged_working) 
	{
		boolean process_completed = cur_process.proceedToNextService();
		
		if (process_completed) { // the whole process is completed
			complete_num++;
			logger.logWorking(dispatched_tick, cur_tick + 1);
			logged_working = true;
			SystemHelper.moveProcessFrom(ready_queue, processes_done); // remove current process from ready queue
			}
		else if (cur_process.getCurServiceType() == ServiceType.Keyboard) { // next service is keyboard input, block current process
			logger.logWorking(dispatched_tick, cur_tick + 1);
			logged_working = true;
			SystemHelper.moveProcessFrom(ready_queue, block_queue_K);
			}
	}
	
}
