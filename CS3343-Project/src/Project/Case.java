package Project;

import java.util.ArrayList;

public class Case {
	private static int newid = 0;
	
	private final int id;
	private ArrayList<AlgorithmResult> results;
	
	private Case(int id, ArrayList<Algorithm> algorithms, ArrayList<Process> processes) {
		this.id = id;
		results = new ArrayList<AlgorithmResult>();
		for (Algorithm algo : algorithms) {
			ArrayList<ProcessInCPU> rawProcessResults = algo.schedule(processes);
			AlgorithmResult algoResult = AlgorithmResult.create(rawProcessResults, algo.getType());
			
			results.add(algoResult);
		}
		printTable();
	}
	
	public static Case create(ArrayList<Algorithm> algorithms, ArrayList<Process> processes) {
		Case newCase;
		try {
			newCase = new Case(newid++, algorithms, processes);
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
			newCase = null;
		}
		return newCase;
	}
	
	public ArrayList<AlgorithmType> bestAlgorithmAQT() {
		
		ArrayList<AlgorithmType> bestAlgorithms = new ArrayList<AlgorithmType>();
		double minAvgQT = results.get(0).getAvgQueueingTime();
		
		for (int i = 1; i < results.size(); i++) {
			AlgorithmResult result = results.get(i);
			if (result.getAvgQueueingTime() < minAvgQT) {
				minAvgQT = result.getAvgQueueingTime();
			}
		}
		
		for (AlgorithmResult result : results) {
			if (result.getAvgQueueingTime() == minAvgQT) {
				bestAlgorithms.add(result.getAlgorithmType());
			}
		}
		return bestAlgorithms;
	}
	
	public ArrayList<AlgorithmType> bestAlgorithmATRT() {
		ArrayList<AlgorithmType> bestAlgorithms = new ArrayList<AlgorithmType>();
		double minAvgATRT = results.get(0).getAvgTurnaroundTime();
		
		for (int i = 1; i < results.size(); i++) {
			AlgorithmResult result = results.get(i);
			if (result.getAvgTurnaroundTime() < minAvgATRT) {
				minAvgATRT = result.getAvgTurnaroundTime();
			}
		}
		
		for (AlgorithmResult result : results) {
			if (result.getAvgTurnaroundTime() == minAvgATRT) {
				bestAlgorithms.add(result.getAlgorithmType());
			}
		}
		
		return bestAlgorithms;
		
	}
	
	public ArrayList<AlgorithmType> bestAlgorithmARTS() {
		ArrayList<AlgorithmType> bestAlgorithms = new ArrayList<AlgorithmType>();
		double minAvgRTS = results.get(0).getavgRatioTS();
		
		for (int i = 1; i < results.size(); i++) {
			AlgorithmResult result = results.get(i);
			if (result.getavgRatioTS() < minAvgRTS) {
				minAvgRTS = result.getavgRatioTS();
			}
		}
		
		for (AlgorithmResult result : results) {
			if (result.getavgRatioTS() == minAvgRTS) {
				bestAlgorithms.add(result.getAlgorithmType());
			}
		}
		return bestAlgorithms;
		
	}
	
	public ArrayList<AlgorithmType> bestAlgorithmCpuUtil() {
		ArrayList<AlgorithmType> bestAlgorithms = new ArrayList<AlgorithmType>();
		
		double minCpuUtil = results.get(0).getCpuUtil();
		
		for (int i = 1; i < results.size(); i++) {
			AlgorithmResult result = results.get(i);
			if (result.getCpuUtil() < minCpuUtil) {
				minCpuUtil = result.getCpuUtil();
			}
		}
		
		for (AlgorithmResult result : results) {
			if (result.getCpuUtil() == minCpuUtil) {
				bestAlgorithms.add(result.getAlgorithmType());
			}
		}
		return bestAlgorithms;
		
	}
	
	public int getId() { return id; }
	
	public void printTable() {
		System.out.format("%-30s%-15s%-15s%-15s%-15s\n", 
				"Type", 
				"Duration",
				"CPU Util",
				"Avg Turnaround",
				"Avg Queuing");
		for (AlgorithmResult res : results) {
			res.printStats();
		}
		System.out.println();
		
		for (AlgorithmResult res : results) {
			res.printDetails();
		}
	}
}
