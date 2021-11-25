package Project;

import java.util.ArrayList;

public class Case {
	private static int newid = 0;
	
	private final int id;
	private ArrayList<AlgorithmResult> results;
	
	protected Case(int id, ArrayList<Algorithm> algorithms, ArrayList<Process> processes) {
		this.id = id;
		results = new ArrayList<AlgorithmResult>();
		for (Algorithm algo : algorithms) {
			ArrayList<ProcessInCPU> rawProcessResults = algo.schedule(processes);
			AlgorithmResult algoResult = AlgorithmResult.create(rawProcessResults, algo.getType());
			
			results.add(algoResult);
		}
	}
	
	public static Case create(ArrayList<Algorithm> algorithms, ArrayList<Process> processes) {
		return new Case(newid++, algorithms, processes);
	}
	
	public ArrayList<AlgorithmType> bestAlgorithm(String indicator) {
		switch (indicator) {
		case "AQT":
			return bestAlgorithmAQT();
		case "ATRT":
			return bestAlgorithmATRT();
		case "ARTS":
			return bestAlgorithmARTS();
		case "CpuUtil":
			return bestAlgorithmCpuUtil();
		default: 
			return bestAlgorithmATRT();
		}
	}
	
	private ArrayList<AlgorithmType> bestAlgorithmAQT() {
 		ArrayList<AlgorithmType> bestAlgorithms = new ArrayList<AlgorithmType>();
 		double minAvgQT = results.get(0).getAvgQueuingTime();

 		for (int i = 1; i < results.size(); i++) {
 			AlgorithmResult result = results.get(i);
 			if (result.getAvgQueuingTime() < minAvgQT) {
 				minAvgQT = result.getAvgQueuingTime();
 			}
 		}
 		
 		for (AlgorithmResult result : results) {
 			if (result.getAvgQueuingTime() == minAvgQT) {
 				bestAlgorithms.add(result.getAlgorithmType());
 			}
 		}
 		
 		return bestAlgorithms;
 	}

 	private ArrayList<AlgorithmType> bestAlgorithmATRT() {
 		ArrayList<AlgorithmType> bestAlgorithms = new ArrayList<AlgorithmType>();
 		double minAvgTRT = results.get(0).getAvgTurnaroundTime();

 		for (int i = 1; i < results.size(); i++) {
 			AlgorithmResult result = results.get(i);
 			if (result.getAvgTurnaroundTime() < minAvgTRT) {
 				minAvgTRT = result.getAvgTurnaroundTime();
 			}
 		}

 		for (AlgorithmResult result : results) {
 			if (result.getAvgTurnaroundTime() == minAvgTRT) {
 				bestAlgorithms.add(result.getAlgorithmType());
 			}
 		}

 		return bestAlgorithms;
 	}

 	private ArrayList<AlgorithmType> bestAlgorithmARTS() {
 		ArrayList<AlgorithmType> bestAlgorithms = new ArrayList<AlgorithmType>();
 		double minAvgRTS = results.get(0).getAvgRatioTS();

 		for (int i = 1; i < results.size(); i++) {
 			AlgorithmResult result = results.get(i);
 			if (result.getAvgRatioTS() < minAvgRTS) {
 				minAvgRTS = result.getAvgRatioTS();
 			}
 		}

 		for (AlgorithmResult result : results) {
 			if (result.getAvgRatioTS() == minAvgRTS) {
 				bestAlgorithms.add(result.getAlgorithmType());
 			}
 		}
 		
 		return bestAlgorithms;
 	}

 	private ArrayList<AlgorithmType> bestAlgorithmCpuUtil() {
 		ArrayList<AlgorithmType> bestAlgorithms = new ArrayList<AlgorithmType>();
 		double maxCpuUtil = results.get(0).getCpuUtil();

 		for (int i = 1; i < results.size(); i++) {
 			AlgorithmResult result = results.get(i);
 			if (result.getCpuUtil() > maxCpuUtil) {
 				maxCpuUtil = result.getCpuUtil();
 			}
 		}

 		for (AlgorithmResult result : results) {
 			if (result.getCpuUtil() == maxCpuUtil) {
 				bestAlgorithms.add(result.getAlgorithmType());
 			}
 		}
 		
 		return bestAlgorithms;
 	}
	
	public static Case findCaseWithId(int id, ArrayList<Case> cases) {
		for (Case c : cases) {
			if (c.id == id) return c;
		}
		return null;
	}
	
	public int getId() { return id; }
	
	public ArrayList<AlgorithmResult> getResults() { 
		return results;
	}

	public void printTable() {
		System.out.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s\n", 
				"Type", 
				"Duration",
				"CPU Util",
				"Avg Turnaround",
				"Max Turnaround",
				"Avg Queuing",
				"Max Queuing");
		for (AlgorithmResult res : results) {
			res.printStats();
		}
		System.out.println();
	}
	
	public void printAlgoShort(AlgorithmType algo) {
		System.out.print(String.format("%-15s", ("Case #" + id)));
		for (AlgorithmResult result : results) {
			if (result.getAlgorithmType() == algo) {
				result.printStats();
			}
		}
	}
	
	public void printAlgoDetail(AlgorithmType algo) {
		for (AlgorithmResult result : results) {
			if (result.getAlgorithmType() == algo) {
				result.printDetails();
			}
		}
	}
	
	
}
