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
    	allAlgorithms.add(FCFS.getInstance()); // First Come First Serve
    	allAlgorithms.add(RR.getInstance()); // Round Robin
    	allAlgorithms.add(FB.getInstance()); // Feedback
    	allAlgorithms.add(SPN.getInstance()); // Shortest Process Next
    	allAlgorithms.add(SRT.getInstance()); // Shortest Remaining Time
    	allAlgorithms.add(HRRN.getInstance()); // Highest Response Ratio Next
    }
    
    public static MainSystem getInstance() {
        return instance;
    }
    
    
    
//    Creating Processes

    public Process createProcess(String id, double arrivalTime, ArrayList<Service> services) {
    	Process p = Process.create(Integer.parseInt(id), arrivalTime, services);
    	allProcesses.add(p);
    	return p;
    }
    
//    Get Process
    public Process getProcess(int id) {
    	for (Process p : allProcesses) {
    		if (p.getId()==id) {
    			return p;
    		}
    	}
    	return null;
    }
    
//    Create Service
    public Service createService(String type, String serviceTime) {
    	Service s = Service.create(type, serviceTime);
    	return s;
    }

	public void scheduleAlgorithms() {
		
		allResults.add(FB.getInstance().schedule(allProcesses));
	}
	
	
    
//    Creating Results
    
	
}