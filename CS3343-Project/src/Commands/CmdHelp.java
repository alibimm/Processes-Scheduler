package Commands;

import java.util.ArrayList;

import Exceptions.ExInsufficientCommandArguments;
import Exceptions.ExInvalidInput;
import Exceptions.ExInvalidServiceType;
import Project.Command;

public class CmdHelp implements Command {
	
	@Override
	public void execute(String[] cmdParts) {
		
		String[] work_commands = new String[] {"readfile [filepathname]",
											"schedule",
											"suggest",
											"custom [indicator]",
											"display",
											"help",
											"exit"};
		String[] work_commands_descriptions = new String[] {"Let the scheduler read the file",
				"Start the scheduling",
				"To get suggestions on best algorithm according to currently set indicator for the given files.\n"
				+ "				(default: Turnaround time)",
				"To get suggestions on best algorithm according to custom indicators for the given files.\n"
				+ "		  	    Available indicators:\n"
				+ "				[AQT] - Average Queueing Time\n"
				+ "				[ATRT] - Average TurnAround Time\n"
				+ "				[ARTS] - Average Ratio TS (Turnaround/Service)\n"
				+ "				[CpuUtil] - CPU Utilization",
				"Print the current information",
				"Get help for execution and navigation",
				"Stop execution of the application\n"};
		
		System.out.println("There are 10 commands used in various situations:\n");
		for (int i = 0; i < work_commands.length; ++i) {
			System.out.println(String.format("  %-25s%-50s", work_commands[i], work_commands_descriptions[i]));
		}
		
		String[] navigation_commands = new String[] {"open [index of case]", 
													"open [algorithm]",
													"close"};
		String[] navigation_commands_descriptions = new String[] {"Open the detailed results of the specified file", 
														"Open the detailed results of the specified algorithm",
														"Go back to previous information"};
		
		System.out.println("\nTo navigate through given files and algorithms for specific results\n");
		for (int i = 0; i < navigation_commands.length; ++i) {
			System.out.println(String.format("  %-25s%-50s", navigation_commands[i], navigation_commands_descriptions[i]));
		}
		System.out.println();
		
	}
	
}
