package Project;

import java.util.*;

import Commands.*;
import Exceptions.*;

public class Main {
	public static void main(String[] args) {
		
		// Declaring in scanner to read input from keyboard
        Scanner in = new Scanner(System.in);
        
        String command;
        
        while (true) {
        	System.out.print(">");
        	command = in.nextLine().trim();
        	
        	if (command.equals("exit")) break;
        	
        	String[] cmdParts = command.split(" ");
        	try {
            	if (cmdParts[0].equals("readfile")) {
            		(new CmdReadFile()).execute(cmdParts);
            	} else if (cmdParts[0].equals("schedule")) {
            		(new CmdSchedule()).execute(cmdParts);
            	} else if (cmdParts[0].equals("custom")) {
            		(new CmdCustom()).execute(cmdParts);
            	} else if (cmdParts[0].equals("suggest")) {
            		(new CmdSuggest()).execute(cmdParts);
            	} else if (cmdParts[0].equals("open")) {
            		(new CmdOpen()).execute(cmdParts);
            	} else if (cmdParts[0].equals("close")) {
            		(new CmdClose()).execute(cmdParts);
            	} else if (cmdParts[0].equals("display")) {
            		(new CmdDisplay()).execute(cmdParts);
            	} else if (cmdParts[0].equals("help")) {
            		(new CmdHelp()).execute(cmdParts);
            	} else {
            		throw new ExNonExistingCommand(cmdParts[0]);
            	}
        	} catch (ExNonExistingCommand e) {
        		System.out.println(e.getMessage());
        	} catch (ExInsufficientCommandArguments e) {
        		System.out.println(e.getMessage());
        	}
        	
        	
        	// Something Else
        	
        }
        
        in.close();
	}
}
