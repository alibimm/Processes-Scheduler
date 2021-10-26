package Commands;

import Exceptions.ExInsufficientCommandArguments;
import Project.Command;
import Project.MainSystem;
import Project.Service;
import Project.ServiceType;

public class CmdCreateService implements Command {
	
	private Project.Service s;

	@Override
	public void execute(String[] cmdParts) {
		
		try {
            if (cmdParts.length < 3) {
                throw new ExInsufficientCommandArguments();
            }
            MainSystem system = MainSystem.getInstance();
            
            if (cmdParts[0] == "C") 
            	s = system.createService(ServiceType.CPU, Double.parseDouble(cmdParts[1]), cmdParts[2]);
            else if (cmdParts[0] == "K")
            	s = system.createService(ServiceType.Keyboard, Double.parseDouble(cmdParts[1]), cmdParts[2]);
            
        } catch (ExInsufficientCommandArguments e) {
            System.out.println(e.getMessage());
        }
		
	}
	
	
	
}
