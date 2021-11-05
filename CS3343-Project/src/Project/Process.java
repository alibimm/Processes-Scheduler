package Project;

import java.util.*;

public class Process {
	private int id;
	private double arrivalTime;
	private ArrayList<Service> allServices;
	
	private Process(int id, double arrivalTime, ArrayList<Service> services) {
		this.id = id;
		this.arrivalTime = arrivalTime;
		this.allServices = services;
	}
	
	public static Process create(int id, double arrivalTime, ArrayList<Service> services) {
		return new Process(id, arrivalTime, services);
	}
	
	
	public double getServiceTime(int index) {
		return this.allServices.get(index).getServiceTime();
	}
	public ServiceType getServiceType(int index) {
		return this.allServices.get(index).getType();
	}
	public int getServicesCount() {
		return this.allServices.size();
	}
	
	public double getArrivalTime() {
		return arrivalTime;
	}
	
	public int getId() {
		return id;
	}
	
	public void setServices(ArrayList<Service> services) {
		this.allServices = services;
	}
}
