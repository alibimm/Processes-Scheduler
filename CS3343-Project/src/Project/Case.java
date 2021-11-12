package Project;

import java.util.ArrayList;

public class Case {
	private ArrayList<AlgorithmResult> results;
	
	private Case(ArrayList<Algorithm> algorithms, ArrayList<Process> processes) {
		for (Algorithm algo : algorithms) {
			ArrayList<ProcessInCPU> rawProcessResults = algo.schedule(processes);
			AlgorithmResult algoResult = AlgorithmResult.create(rawProcessResults, algo.getType());
			
			algoResult.printStats();
			results.add(algoResult);
		}
	}
	
	public static Case create(ArrayList<Algorithm> algorithms, ArrayList<Process> processes) {
		return new Case(algorithms, processes);
	}
}
