package Project;

import java.util.ArrayList;

public class AlgorithmResult {
	private ArrayList<ProcessResult> processes = new ArrayList<ProcessResult>();
	private double duration;
	private double avgQueuingTime;
	private double avgTurnaroundTime;
	private double avgRatioTS;
	private double maxQueuingTime;
	private double maxTurnaroundTime;
	private double cpuUtil; 
	private AlgorithmType algorithmType;
	
	protected AlgorithmResult(ArrayList<ProcessInCPU> rawProcessResults, AlgorithmType algoType) {
		algorithmType = algoType;
		processes = ProcessResult.createResultList(rawProcessResults);
		
		duration = processes.get(processes.size() - 1).getExitTime();
		
		double[] queuingTime = ProcessResult.calcMaxAvgQueuingTime(processes);
		maxQueuingTime = queuingTime[0];
		avgQueuingTime = queuingTime[1];
		double[] turnaroundTime = ProcessResult.calcMaxAvgTurnaroundTime(processes);
		maxTurnaroundTime = turnaroundTime[0];
		avgTurnaroundTime = turnaroundTime[1];
		double[] ratioTS = ProcessResult.calcMaxAvgRatioTS(processes);
		avgRatioTS = ratioTS[1];
		
		double occupiedTime = 0;
		for (ProcessResult pr : processes) {
			occupiedTime += pr.getTimeInCPU();
		}
		cpuUtil = occupiedTime / duration;
	}
	public static AlgorithmResult create(ArrayList<ProcessInCPU> processes, AlgorithmType algoName) {
		return new AlgorithmResult(processes, algoName);
	}
	
	public AlgorithmType getAlgorithmType() {
		return algorithmType;
	}
	public double getAvgQueuingTime() {
		return this.avgQueuingTime;
	}
	public double getAvgTurnaroundTime() {
		return this.avgTurnaroundTime;
	}
	public double getAvgRatioTS() {
		return this.avgRatioTS;
	}
	public double getMaxQueuingTime() {
		return this.maxQueuingTime;
	}
	public double getMaxTurnaroundTime() {
		return this.maxTurnaroundTime;
	}
	public double getCpuUtil() {
		return this.cpuUtil;
	}

	public void printStats() {
		System.out.format("%-15s%-15.0f%-15.2f%-15.2f%-15.2f%-15.2f%-15.2f\n", 
				algorithmType.toShort(), 
				duration,
				cpuUtil,
				avgTurnaroundTime,
				maxTurnaroundTime,
				avgQueuingTime,
				maxQueuingTime);
	}
	public void printDetails() {
		System.out.println("---------------------------------------------------");
		System.out.println(algorithmType.toString());
		for (ProcessResult process : processes) {
			process.print();
		}
		System.out.println();
	}
}
