package Project;

import java.util.ArrayList;

public class HRRN implements Algorithm{
	private static HRRN instance = new HRRN();
	
	private HRRN() {}
	
	public static HRRN getInstance() {
		return instance;
	}

	@Override
	public Result schedule(ArrayList<Process> processes) {
		// add functionality
		return null;
	}

}
