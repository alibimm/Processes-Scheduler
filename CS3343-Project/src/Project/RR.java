package Project;

import java.util.ArrayList;

public class RR implements Algorithm {
	private static RR instance = new RR();
	
	private RR() {}
	
	public static RR getInstance() {
		return instance;
	}

	@Override
	public Result schedule(ArrayList<Process> processes) {
//		Add functionality
		return null;
	}
	
}
