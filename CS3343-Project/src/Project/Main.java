package Project;

import java.util.*;

import Commands.*;
import Exceptions.*;

public class Main {
	public static void main(String[] args) throws Exception {
		
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
            	} else if (cmdParts[0].equals("suggest")) {
            		(new CmdSuggest()).execute(cmdParts);
            	} else if (cmdParts[0].equals("open")) {
            		(new )
            	} else {
            		throw new ExNonExistingCommand(cmdParts[0]);
            	}
        	} catch (ExNonExistingCommand e) {
        		System.out.println(e.getMessage());
        	}
        	
        	
        	// Something Else
        	
        }
        
        in.close();
	}
}
