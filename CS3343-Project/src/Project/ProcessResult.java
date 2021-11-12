package Project;

import java.util.ArrayList;

public class ProcessResult {
	private final Process process;
	private final ArrayList<IntervalPair> serviceIntervals;
	private final int exitTime;
	private final int serviceTime;
	private final int turnaroundTime;
	private final int queuingTime;
	private final int queuingTimeCPU;
	private final int queuingTimeIO;
	private double ratioTS;
	
	private ProcessResult(ProcessInCPU processInCPU) {
		process = processInCPU.getProcess();
		serviceIntervals = processInCPU.getServiceTimes();
		exitTime = serviceIntervals.get(serviceIntervals.size() - 1).getEnd();
		
		queuingTimeCPU = processInCPU.getQueuingTimeCPU();
		queuingTimeIO = processInCPU.getQueuingTimeIO();
		queuingTime = queuingTimeIO + queuingTimeCPU;
		
		int acc = 0;
		for (int i = 0; i < process.getServicesCount(); i++) {
			acc += this.process.getServiceTime(i);
		}
		serviceTime = acc;
		turnaroundTime = exitTime - process.getArrivalTime(); 
		ratioTS = turnaroundTime / serviceTime;
	}
	
	public ProcessResult create(ProcessInCPU process) {
		return new ProcessResult(process);
	}
	
	public static ArrayList<ProcessResult> createResultList(ArrayList<ProcessInCPU> processes) {
		ArrayList<ProcessResult> results = new ArrayList<ProcessResult>(); 
		for (ProcessInCPU rawProcess : processes) {
			results.add(new ProcessResult(rawProcess));
		}
		return results;
	}
	public static double[] calcMaxAvgQueuingTime(ArrayList<ProcessResult> processes) {
		double[] result = new double[2];
		int accumulator = 0;
		for (ProcessResult process : processes) {
			if (process.queuingTime > result[0]) result[0] = process.queuingTime;
			accumulator += process.queuingTime;
		}
		result[1] = (double) accumulator / processes.size();
		return result;
	}
	public static double[] calcMaxAvgTurnaroundTime(ArrayList<ProcessResult> processes) {
		double[] result = new double[2];
		int accumulator = 0;
		for (ProcessResult process : processes) {
			if (process.turnaroundTime > result[0]) result[0] = process.turnaroundTime;
			accumulator += process.turnaroundTime;
		}
		result[1] = (double) accumulator / processes.size();
		return result;
	}
	public static double[] calcMaxAvgRatioTS(ArrayList<ProcessResult> processes) {
		double[] result = new double[2];
		int accumulator = 0;
		for (ProcessResult process : processes) {
			if (process.ratioTS > result[0]) result[0] = process.ratioTS;
			accumulator += process.ratioTS;
		}
		result[1] = (double) accumulator / processes.size();
		return result;
	}
 	
	public void print() {
	    System.out.println("Process " + process.getId());
	    for (IntervalPair pair : serviceIntervals) {
	    	pair.print();
	    }
	    System.out.println();
	}
	
	public void printQueueingTime() {
	    System.out.println("CPU Queuing Time: " + queuingTimeCPU);
	    System.out.println("Keyboard Queuing Time: " + queuingTimeIO);
	}
}
