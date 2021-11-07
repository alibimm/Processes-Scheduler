package Project;

import java.util.*;

public class Result {
	private ArrayList<ProcessResult> processes = new ArrayList<ProcessResult>();
	private double avgQueuingTime;
	private double avgTurnaroundTime;
	private double avgTSRatio;
	private double maxQueueingTime;
	private double maxTurnaroundTime;
	private double cpuUtil;
	private double throughput;
	private String alghorithmType;
	
	
	private Result(ArrayList<ProcessInCPU> rawProcessResults) {
		processes = ProcessResult.createResultList(rawProcessResults);
		
	}
	public static Result create(ArrayList<ProcessInCPU> processes) {
		return new Result(processes);
	}
	
	
	public double getAvgQueueingTime() {
		return this.avgQueuingTime;
	}
	public double getAvgTurnaroundTime() {
		return this.avgTurnaroundTime;
	}
	public double getAvgTSRatio() {
		return this.avgTSRatio;
	}
	public double getMaxQueueingTime() {
		return this.maxQueueingTime;
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
	public String getAlghorithmType() {
		return this.alghorithmType;
	}
	
	
	public void setAvgQueueingTime(double aqt) {
		this.avgQueuingTime = aqt;
	}
	public void setAvgTurnaroundTime(double att) {
		this.avgTurnaroundTime = att;
	}
	public void setAvgTSRatio(double atsr) {
		this.avgTSRatio = atsr;
	}
	public void setMaxQueueingTime(double mqt) {
		this.maxQueueingTime = mqt;
	}
	public void setMaxTurnaroundTime(double mtt) {
		this.maxTurnaroundTime = mtt;
	}
	public void setThroughput(double t) {
		this.throughput = t;
	}
	public void set(String algType) {
		this.alghorithmType = algType;
	}
	
	public void printStats() {
		for (ProcessResult process : processes) {
			  process.print();
		      process.printQueueingTime();
		}
	}
}
