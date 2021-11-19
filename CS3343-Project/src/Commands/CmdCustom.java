package Commands;

import Exceptions.ExInsufficientCommandArguments;
import Project.Command;
import Project.MainSystem;

public class CmdCustom implements Command {

	@Override
 	public void execute(String[] cmdParts) throws ExInsufficientCommandArguments {
 		if (cmdParts.length < 2) throw new ExInsufficientCommandArguments("Custom Command should have 2 arguments");

 		MainSystem system = MainSystem.getInstance();
 		boolean success = system.changeBestIndicator(cmdParts[1]);
 		
 		if (success) {
 			System.out.println(String.format("Changed the best algorithm indicator to %s.", cmdParts[1]));
 		} else {
 			System.out.println("The is no such indicator for the best algorithm, please use any of: AQT, ATRT, ARTS, CpuUtil.");
 		}
 	}	
}