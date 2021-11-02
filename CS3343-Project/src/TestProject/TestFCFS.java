package TestProject;

import java.util.ArrayList;

import org.junit.Test;

import com.sun.net.httpserver.Authenticator.Result;

import Project.FCFS;
import Project.MainSystem;
import Project.Process;
import Project.Service;
import Project.Result

public class TestFCFS {
	
	@Test
	public void test1(){
		
		MainSystem system = MainSystem.getInstance();
		ArrayList<Process> allprocesses = system.getProcesses();
    	Project.Result res = FCFS.getInstance().schedule(allprocesses);
    	
    	

	}
	
	


}
