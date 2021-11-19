package Commands;

import java.util.ArrayList;
import java.util.HashMap;

import Project.AlgorithmType;
import Project.Command;
import Project.MainSystem;

public class CmdSuggest implements Command {

	@Override
	public void execute(String[] cmdParts) {
		
		MainSystem system = MainSystem.getInstance();
		HashMap<Integer, ArrayList<AlgorithmType>> performance = system.suggest("ATRT");
		system.printcases(performance);
	}

}
