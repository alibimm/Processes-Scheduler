package Project;

import java.util.ArrayList;

public class RR extends Algorithm {
	private final AlgorithmType type = AlgorithmType.RR;
	private ArrayList<ProcessInCPU> readyQueue;
	private ArrayList<ProcessInCPU> blockQueueIO;
	private ArrayList<ProcessInCPU> completedProcesses;
	private int dispatchedTick;
	private int curProcessId, prevProcessId;
	
	private RR() {
		readyQueue = new ArrayList<ProcessInCPU>();
		blockQueueIO = new ArrayList<ProcessInCPU>();
		completedProcesses = new ArrayList<ProcessInCPU>();
		dispatchedTick = 0;
		curProcessId = -1;
		prevProcessId = -1;
	}
	
	private static RR instance = new RR();
	public static RR getInstance() { return instance; }
	
	@Override
	public ArrayList<ProcessInCPU> schedule(ArrayList<Process> processes) {
		reset();
		
		// main loop
		for (int tick = 0; tick < Constants.MAX_LOOP; tick++) {
			
			// long term scheduler 
			for (int i = 0; i < processes.size(); i++) {
				if (processes.get(i).getArrivalTime() == tick) {
					ProcessInCPU logger = ProcessInCPU.create(processes.get(i));
                    readyQueue.add(logger);
				}
			}
			
			// keyboard I/O device scheduling
			if (!blockQueueIO.isEmpty()) {
				ProcessInCPU curIOProcess = blockQueueIO.get(0);
				
				if (curIOProcess.isCurServiceOver()) {
					curIOProcess.proceedToNextService();
					ProcessInCPU.moveProcessFrom(blockQueueIO, readyQueue);
				}
				
				if (!blockQueueIO.isEmpty()) blockQueueIO.get(0).incrementCurServiceTick();
                ProcessInCPU.updateQueingTime(blockQueueIO, 1, blockQueueIO.size());
			}
			
			// CPU scheduling
			if (readyQueue.isEmpty()) {
				prevProcessId = -1;
			} else {
				ProcessInCPU curProcess = readyQueue.get(0);
				curProcessId = curProcess.getId();
				if (curProcessId != prevProcessId) {
					dispatchedTick = tick;
				}
				
				curProcess.incrementCurServiceTick();
                ProcessInCPU.updateQueingTime(readyQueue, 1, readyQueue.size());
				
				if (curProcess.isCurServiceOver() || tick + 1 - dispatchedTick >= Constants.CLOCK) {
					manageCurrentCPUProcess(tick);
				}
				
				prevProcessId = curProcessId;
			}
			
			if (completedProcesses.size() == processes.size()) break;
		}
		
		return completedProcesses;
	}
	
	@Override
	protected void manageCurrentCPUProcess(int curTick) {
    	ProcessInCPU process = readyQueue.get(0);
    	process.logWorking(dispatchedTick, curTick + 1);
    	
    	if (process.isCurServiceOver()) {
    		boolean processCompleted = process.proceedToNextService();
            if (processCompleted) {
            	ProcessInCPU.moveProcessFrom(readyQueue, completedProcesses); // remove current process from ready queue
            } else if (process.getCurServiceType() == ServiceType.Keyboard) { 
            	ProcessInCPU.moveProcessFrom(readyQueue, blockQueueIO); // next service is keyboard input, block current process
            }
    	} else {
    		ProcessInCPU.moveProcessFrom(readyQueue, readyQueue);
			dispatchedTick = curTick + 1;
    	}
    }
	
	private void reset() {
		readyQueue.clear();
		blockQueueIO .clear();
		completedProcesses.clear();
		dispatchedTick = 0;
		curProcessId = -1;
		prevProcessId = -1;
	}
	
	@Override
	public AlgorithmType getType() {
		return type;
	}
}
