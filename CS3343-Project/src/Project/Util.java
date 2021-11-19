package Project;

import java.util.ArrayList;

public class Util {
	private Util() {}
	public static void moveProcessFrom(ArrayList<ProcessInCPU> from, ArrayList<ProcessInCPU> to){
    	ProcessInCPU tmp = from.get(0);
    	to.add(tmp);
    	from.remove(0);
    }
	
	public static void updateQueingTime(ArrayList<ProcessInCPU> processes, int start, int end) {
		for (int i = start; i < end; i++) {
			processes.get(i).updateQueueingTime();
		}
	}
}
