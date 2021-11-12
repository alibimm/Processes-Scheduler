package Exceptions;

public class ExInvalidServiceType extends Exception{

private static final long serialVersionUID = 1L;
	
	public ExInvalidServiceType() {
		super("Invalid Service Type");
	}
	
	public ExInvalidServiceType(String msg) {
		super(msg);
		System.out.println(msg);
	}
	
}
