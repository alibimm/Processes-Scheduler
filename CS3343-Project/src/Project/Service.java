package Project;

public class Service {
	private double serviceTime;
	private ServiceType type;
	
	// CONSTRUCTOR
	private Service(ServiceType type, double serviceTime) {
		this.type = type;
		this.serviceTime = serviceTime; 
	}

	public static Service create(String type, String serviceTime) {
		if (type.equals("C")) {
        	return new Service(ServiceType.CPU, Double.parseDouble(serviceTime));
		} else if (type.equals("K")) {
        	return new Service(ServiceType.Keyboard, Double.parseDouble(serviceTime));
        }
		// To-Do - Throw exception if ServiceType is not C or K
		return new Service(ServiceType.None, 0);
	}
	
	// GETTERS
	public double getServiceTime() {
		return serviceTime;
	}
	public ServiceType getType() {
		return type;
	}
}
