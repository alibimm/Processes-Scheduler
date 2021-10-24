package Project;

import java.util.*;

public class Process {
	private String id;
	private double arrivalTime;
	private ArrayList<Service> allServices;
	
	public Process(String id, double arrivalTime) {
		this.id = id;
		this.arrivalTime = arrivalTime;
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
