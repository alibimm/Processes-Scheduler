package Commands;

import Project.Command;
import Project.MainSystem;

public class CmdDisplay implements Command {

	@Override
	public void execute(String[] cmdParts) {
		MainSystem system = MainSystem.getInstance();
		system.display();
	}
	
}
