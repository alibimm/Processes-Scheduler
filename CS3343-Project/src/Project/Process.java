package Project;

import java.util.*;

public class Process {
	private String id;
	private double arrivalTime;
	private ArrayList<Service> allServices;
	
	private Process(String id, double arrivalTime, ArrayList<Service> services) {
		this.id = id;
		this.arrivalTime = arrivalTime;
		this.allServices = services;
	}
	
	public static Process create(String id, double arrivalTime, ArrayList<Service> services) {
		return new Process(id, arrivalTime, services);
	}
	
	public String getId() {
		return this.id;
	}
	
	public ArrayList<Service> getServices() {
		return this.allServices;
	}
	
	public void setServices(ArrayList<Service> services) {
		this.allServices = services;
	}
}
