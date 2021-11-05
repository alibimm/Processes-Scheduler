package Project;

import java.util.*;

import Project.Process;


public class ProcessInCPU {
	private Process process;
	
	int curServiceTick;
	int curServiceIndex;
	final int servicesCount;
	
	private ArrayList<IntervalPair> serviceTimes;
	private double k_queuing_time;
	private double cpu_queuing_time;
	private double turnaroundTime;
	private double queueingTime;
	private double TSRatio;
	
	public ProcessInCPU (Process process) {
		this.process = process;
		this.servicesCount = process.getServicesCount();
		
		this.curServiceIndex=0;
		this.curServiceTick=0;
		
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
	
	public boolean isCurServiceOver() {
		return this.curServiceTick >= this.getCurServiceTime();
	}
	public void incrementCurServiceTick() {
		this.curServiceTick++;
	}
	
	 // Call when current service completed
    // if there are no service left, return true. Otherwise, return false
	public boolean proceedToNextService() {
		this.curServiceIndex++;
        this.curServiceTick = 0;
        if (this.curServiceIndex >= this.servicesCount) { // all services are done, process should end
            return true;
        }
        else
        { // still requests services
//            this.cur_service = this.allServices.get(this.curServiceIndex);
            return false;
        }
	}
	public ServiceType getCurServiceType() {
		return this.process.getServiceType(curServiceIndex);
	}
	public double getCurServiceTime() {
		return this.process.getServiceTime(curServiceIndex);
	}
	
	public int getId() {
		return this.process.getId();
	}
	
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
	
	public void logWorking(int start_tick, int end_tick) {
		IntervalPair pair = new IntervalPair(start_tick, end_tick);
		serviceTimes.add(pair);
	}
	
	public ArrayList<IntervalPair> getServiceTimes() {
		return this.serviceTimes;
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
		if(process.getServiceType(curServiceIndex)==ServiceType.Keyboard) {
			this.k_queuing_time++;
		}
		else if(process.getServiceType(curServiceIndex)==ServiceType.CPU) {
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
		this.turnaroundTime = serviceTimes.get(serviceTimes.size()-1).getEnd() - process.getArrivalTime(); 
		int overallServiceTime = 0;
		for(int i = 0; i < servicesCount; i++) {
			overallServiceTime += process.getServiceTime(i);
		}
		this.TSRatio = this.turnaroundTime / overallServiceTime ;
		System.out.println("Process: " + process.getId());
		System.out.println("Queuing time: " + this.queueingTime);
		System.out.println("Turnaround time: " + this.turnaroundTime);
		System.out.println("TS ratio: " + this.TSRatio);
	}
}
