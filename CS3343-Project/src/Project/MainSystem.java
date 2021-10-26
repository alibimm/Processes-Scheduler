package Project;

import java.util.*;
import java.util.Collections;

public class MainSystem {
	
	private ArrayList<Process> allProcesses;
	private ArrayList<Result> allResults;
	private ArrayList<Algorithm> allAlgorithms;
	
	// Using Singleton to instanciate Main System, to have only one System
    private static MainSystem instance = new MainSystem();
    
    // Constructor
    private MainSystem() {
    	allProcesses = new ArrayList<>();
    	allResults = new ArrayList<>();
    	allAlgorithms = new ArrayList<>();
    }
    
    public static MainSystem getInstance() {
        return instance;
    }
    
//    Creating Algorithms
    
    
//    Creating Processes
    public Process createProcess(String id, double arrivalTime) {
    	Process p = new Process(id, arrivalTime);
    	allProcesses.add(p);
    	return p;
    }
    
//    Get Process
    public Process getProcess(String id) {
    	for (Process p : allProcesses) {
    		if (p.getId().equals(id)) {
    			return p;
    		}
    	}
    }
    
//    Create Service
    public Service createService(double serviceTime, String processId) { // Add one argunment, service type
    	Service s = new Service(serviceTime); // add one argument service type
    	Process p = getProcess(processId);
    	p.getServices().add(s);
    }
    
//    Creating Results
    
	
}
