package Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import Exceptions.ExCaseNotFound;
import Exceptions.ExInvalidServiceType;

public class MainSystem {
	private static int curInd = -1;
	
	private String bestIndicator;
	private List<String> indicatorOptions;
	private Case openCase;
	private AlgorithmType openAlgoType;
	
	private ArrayList<ArrayList<Process>> allInputs;
	private ArrayList<Case> allCases;
	private ArrayList<Algorithm> allAlgorithms;
	
	// Using Singleton to instantiate Main System, to have only one System
    private static MainSystem instance = new MainSystem();
    
    // Constructor
    private MainSystem() {
    	allInputs = new ArrayList<ArrayList<Process>>();
    	allCases = new ArrayList<Case>();
    	allAlgorithms = new ArrayList<Algorithm>();
    	
    	allAlgorithms.add(FCFS.getInstance()); // First Come First Serve
    	allAlgorithms.add(RR.getInstance()); // Round Robin
    	allAlgorithms.add(FB.getInstance()); // Feedback
    	allAlgorithms.add(SPN.getInstance()); // Shortest Process Next
    	allAlgorithms.add(SRT.getInstance()); // Shortest Remaining Time
    	allAlgorithms.add(HRRN.getInstance()); // Highest Response Ratio Next
    	
    	openCase = null;
    	openAlgoType = AlgorithmType.None;
    	bestIndicator = "ATRT";
    	indicatorOptions = Arrays.asList(new String[] {"AQT", "ATRT", "ARTS", "CpuUtil"});
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
    public Service createService(String type, String serviceTime) {
    	Service s = Service.create(type, serviceTime);
    	return s;
    }

	public int scheduleAlgorithms() {
		int inputsCount = allInputs.size();
		if (inputsCount == 0) return 0;
		
		for (ArrayList<Process> input : allInputs) {
			Case newCase = Case.create(allAlgorithms, input);
			if (newCase != null) {
				allCases.add(newCase);
			}
		}
		allInputs.clear();
		curInd = -1; // BUG TO TRACK: WITHOUT THIS LINE WHEN INPUTS ARE CLEARED SYSTEM WOULD GO OUT OF BOUNDS
		return inputsCount;
	}
	
	public boolean openAlgo(AlgorithmType type) {
		if (openAlgoType != AlgorithmType.None) return false;
		openAlgoType = type;
		return true;
	}
	
	public boolean openCase(int id) throws ExCaseNotFound {
		if (openCase != null) return false;
		openCase = Case.findCaseWithId(id, allCases);
		if (openCase == null) throw new ExCaseNotFound(id);
		return true;
	}
	
	public boolean close() {
		if (openAlgoType != AlgorithmType.None) {
			openAlgoType = AlgorithmType.None;
		} else {
			if (openCase != null) {
				openCase = null;
			} else {
				return false;
			}
		}
		return true;
	}
	
	public void display() {
		if (openCase != null) {
			if (openAlgoType != AlgorithmType.None) {
				openCase.printAlgoDetail(openAlgoType);
			} else {
				openCase.printTable();
			}
		} else {
			if (openAlgoType != AlgorithmType.None) {
				System.out.println(String.format("Current algorithm filter: %s", openAlgoType.toString()));
				System.out.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s\n", 
						"Case", 
						"Type",
						"Duration",
						"CPU Util",
						"Avg Turnaround",
						"Max Turnaround",
						"Avg Queuing",
						"Max Queuing");
				for (Case c : allCases) {
					c.printAlgoShort(openAlgoType);
				}
			} else {
				System.out.println("Scheduled cases:");
				for (Case c : allCases) {
					System.out.println(String.format("Case %d", c.getId()));
				}
				System.out.println(String.format("Unscheduled files: %d", allInputs.size()));
			}
		}
	}
	
	public HashMap<Integer, ArrayList<AlgorithmType>> suggest() {
		HashMap<Integer, ArrayList<AlgorithmType>> performance = new HashMap<Integer, ArrayList<AlgorithmType>>();
		HashMap<AlgorithmType, Integer> frequencies = new HashMap<AlgorithmType, Integer>();
		int maxFreq = 0;
		for (Case c : allCases) {
			ArrayList<AlgorithmType> best = c.bestAlgorithm(bestIndicator);
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
	
	public boolean changeBestIndicator(String indicator) {
		if (!indicatorOptions.contains(indicator)) return false;
		bestIndicator = indicator;
		return true;
	}
	
	public void clear() {
		openCase = null;
		openAlgoType = AlgorithmType.None;
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