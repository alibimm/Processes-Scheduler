package Project;

public class Service {
	private double serviceTime;
	private ServiceType type;
	
	
	public Service(ServiceType type, double serviceTime) {
		this.type = type;
		this.serviceTime = serviceTime; 
	}
	
	public double getServiceTime() {
		return this.serviceTime;
	}
	
	public ServiceType getType() {
		return this.type;
	}
}
