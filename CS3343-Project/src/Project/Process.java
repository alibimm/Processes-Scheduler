package Project;

import java.util.*;

public class Process {
	private int id;
	private double arrivalTime;
	int cur_service_tick;
	int cur_service_idx;
	private Service cur_service;
	private ArrayList<Service> allServices;
	
	public Process(int id, double arrivalTime) {
		this.id = id;
		this.arrivalTime = arrivalTime;
	}
	
	 // Call when current service completed
    // if there are no service left, return true. Otherwise, return false
	public boolean proceedToNextService() {
		this.cur_service_idx++;
        this.cur_service_tick = 0;
        if (this.cur_service_idx >= this.allServices.size())
        { // all services are done, process should end
            return true;
        }
        else
        { // still requests services
            this.cur_service = this.allServices.get(this.cur_service_idx);
            return false;
        }
		
	}
	
	public double getArrivalTime() {
		return arrivalTime;
	}
	
	public Service getCurService() {
		return cur_service;
	}
	
	public int getId() {
		return id;
	}
	
	public ArrayList<Service> getServices() {
		return this.allServices;
	}
	
	public void setServices(ArrayList<Service> services) {
		this.allServices = services;
	}

	public double getCurSurviceTime() {
		return cur_service.getServiceTime();
	}
	
	public ServiceType getCurServiceType() {
		return cur_service.getType();
	}
}
