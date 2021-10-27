package Project;

import java.util.ArrayList;

public class FB implements Algorithm {
	private static FB instance = new FB();
	
	private FB() {}
	
	public static FB getInstance() {
		return instance;
	}
	
	@Override
	public Result schedule(ArrayList<Process> processes) {
		// add functionality
		return null;
	}

}
