package Exceptions;

public class ExCaseNotFound extends Exception {
	private static final long serialVersionUID = 1L;
	
	public ExCaseNotFound(int id) {
		super(String.format("Case with ID %d was not found", id));
	}
}
