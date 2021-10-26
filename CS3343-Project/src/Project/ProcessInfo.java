package Project;
import java.util.*;

public class ProcessInfo {
	private double turnaroundTime;
	private double queueingTime;
	private double TSRatio;
	
	public ProcessInfo(double tt, double qt, double tsr) {
		this.turnaroundTime = tt;
		this.queueingTime = qt;
		this.TSRatio = tsr;
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
	
	
}
