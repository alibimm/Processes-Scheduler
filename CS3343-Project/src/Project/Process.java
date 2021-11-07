package Project;

import java.util.ArrayList;

public class Process {
	private int id;
	private int arrivalTime;
	private ArrayList<Service> allServices;
	
	// CONSTRUCTOR
	private Process(int id, int arrivalTime, ArrayList<Service> services) {
		this.id = id;
		this.arrivalTime = arrivalTime;
		this.allServices = services;
	}
	public static Process create(int id, int arrivalTime, ArrayList<Service> services) {
		return new Process(id, arrivalTime, services);
	}
	
	// GETTERS
	public double getServiceTime(int index) {
		return allServices.get(index).getServiceTime();
	}
	public ServiceType getServiceType(int index) {
		return allServices.get(index).getType();
	}
	public int getServicesCount() {
		return allServices.size();
	}
	public int getArrivalTime() {
		return arrivalTime;
	}
	public int getId() {
		return id;
	}
}
