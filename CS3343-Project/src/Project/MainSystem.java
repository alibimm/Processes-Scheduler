package Project;

import java.util.ArrayList;

public class MainSystem {
	
	private ArrayList<Process> allProcesses;
	private ArrayList<Case> allCases;
	private ArrayList<Algorithm> allAlgorithms;
	
	// Using Singleton to instantiate Main System, to have only one System
    private static MainSystem instance = new MainSystem();
    
    // Constructor
    private MainSystem() {
    	allProcesses = new ArrayList<Process>();
    	allCases = new ArrayList<Case>();
//    	allResults = new ArrayList<>();
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
    public Process createProcess(String id, int arrivalTime, ArrayList<Service> services) {
    	Process p = Process.create(Integer.parseInt(id), arrivalTime, services);
    	allProcesses.add(p);
    	return p;
    }
    
//    Create Service
    public Service createService(String type, String serviceTime) {
    	Service s = Service.create(type, serviceTime);
    	return s;
    }

	public boolean scheduleAlgorithms() {
		if (allProcesses.size() == 0) return false;
		Case newCase = Case.create(allAlgorithms, allProcesses);
		allCases.add(newCase);
		return true;
	}
	
	public void clear() {
		allProcesses.clear();
	}
    
//    Creating Results
    
	
}