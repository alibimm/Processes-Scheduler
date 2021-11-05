package Project;

import java.util.*;

public class FCFS implements Algorithm {
    private ArrayList<ProcessInCPU> readyQueue;
    private ArrayList<ProcessInCPU> blockQueueIO;
    private ArrayList<ProcessInCPU> completedProcesses;
    private int complete_num;
    private int dispatchedTick;
    private int curProccessID, prevProccessID;
    private int MAX_LOOP;

    private static FCFS instance = new FCFS();
    
    private FCFS() {
    	readyQueue = new ArrayList<>();
        blockQueueIO = new ArrayList<>();
        completedProcesses = new ArrayList<>();
        complete_num = 0;
        dispatchedTick = 0;
        curProccessID = -1;
        prevProccessID = -1;
        MAX_LOOP=1000;
    }
    
    public static FCFS getInstance() {
    	return instance;
    }

    public Result schedule(ArrayList<Process> processes){
    	//storage of loggers for each process
    	HashMap<Integer, ProcessInCPU> loggerMap = new HashMap<Integer, ProcessInCPU>();
    	
    	//Create new logger for each process
//    	for (int i = 0; i < processes.size(); i++) {
//    		ProcessInCPU logger = ProcessInCPU.create(processes.get(i));
//    		loggerMap.put(processes.get(i).getId(), logger);
//    	}
    	
        // main loop
        for (int currentTick = 0; currentTick < MAX_LOOP; currentTick++) {
            // long term scheduler
            for (int i = 0; i < processes.size(); i++) {
                if (processes.get(i).getArrivalTime() == currentTick) { // process arrives at current tick
                	ProcessInCPU logger = ProcessInCPU.create(processes.get(i));
                	loggerMap.put(processes.get(i).getId(), logger);
                    readyQueue.add(logger);
                }
            }

             // keyboard I/O device scheduling
            if (!blockQueueIO.isEmpty()) {
                ProcessInCPU curIOProcess = blockQueueIO.get(0); // always provide service to the first process in block queue
                
                if (curIOProcess.isCurServiceOver()) { // I/O service is completed
                    curIOProcess.proceedToNextService();
                    SystemHelper.moveProcessFrom(blockQueueIO, readyQueue);
                    
                    if (!blockQueueIO.isEmpty()) curIOProcess = blockQueueIO.get(0); 
                    else curIOProcess = null;
                }
                
                for (int i = 1; i < blockQueueIO.size(); i++) {
//                	block_queue_K.get(i).updateQueueingTime();
                	loggerMap.get(blockQueueIO.get(i).getId()).updateQueueingTime();
                }
                if (curIOProcess != null) curIOProcess.incrementCurServiceTick();
            }

            // CPU scheduling
            if (readyQueue.isEmpty()) {
                prevProccessID = -1; // reset the previous dispatched process ID to empty, no process for scheduling
            } else {
                ProcessInCPU curProcess = readyQueue.get(0); // always dispatch the first process in ready queue
                curProccessID = curProcess.getId();
                if (curProccessID != prevProccessID)
                { // store the tick when current process is dispatched
                    dispatchedTick = currentTick;
                }
                
                curProcess.incrementCurServiceTick();
                for (int i = 1; i < readyQueue.size(); i++) {
//                	ready_queue.get(i).updateQueueingTime();
                	loggerMap.get(readyQueue.get(i).getId()).updateQueueingTime();
                }
                
                if (curProcess.isCurServiceOver()) {
                    ManageNextServiceFCFS.manageNextServiceFcfs(curProcess, complete_num, dispatchedTick, currentTick, readyQueue,
                                            completedProcesses, blockQueueIO, loggerMap.get(curProcess.getId())); // look for next service
                }
                
                prevProccessID = curProccessID;
            }
            
            if (completedProcesses.size() == processes.size()) break;
        }
        
        
        //Generate result
    	Result res = new Result(processes);
        res.setSequence(loggerMap);
        
        res.printStats();
        
        return res;

    }
}
