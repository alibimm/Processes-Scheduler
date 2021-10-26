package Project;

public class Process {
	private int id;
	private double arrivalTime;
	int cur_service_tick;
	private Service cur_service;
	
	public void proceed_to_next_service() {
		
	}
	
	public double getArrivalTime() {
		return arrivalTime;
	}
	
	public Service getCurService() {
		return cur_service;
	}
	
	public int getProcessID() {
		return id;
	}
}
