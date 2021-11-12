package Project;

import java.util.ArrayList;

public class ProcessInCPU {
	private Process process;
	
	private int curServiceTick;
	private int curServiceIndex;
	private final int servicesCount;
	
	private ArrayList<Interval> serviceTimes;
	private int queuingTimeIO;
	private int queuingTimeCPU;
	
	// CONSTRUCTOR
	public ProcessInCPU (Process process) {
		this.process = process;
		servicesCount = process.getServicesCount();
		
		setCurServiceIndex(0);
		curServiceTick = 0;
		
		serviceTimes= new ArrayList<Interval>();
		
		queuingTimeIO = 0;
		queuingTimeCPU = 0;
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
		this.setCurServiceIndex(this.getCurServiceIndex() + 1);
        this.curServiceTick = 0;
        if (this.getCurServiceIndex() >= this.servicesCount) { // all services are done, process should end
            return true;
        }
        // still requests services
        return false;
	}
	
	public void updateQueueingTime() {
		if (process.getServiceType(getCurServiceIndex()) == ServiceType.Keyboard) {
			this.queuingTimeIO++;
		} else if (process.getServiceType(getCurServiceIndex()) == ServiceType.CPU) { // TODO: check is this way correct? why access service type outside
			this.queuingTimeCPU++;
		}
		else {
			//commented because nothing is handling this exception
			//TODO add 'catch' somewhere
			//throw new Exception("Service type of current process is None");
		}
	}
	
	public void logWorking(int startTick, int endTick) {
		Interval pair = Interval.create(startTick, endTick);
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
	public static int findShortestRemainingTimeProcess(ArrayList<ProcessInCPU> processes) {
		if (processes.size() == 0) return -1;
		double shortest = processes.get(0).getCurServiceRemainingTime();
		int shortestIndex = 0;
		for (int i = 1; i < processes.size(); i++) {
			if (processes.get(i).getCurServiceRemainingTime() < shortest) {
				shortest = processes.get(i).getCurServiceRemainingTime();
				shortestIndex = i;
			}
		}
		
		return shortestIndex;
	}
	public static int findHighestResponseRatioProcess(ArrayList<ProcessInCPU> processes) {
		if (processes.size() == 0) return -1;
		double highest = processes.get(0).getResponseRatio();
		int highestIndex = 0;
		for (int i = 1; i < processes.size(); i++) {
			if (processes.get(i).getResponseRatio() > highest) {
				highest = processes.get(i).getResponseRatio();
				highestIndex = i;
			}
		}
		
		return highestIndex;
	}
	
	// GETTERS
	public ServiceType getCurServiceType() {
		return process.getServiceType(getCurServiceIndex());
	}
	private double getCurServiceTime() {
		return process.getServiceTime(getCurServiceIndex());
	}
	private double getCurServiceRemainingTime() {
		return getCurServiceTime() - curServiceTick;
	}
	private double getResponseRatio() {
		return (queuingTimeCPU + process.getServiceTime(getCurServiceIndex())) / process.getServiceTime(getCurServiceIndex());
	}
	
	public int getId() {
		return this.process.getId();
	}
	public ArrayList<Interval> getServiceTimes() {
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
	public int getCurServiceIndex() {
		return curServiceIndex;
	}
	public void setCurServiceIndex(int curServiceIndex) {
		this.curServiceIndex = curServiceIndex;
	}
}
