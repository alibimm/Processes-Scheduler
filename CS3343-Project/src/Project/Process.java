package Project;

import java.util.*;

public class Process {
	private int id;
	private double arrivalTime;
	int cur_service_tick;
	int cur_service_idx;
	private Service cur_service;
	private ArrayList<Service> allServices;
//	private double k_queuing_time;
//	private double cpu_queuing_time;
	
	private Process(int id, double arrivalTime, ArrayList<Service> services) {
		this.id = id;
		this.arrivalTime = arrivalTime;
		this.allServices = services;
		this.cur_service_idx=0;
		this.cur_service_tick=0;
//		this.k_queuing_time=0;
//		this.cpu_queuing_time=0;
		this.cur_service = allServices.get(cur_service_idx);
	}
	
	public static Process create(int id, double arrivalTime, ArrayList<Service> services) {
		return new Process(id, arrivalTime, services);
	}
	
	public Service getService(int index) {
		return allServices.get(index);
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
	
	public double getServiceTime(int index) {
		return this.allServices.get(index).getServiceTime();
	}
	
	public boolean isCurServiceOver() {
		return this.cur_service_tick >= this.cur_service.getServiceTime();
	}
	public void incrementCurServiceTick() {
		this.cur_service_tick++;
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

	public double getCurServiceTime() {
		return cur_service.getServiceTime();
	}
	
	public ServiceType getCurServiceType() {
		return cur_service.getType();
	}
	
//	public void updateQueueingTime() {
//		if(this.getCurServiceType()==ServiceType.Keyboard) {
//			this.k_queuing_time++;
//		}
//		else if(this.getCurServiceType()==ServiceType.CPU) {
//			this.cpu_queuing_time++;
//		}
//		else {
//			//commented because nothing is handling this exception
//			//TODO add 'catch' somewhere
//			//throw new Exception("Service type of current process is None");
//		}
//	}
	//TMP 
//	public double getCPUQT() {
//		return this.cpu_queuing_time;
//	}
//	public double getKeybQT() {
//		return this.k_queuing_time;
//	}
}
