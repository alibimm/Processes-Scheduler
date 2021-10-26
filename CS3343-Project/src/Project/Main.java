package Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import Commands.CmdCreateProcess;
import Commands.CmdCreateService;

public class Main {
	public static void main(String[] args) {
		
		// Declaring in scanner to read input from keyboard
        Scanner in = new Scanner(System.in);
        
     // Reading filepath name to the testcases directory
        System.out.print("Please input the file pathname: ");
		String filepathname = in.nextLine();
		
		// Testcases file object
        Scanner inFile = null;
        
        try {

            inFile = new Scanner(new File(filepathname));

            String processNum = inFile.nextLine(); // Input sample_0.txt
            int numberOfProcesses = Integer.parseInt(processNum);
            
            for (int i = 0; i < numberOfProcesses; ++i) {
            	
            	while(inFile.hasNext()) {
                    String cmdLine = inFile.nextLine().trim();
                    
                    
//                    Think About exception 
                    if (cmdLine.equals("")) {
                        continue;
                    }
//                    
                    
                    
                    String[] cmdParts = cmdLine.split(" "); 
                    
                    if (cmdParts[0].equals("#")) {
                    	
                    	(new CmdCreateProcess()).execute(cmdParts);
                    	
                    	for (int j = 0; j < Integer.parseInt(cmdParts[3]); ++j) {
                    		String cmdLineServ = inFile.nextLine().trim();
                    		String[] cmdPartsServ = cmdLineServ.split(" ");
                    		cmdPartsServ[2] = cmdParts[1];
                    		(new CmdCreateService()).execute(cmdPartsServ);
                    	}
                    }
            	}   
            }
         } catch (FileNotFoundException e) {
             System.out.println("File not found!");
         } finally {
             if (inFile != null) {
                 inFile.close();
             }
             in.close();
         }
	}
}
