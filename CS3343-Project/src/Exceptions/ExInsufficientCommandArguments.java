package Exceptions;

public class ExInsufficientCommandArguments extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ExInsufficientCommandArguments(String msg) {
		super(msg);
	}
}