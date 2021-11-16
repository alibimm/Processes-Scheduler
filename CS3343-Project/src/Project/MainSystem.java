package Project;

import java.util.ArrayList;
import java.util.HashMap;

import Exceptions.ExInvalidServiceType;

public class MainSystem {
	private static int curInd = -1;
	
	private ArrayList<ArrayList<Process>> allInputs;
	private ArrayList<Case> allCases;
	private ArrayList<Algorithm> allAlgorithms;
	
	// Using Singleton to instantiate Main System, to have only one System
    private static MainSystem instance = new MainSystem();
    
    // Constructor
    private MainSystem() {
    	allInputs = new ArrayList<ArrayList<Process>>();
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
    	allInputs.get(curInd).add(p);
    	return p;
    }
    
//    Create Service
    public Service createService(String type, String serviceTime) throws ExInvalidServiceType {
    	Service s = Service.create(type, serviceTime);
    	return s;
    }

	public boolean scheduleAlgorithms() {
		if (allInputs.size() == 0) return false;
		for (ArrayList<Process> input : allInputs) {
			Case newCase = Case.create(allAlgorithms, input);
			if (newCase != null) {
				allCases.add(newCase);
			}
		}
		allInputs.clear();
		curInd = -1; // BUG TO TRACK: WITHOUT THIS LINE WHEN INPUTS ARE CLEARED SYSTEM WOULD GO OUT OF BOUNDS
		return true;
	}
	
	public HashMap<Integer, ArrayList<AlgorithmType>> suggest() {
		HashMap<Integer, ArrayList<AlgorithmType>> performance = new HashMap<Integer, ArrayList<AlgorithmType>>();
		HashMap<AlgorithmType, Integer> frequencies = new HashMap<AlgorithmType, Integer>();
		int maxFreq = 0;
		for (Case c : allCases) {
			ArrayList<AlgorithmType> best = c.bestAlgorithm();
			for (AlgorithmType algo : best) {
				if (!frequencies.containsKey(algo)) frequencies.put(algo, 0);
				int freq = frequencies.get(algo);
				if (freq + 1 > maxFreq) maxFreq = freq + 1;
				frequencies.put(algo, freq + 1);
			}
			performance.put(c.getId(), best);
		}
		
		System.out.print("The best performing algorithm(s):");
		boolean first = true;
		for (HashMap.Entry<AlgorithmType, Integer> entry : frequencies.entrySet()) {
			AlgorithmType algo = entry.getKey();
		    int freq = entry.getValue();
		    if (freq == maxFreq) {
		    	if (!first) {
		    		System.out.print(",");
		    	} else {
		    		first = false;
		    	}
		    	System.out.print(String.format(" %s", algo.toString()));
		    }
		}
		System.out.println();
		
		return performance;
	}
	
	public void clear() {
		allInputs.clear();
		allCases.clear();
	}
	
	public void startReading() {
		ArrayList<Process> input = new ArrayList<Process>();
		allInputs.add(input);
		curInd++;
	}
	public void stopReading(boolean finished) {
		if (!finished) {
			allInputs.remove(curInd);
			curInd--;
		}
	}
	
	public ArrayList<ArrayList<Process>> getAllInputs() {
		return allInputs;
	}
	
}