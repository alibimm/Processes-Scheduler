package Project;

import java.util.*;


public class ProcessInCPU {
	private Process process;
	private ArrayList<IntervalPair> serviceTimes;
	private double k_queuing_time;
	private double cpu_queuing_time;
	private double turnaroundTime;
	private double queueingTime;
	private double TSRatio;
	
	ProcessInCPU(Process process){
		this.process=process;
		this.serviceTimes= new ArrayList<IntervalPair>();
		this.k_queuing_time=0;
		this.cpu_queuing_time=0;
		this.turnaroundTime = 0;
		this.queueingTime = 0;
		this.TSRatio = 0;
	}
	
	public static ProcessInCPU create(Process process) {
		return new ProcessInCPU(process);
	}
	
	public void logWorking(int start_tick, int end_tick) {
		IntervalPair pair = new IntervalPair(start_tick, end_tick);
		serviceTimes.add(pair);
	}
	
	public Process getProcess() {
		return this.process;
	}
	public void setProcess(Process p) {
		this.process = p;
	}
	
	public void print() {
	    System.out.println("Process " + process.getId());
	    for(IntervalPair pair : serviceTimes) {
	      pair.print();
	    }
	  }
	
	public void printQueueingTime() {
	    System.out.println("Process " + process.getId());
	    System.out.println("CPU Queuing Time: " + this.getCPUQT());
	    System.out.println("Keyboard Queuing Time: " + this.getKeybQT());
	  }
	
	public void updateQueueingTime() {
		if(process.getCurServiceType()==ServiceType.Keyboard) {
			this.k_queuing_time++;
		}
		else if(process.getCurServiceType()==ServiceType.CPU) {
			this.cpu_queuing_time++;
		}
		else {
			//commented because nothing is handling this exception
			//TODO add 'catch' somewhere
			//throw new Exception("Service type of current process is None");
		}
	}
	
	public double getCPUQT() {
		return this.cpu_queuing_time;
	}
	public double getKeybQT() {
		return this.k_queuing_time;
	}
	
	public double getTurnaroundTime() {
		return this.turnaroundTime;
	}
	public double getQueueingTime() {
		return this.queueingTime;
	}
	public double getTSRatio() {
		return this.TSRatio;
	}
	
	public void setTurnaroundTime(double tt) {
		this.turnaroundTime = tt;
	}
	public void setQueueingTime(double qt) {
		this.queueingTime = qt;
	}
	public void setTSRatio(double tsr) {
		this.TSRatio = tsr;
	}
	
	public void calculateStats() {
		this.queueingTime = this.k_queuing_time + this.cpu_queuing_time;
		this.turnaroundTime = serviceTimes.get(serviceTimes.size()-1).getEnd() - serviceTimes.get(0).getStart();
		int overallServiceTime = 0;
		for(int i=0; i<process.getServices().size(); i++) {
			overallServiceTime+=process.getServices().get(i).getServiceTime();
		}
		this.TSRatio = this.turnaroundTime / overallServiceTime ;
		System.out.println("Process: " + process.getId());
		System.out.println("Queuing time: " + this.queueingTime);
		System.out.println("Turnaround time: " + this.turnaroundTime);
		System.out.println("TS ratio: " + this.TSRatio);
	}
}
