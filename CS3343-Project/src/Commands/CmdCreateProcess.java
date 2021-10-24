package Commands;

import Exceptions.ExInsufficientCommandArguments;
import Project.Command;
import Project.MainSystem;

public class CmdCreateProcess implements Command {
	
	private Project.Process p;

	@Override
	public void execute(String[] cmdParts) {
		
		try {
            if (cmdParts.length < 4) {
                throw new ExInsufficientCommandArguments();
            }
            MainSystem system = MainSystem.getInstance();
            p = system.createProcess(cmdParts[1], Double.parseDouble(cmdParts[2]));
        } catch (ExInsufficientCommandArguments e) {
            System.out.println(e.getMessage());
        }
		
	}

}
