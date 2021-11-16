package Exceptions;

public class ExNonExistingCommand extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public ExNonExistingCommand() {
		super("This command doesn't exist.");
	}
	
	public ExNonExistingCommand(String cmd) {
		super(String.format("Command %s doesn't exist. To see all commands use 'help'.", cmd));
//		String msg = String.format("Command %s doesn't exist. To see all commands use 'help'.", cmd);
//		System.out.println(msg);
	}
}
