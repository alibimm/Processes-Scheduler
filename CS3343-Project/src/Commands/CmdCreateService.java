package Commands;

import Exceptions.ExInsufficientCommandArguments;
import Project.Command;
import Project.MainSystem;
import Project.Service;

public class CmdCreateService implements Command {
	
	private Project.Service s;

	@Override
	public void execute(String[] cmdParts) {
		
		try {
            if (cmdParts.length < 3) {
                throw new ExInsufficientCommandArguments();
            }
            MainSystem system = MainSystem.getInstance();
            s = system.createService(Double.parseDouble(cmdParts[1]), cmdParts[2]);
            //Add one more argument, serviceType (first in order)
        } catch (ExInsufficientCommandArguments e) {
            System.out.println(e.getMessage());
        }
		
	}
	
	
	
}
