package Commands;

import Exceptions.ExInsufficientCommandArguments;
import Exceptions.ExCaseNotFound;
import Project.AlgorithmType;
import Project.Command;
import Project.MainSystem;

public class CmdOpen implements Command {

	@Override
	public void execute(String[] cmdParts) throws ExInsufficientCommandArguments {
		if (cmdParts.length < 2) throw new ExInsufficientCommandArguments("Open Command should have more than 2 arguments");
		
		String path = cmdParts[1];
		
		MainSystem system = MainSystem.getInstance();
		boolean success = false;
		boolean openingCase = false;
		
		try {
			switch (path) {
			case "FB":
				success = system.openAlgo(AlgorithmType.FB);
				break;
			case "FCFS":
				success = system.openAlgo(AlgorithmType.FCFS);
				break;
			case "HRRN":
				success = system.openAlgo(AlgorithmType.HRRN);
				break;
			case "RR":
				success = system.openAlgo(AlgorithmType.RR);
				break;
			case "SPN":
				success = system.openAlgo(AlgorithmType.SPN);
				break;
			case "SRT":
				success = system.openAlgo(AlgorithmType.SRT);
				break;
			default:
				int caseId = Integer.parseInt(path);
				success = system.openCase(caseId);
				openingCase = true;
			}
			if (!success) {
				if (openingCase) {
					System.out.println("There is already case open.");
				} else {
					System.out.println("There is already algorithm open.");
				}
			} else {
				system.display();
			}
		} catch (NumberFormatException e) {
			System.out.println("Sorry, there is no such algorithm.");
		} catch (ExCaseNotFound e) {
			System.out.println(e.getMessage());
		}
	}
}
