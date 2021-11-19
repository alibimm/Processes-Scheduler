package Commands;

import Project.Command;
import Project.MainSystem;

public class CmdSchedule implements Command {

	@Override
	public void execute(String[] cmdParts) {
		
		MainSystem system = MainSystem.getInstance();
		int inputsScheduled = system.scheduleAlgorithms();
		
		if (inputsScheduled > 0) {
			System.out.println(String.format("%d unscheduled inputs scheduled successfully.", inputsScheduled));
		} else {
			System.out.println("There are no unscheduled inputs.");
		}
		
	}

}
