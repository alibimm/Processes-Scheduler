package Project;

import java.util.ArrayList;

public class ProcessInCPU {
	private Process process;
	
	int curServiceTick;
	int curServiceIndex;
	final int servicesCount;
	
	private ArrayList<IntervalPair> serviceTimes;
	private int queuingTimeIO;
	private int queuingTimeCPU;
	
	// CONSTRUCTOR
	public ProcessInCPU (Process process) {
		this.process = process;
		this.servicesCount = process.getServicesCount();
		
		this.curServiceIndex=0;
		this.curServiceTick=0;
		
		this.serviceTimes= new ArrayList<IntervalPair>();
		
		this.queuingTimeIO=0;
		this.queuingTimeCPU=0;
	}
	public static ProcessInCPU create(Process process) {
		return new ProcessInCPU(process);
	}
	
	// METHODS
	public boolean isCurServiceOver() {
		return curServiceTick >= getCurServiceTime();
	}
	public void incrementCurServiceTick() {
		curServiceTick++;
	}
	
	// Call when current service completed
    // if there are no service left, return true. Otherwise, return false
	public boolean proceedToNextService() {
		this.curServiceIndex++;
        this.curServiceTick = 0;
        if (this.curServiceIndex >= this.servicesCount) { // all services are done, process should end
            return true;
        }
        // still requests services
        return false;
	}
	
	public void updateQueueingTime() {
		if (process.getServiceType(curServiceIndex) == ServiceType.Keyboard) {
			this.queuingTimeIO++;
		} else if (process.getServiceType(curServiceIndex) == ServiceType.CPU) { // TODO: check is this way correct? why access service type outside
			this.queuingTimeCPU++;
		}
		else {
			//commented because nothing is handling this exception
			//TODO add 'catch' somewhere
			//throw new Exception("Service type of current process is None");
		}
	}
	
	public void logWorking(int start_tick, int end_tick) {
		IntervalPair pair = IntervalPair.create(start_tick, end_tick);
		serviceTimes.add(pair);
	}
	
	// STATIC FUNCS
	public static int findShortestServiceNextProcess(ArrayList<ProcessInCPU> processes) {
		if (processes.size() == 0) return -1;
		double shortest = processes.get(0).getCurServiceTime();
		int shortestIndex = 0;
		for (int i = 1; i < processes.size(); i++) {
			if (processes.get(i).getCurServiceTime() < shortest) {
				shortest = processes.get(i).getCurServiceTime();
				shortestIndex = i;
			}
		}

		return shortestIndex;
	}
	
	// GETTERS
	public ServiceType getCurServiceType() {
		return this.process.getServiceType(curServiceIndex);
	}
	private double getCurServiceTime() {
		return this.process.getServiceTime(curServiceIndex);
	}
	public int getId() {
		return this.process.getId();
	}
	public ArrayList<IntervalPair> getServiceTimes() {
		return this.serviceTimes;
	}
	public Process getProcess() {
		return this.process;
	}
	public int getQueuingTimeCPU() {
		return this.queuingTimeCPU;
	}
	public int getQueuingTimeIO() {
		return this.queuingTimeIO;
	}
}
