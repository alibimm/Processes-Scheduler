package Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import Commands.CmdReadFile;
import Commands.CmdSchedule;

public class Main {
	public static void main(String[] args) throws Exception {
		
		// Declaring in scanner to read input from keyboard
        Scanner in = new Scanner(System.in);
        
        String command;
        do {
        	System.out.print(">");
        	command = in.nextLine().trim();
        	String[] cmdParts = command.split(" ");
        	
        	if (cmdParts[0].equals("readfile")) {
        		(new CmdReadFile()).execute(cmdParts);
        	} else if (cmdParts[0].equals("schedule")) {
        		(new CmdSchedule()).execute(cmdParts);
        	} else {
        		// Exception
        	}
        	
        	// Something Else
        	
        } while (!command.equals("exit"));
        
        in.close();
	}
}
