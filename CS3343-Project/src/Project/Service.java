package Project;

public class Service {
	private double serviceTime;
	private ServiceType type;
	
	
	private Service(ServiceType type, double serviceTime) {
		this.type = type;
		this.serviceTime = serviceTime; 
	}
  
	public double getServiceTime() {
		return this.serviceTime;
	}
	
	public ServiceType getType() {
		return this.type;
  }
  
	public static Service create(String type, String serviceTime) {
		if (type == "C") 
        	return new Service(ServiceType.CPU, Double.parseDouble(serviceTime));
        else if (type == "K")
        	return new Service(ServiceType.Keyboard, Double.parseDouble(serviceTime));
        
		return new Service(ServiceType.None, 0);
	}
}
