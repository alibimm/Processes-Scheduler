package Commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import Project.Command;
import Project.MainSystem;
import Project.Service;

public class CmdReadFile implements Command {
	
	private Project.Process p;
	private Project.Service s;
	Scanner in = new Scanner(System.in);
//	// Singleton
//	private static CmdReadFile instance = new CmdReadFile();
//	private CmdReadFile() {}
//	public static CmdReadFile getInstance() {
//		return instance;
//	}

	@Override
	public void execute(String[] cmdParts) {
		
		// Reading filepath name from user input
		String filepathname = cmdParts[1];
		
		// File object
        Scanner inFile = null;
        
        MainSystem system = MainSystem.getInstance();
        
        try {
        	
        	// Reading File
        	inFile = new Scanner(new File(filepathname));
        	// Placeholder for number of Processes (Read first line of input file)
        	int numberOfProcesses = Integer.parseInt(inFile.nextLine());
        	
        	// Iterating through all the processes in file
        	for (int i = 0; i < numberOfProcesses; ++i) {
        		
        		// Reading process line (e.g. # 0 0 5)
    			String processLine = inFile.nextLine().trim();
    			
    			// Think of some exceptions
//    			if (processLine.equals("")) {
//                    continue;
//                }
    			
    			// Split the Process Line into parts for access
    			String[] processLineParts = processLine.split(" ");
    			
    			// Iterating through all services
    			String[] serviceLineParts;    			
    			ArrayList<Service> services = new ArrayList<>();
    			for (int j = 0; j < Integer.parseInt(processLineParts[3]); ++j) {
    				
    				// Splitting service line into parts
    				serviceLineParts = inFile.nextLine().trim().split(" ");
					s = system.createService(serviceLineParts[0], serviceLineParts[1]);
    				services.add(s);
    			}
    			
    			if (processLineParts[0].equals("#")) {
    				
    				p = system.createProcess(processLineParts[1], Integer.parseInt(processLineParts[2]), services);
    				
    			}
        		
        	}
        	
        	
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } finally {
            if (inFile != null)
            	inFile.close();
            	System.out.print("File has been read and closed");
            	
        }
		
	}

}
