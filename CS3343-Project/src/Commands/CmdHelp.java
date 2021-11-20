package Commands;

import Exceptions.ExInsufficientCommandArguments;
import Exceptions.ExInvalidInput;
import Exceptions.ExInvalidServiceType;
import Project.Command;

public class CmdHelp implements Command {

	@Override
	public void execute(String[] cmdParts) throws ExInvalidServiceType, ExInvalidInput, ExInsufficientCommandArguments {

		System.out.println("There are 8 commands used in various situations:");
		System.out.println("");
		System.out.println("Working area");
		System.out.println("	readfile [filepathname]			Let the scheduler read the file");
		System.out.println("	schedule				Start the scheduling");
		System.out.println("	suggest					To get suggestions on best algorithm according to TurnAround Time for the given files");
		System.out.println("	display					Print the current information");
		System.out.println("	help					Get help for execution and navigation");
		System.out.println("");
		System.out.println("To navigate through given files and algorithms for specific results");
		System.out.println("");
		System.out.println("	open [index of file]			Open the detailed results of the specified file");
		System.out.println("	open [algorithm]			Open the detailed results of the specified algorithm");
		System.out.println("	close					Go back to previous information");
		System.out.println("");
	}
	
}
