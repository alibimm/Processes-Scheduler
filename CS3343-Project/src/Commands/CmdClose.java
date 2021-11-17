package Commands;

import Project.Command;
import Project.MainSystem;

public class CmdClose implements Command {

	@Override
	public void execute(String[] cmdParts) {
		
		MainSystem system = MainSystem.getInstance();
		boolean success = system.close();
		
		if (!success) {
			System.out.println("You are already in the root directory");
		}
	}

}
