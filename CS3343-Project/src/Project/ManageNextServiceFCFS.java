package Project;

import java.util.ArrayList;

public class ManageNextServiceFCFS {
    public static void manageNextServiceFcfs(int dispatchedTick, int curTick, ArrayList<ProcessInCPU> readyQueue, ArrayList<ProcessInCPU> completedProcesses, ArrayList<ProcessInCPU> block_queue_K)
    {
    	ProcessInCPU process = readyQueue.get(0);
        boolean processCompleted = process.proceedToNextService();
        
        if (processCompleted) { 
            process.logWorking(dispatchedTick, curTick + 1);
            Util.moveProcessFrom(readyQueue, completedProcesses); // remove current process from ready queue
        } else if (process.getCurServiceType() == ServiceType.Keyboard) { // next service is keyboard input, block current process
            process.logWorking(dispatchedTick, curTick + 1);
            Util.moveProcessFrom(readyQueue, block_queue_K);
        }
    }
}

