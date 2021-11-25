package Project;
import java.util.ArrayList;

public abstract class Algorithm {
	public static int MAX_LOOP = 1000;
	public static int CLOCK = 5;
	public static int PRIORITY_COUNT = 3;
	
	public abstract ArrayList<ProcessInCPU> schedule(ArrayList<Process> processes);
	protected abstract void manageCurrentCPUProcess(int curTick);
	public abstract AlgorithmType getType();
}
