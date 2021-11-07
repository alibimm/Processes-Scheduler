package Project;
import java.util.ArrayList;

public abstract class Algorithm {
	public abstract Result schedule(ArrayList<Process> processes);
	protected abstract void manageCurrentCPUProcess(int currentTick);
}
