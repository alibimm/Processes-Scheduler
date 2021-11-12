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
		return new Case(newid++, algorithms, processes);
	}
	
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
