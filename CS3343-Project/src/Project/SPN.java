package Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class SPN implements Algorithm {
	private ArrayList<ProcessInCPU> readyQueue;
    private ArrayList<ProcessInCPU> blockQueueIO;
    private ArrayList<ProcessInCPU> completedProcesses;
    private int completeNum;
    private int dispatchedTick;
    private int curProcessID, prevProcessID;
    private int MAX_LOOP;
    private boolean flag; // change later
    
	private static SPN instance = new SPN();
	
	private SPN() {
		readyQueue = new ArrayList<>();
        blockQueueIO = new ArrayList<>();
        completedProcesses = new ArrayList<>();
        completeNum = 0;
        dispatchedTick = 0;
        curProcessID = -1;
        prevProcessID = -1;
        MAX_LOOP=1000;
        flag = false;
	}
	
	public static SPN getInstance() {
		return instance;
	}

	@Override
	public Result schedule(ArrayList<Process> processes) {


    	//storage of loggers for each process
    	HashMap<Integer, ProcessInCPU> loggerMap = new HashMap<Integer, ProcessInCPU>();
    	
    	//Create new logger for each process
    	for(int i = 0; i < processes.size(); i++) {
    		ProcessInCPU logger = new ProcessInCPU(processes.get(i));
    		loggerMap.put(processes.get(i).getId(), logger);
    	}
    	
        // main loop
        for(int currentTick = 0; currentTick < MAX_LOOP; currentTick++){
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
                // AYAN TODO
                // for all other processes in queue, except for current, increment k_queuing_time: For loop from index 1 to end
                
                if (curIOProcess.isCurServiceOver()) { // I/O service is completed
                    curIOProcess.proceedToNextService();
                    SystemHelper.moveProcessFrom(blockQueueIO, readyQueue);
                    
                    if (!blockQueueIO.isEmpty()) curIOProcess = blockQueueIO.get(0); 
                    else curIOProcess = null;
                }
                
                for (int i = 1; i < blockQueueIO.size(); i++) {
//                	blockQueueIO.get(i).updateQueueingTime();
                	loggerMap.get(blockQueueIO.get(i).getId()).updateQueueingTime();
                }
                if (curIOProcess != null) curIOProcess.incrementCurServiceTick(); // increment the num of ticks that have been spent on current service
                
                
            }

            // CPU scheduling
            if (readyQueue.isEmpty()) {
                prevProcessID = -1; // reset the previous dispatched process ID to empty no process for scheduling
            } else {
            	if (!flag) {
            		int shortestIndex = ProcessInCPU.findShortestServiceNextProcess(readyQueue);
            		Collections.swap(readyQueue, 0, shortestIndex);
            	}
            	ProcessInCPU curProcess = readyQueue.get(0);
            	
                curProcessID = curProcess.getId();
                
                if (curProcessID != prevProcessID) { // store the tick when current process is dispatched
                    dispatchedTick = currentTick;
                }
                
                curProcess.incrementCurServiceTick(); // increment the num of ticks that have been spent on current service
                // AYAN TODO
                // for all other processes in queue, except for current, increment p_queuing_time: For loop from index 1 to end
                for (int i = 1; i < readyQueue.size(); i++) {
//                	readyQueue.get(i).updateQueueingTime();
                	loggerMap.get(readyQueue.get(i).getId()).updateQueueingTime();
                }
                
                if (curProcess.isCurServiceOver()) { // current service is completed
                	flag = false;
                    ManageNextServiceFCFS.manageNextServiceFcfs(curProcess, completeNum, dispatchedTick, currentTick, readyQueue,
                                            completedProcesses, blockQueueIO, loggerMap.get(curProcess.getId())); // look for next service
                }
                
                prevProcessID = curProcessID; // log the previous dispatched process ID
            }
            
            if (completedProcesses.size() == processes.size()) break;
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
