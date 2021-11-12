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
		HashMap<Integer, ArrayList<AlgorithmType>> performance = system.suggest();
		System.out.print(String.format("%-15s%s\n", "Case", "Best algorithms"));
		
		for (HashMap.Entry<Integer, ArrayList<AlgorithmType>> entry : performance.entrySet()) {
		    Integer id = entry.getKey();
		    ArrayList<AlgorithmType> best = entry.getValue();
		    
		    System.out.print(String.format("%-15s", "Case #" + id));
		    boolean first = true;
		    for (AlgorithmType algo : best) {
		    	if (!first) {
		    		System.out.print(", ");
		    	} else {
		    		first = false;
		    	}
		    	System.out.print(String.format("%s", algo.toShort()));
		    }
		    System.out.println();
		}
	}

}
