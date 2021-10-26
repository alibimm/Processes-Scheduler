package Project;

import java.util.*;

public class Result {
	private ArrayList<ProcessInCPU> sequence;
	private double avgQueuingTime;
	private HashMap<String, ProcessInfo> proccessesInfo;
	//TODO add all attributes
	
	
	Result(ArrayList<Process> processes){
		for(int i=0; i<processes.size(); i++) {
			ProcessInCPU p = new ProcessInCPU(processes.get(i));
			sequence.add(p);
			//Create processInfo
		}
	}
	
	public void setSequence(HashMap<Integer, ProcessInCPU> logger_map) {
		logger_map.forEach((k, v) -> {
            sequence.add(v);
        });
	}
	
}
