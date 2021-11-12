package Project;

import java.util.*;

public class Result {
	private ArrayList<ProcessResult> processes = new ArrayList<ProcessResult>();
	private double avgQueuingTime;
	private double avgTurnaroundTime;
	private double avgRatioTS;
	private double maxQueuingTime;
	private double maxTurnaroundTime;
	private double cpuUtil;
	private double throughput;
	private String algorithmType;
	
	private Result(ArrayList<ProcessInCPU> rawProcessResults, String algoName) {
		algorithmType = algoName;
		processes = ProcessResult.createResultList(rawProcessResults);
		
		double[] queuingTime = ProcessResult.calcMaxAvgQueuingTime(processes);
		maxQueuingTime = queuingTime[0];
		avgQueuingTime = queuingTime[1];
		double[] turnaroundTime = ProcessResult.calcMaxAvgTurnaroundTime(processes);
		maxTurnaroundTime = turnaroundTime[0];
		avgTurnaroundTime = turnaroundTime[1];
		double[] ratioTS = ProcessResult.calcMaxAvgRatioTS(processes);
		avgRatioTS = ratioTS[1];
	}
	public static Result create(ArrayList<ProcessInCPU> processes, String algoName) {
		return new Result(processes, algoName);
	}
	
	
	public double getAvgQueueingTime() {
		return this.avgQueuingTime;
	}
	public double getAvgTurnaroundTime() {
		return this.avgTurnaroundTime;
	}
	public double getavgRatioTS() {
		return this.avgRatioTS;
	}
	public double getmaxQueuingTime() {
		return this.maxQueuingTime;
	}
	public double getMaxTurnaroundTime() {
		return this.maxTurnaroundTime;
	}
	public double getCpuUtil() {
		return this.cpuUtil;
	}
	public double getThroughput() {
		return this.throughput;
	}
	public String getAlgorithmType() {
		return this.algorithmType;
	}
	
	
	public void setAvgQueueingTime(double aqt) {
		this.avgQueuingTime = aqt;
	}
	public void setAvgTurnaroundTime(double att) {
		this.avgTurnaroundTime = att;
	}
	public void setavgRatioTS(double atsr) {
		this.avgRatioTS = atsr;
	}
	public void setmaxQueuingTime(double mqt) {
		this.maxQueuingTime = mqt;
	}
	public void setMaxTurnaroundTime(double mtt) {
		this.maxTurnaroundTime = mtt;
	}
	public void setThroughput(double t) {
		this.throughput = t;
	}
	public void set(String algType) {
		this.algorithmType = algType;
	}
	
	public void printStats() {
		for (ProcessResult process : processes) {
			  process.print();
		      process.printQueueingTime();
		      System.out.println();
		}
	}
}
