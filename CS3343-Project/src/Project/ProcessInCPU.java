package Project;

import java.util.*;


public class ProcessInCPU {
	private Process process;
	private ArrayList<IntervalPair> serviceTimes;
	
	ProcessInCPU(Process process){
		this.process=process;
		this.serviceTimes= new ArrayList<IntervalPair>();
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
}
