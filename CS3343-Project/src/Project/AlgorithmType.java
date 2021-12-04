package Project;

public enum AlgorithmType {
	FCFS("First Come First Serve", "FCFS"),
	RR("Round Robin", "RR"),
	FB("Feedback", "FB"),
	SPN("Shortest Process Next", "SPN"),
	SRT("Shortest Remaining Time Next", "SRT"),
	HRRN("Highest Response Ratio Next", "HRRN"),
	None("None", "None");
	
	private final String name;
	private final String shortName;
	
	private AlgorithmType(String name, String shortName) {
		this.name = name;
		this.shortName = shortName;
	}
	
	public String toShort() { return shortName; }
	
	public String toString() { return name; }
}
