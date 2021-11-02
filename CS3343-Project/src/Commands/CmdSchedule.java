package Commands;

import Project.Command;
import Project.MainSystem;

public class CmdSchedule implements Command {

	@Override
	public void execute(String[] cmdParts) {
		
		MainSystem system = MainSystem.getInstance();
		system.scheduleAlgorithms();
		System.out.print("processes has been schedule");
		
	}

}
