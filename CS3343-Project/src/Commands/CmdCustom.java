package Commands;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import Exceptions.ExInvalidInput;

import java.util.Arrays;

import Project.AlgorithmType;
import Project.Command;
import Project.MainSystem;

public class CmdCustom implements Command {

	@Override
	public void execute(String[] cmdParts) {
	
		MainSystem system = MainSystem.getInstance();
		List<String> options = Arrays.asList(new String[] {"AQT", "ATRT", "ARTS", "CpuUtil"});
		
		try {
			if(system.isCaseEmpty()) {
				throw new ExInvalidInput("Make sure that you input the files and run schedule command before");
			}else {
				if (options.contains(cmdParts[1])) {
				    
					HashMap<Integer, ArrayList<AlgorithmType>> performance = system.suggest(cmdParts[1]);
					system.printcases(performance);	
				}else {
					System.out.print("please enter valid performance measure/n");
				}
		    }
			
		}catch (ExInvalidInput e) {
			
		}
		
			
	}	
}


