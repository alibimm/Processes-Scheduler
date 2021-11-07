package Project;

import java.util.ArrayList;

public class HRRN extends Algorithm {
	private static HRRN instance = new HRRN();
	
	private HRRN() {}
	
	public static HRRN getInstance() {
		return instance;
	}

	@Override
	public ArrayList<ProcessInCPU> schedule(ArrayList<Process> processes) {
		// add functionality
		return null;
	}

	@Override
	protected void manageCurrentCPUProcess(int currentTick) {
		// TODO Auto-generated method stub
		
	}

}
