package Project;
import java.util.ArrayList;

public abstract class Algorithm {
	public abstract ArrayList<ProcessInCPU> schedule(ArrayList<Process> processes);
	protected abstract void manageCurrentCPUProcess(int curTick);
	public String getName() { return "Algortihm"; }
}
