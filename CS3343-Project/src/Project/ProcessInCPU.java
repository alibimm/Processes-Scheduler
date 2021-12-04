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
	private ProcessInCPU (Process process) {
		this.process = process;
		servicesCount = process.getServicesCount();
		
		curServiceIndex = 0;
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
		curServiceIndex++;
        this.curServiceTick = 0;
        if (this.curServiceIndex >= this.servicesCount) { // all services are done, process should end
            return true;
        }
        // still requests services
        return false;
	}
	
	public void updateQueueingTime() {
		if (getCurServiceType() == ServiceType.Keyboard) {
			this.queuingTimeIO++;
		} else if (getCurServiceType() == ServiceType.CPU) {
			this.queuingTimeCPU++;
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
	public static void moveProcessFrom(ArrayList<ProcessInCPU> from, ArrayList<ProcessInCPU> to){
    	ProcessInCPU tmp = from.get(0);
    	to.add(tmp);
    	from.remove(0);
    }
	public static void updateQueingTime(ArrayList<ProcessInCPU> processes, int start, int end) {
		for (int i = start; i < end; i++) {
			processes.get(i).updateQueueingTime();
		}
	}
	
	// GETTERS
	public ServiceType getCurServiceType() {
		return process.getServiceType(curServiceIndex);
	}
	private double getCurServiceTime() {
		return process.getServiceTime(curServiceIndex);
	}
	private double getCurServiceRemainingTime() {
		return getCurServiceTime() - curServiceTick;
	}
	private double getResponseRatio() {
		return (queuingTimeCPU + getCurServiceTime()) / getCurServiceTime();
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
}
