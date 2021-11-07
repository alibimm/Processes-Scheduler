package Project;

import java.util.ArrayList;

public class SRT extends Algorithm {
	private static SRT instance = new SRT();
	
	private SRT() {}
	
	public static SRT getInstance() {
		return instance;
	}
	
	@Override
	public ArrayList<ProcessInCPU> schedule(ArrayList<Process> processes) {
		// Add fucntionality
		return null;
	}

	@Override
	protected void manageCurrentCPUProcess(int currentTick) {
		// TODO Auto-generated method stub
		
	}

}
