package Project;

public enum AlgorithmType {
	FCFS("First Come First Serve"),
	RR("Round Robin"),
	FB("Feedback"),
	SPN("Shortest Process Next"),
	SRT("Shortest Remaining Time Next"),
	HRRN("Highest Response Ratio Next"),
	None("None");
	
	private final String name;
	
	private AlgorithmType(String name) {
		this.name = name;
	}
	
	public String toString() { return name; }
}
