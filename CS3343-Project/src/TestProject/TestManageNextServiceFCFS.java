package TestProject;

import java.util.ArrayList;

import org.junit.Test;

import Project.ManageNextServiceFCFS;
import Project.Process;
import Project.ProcessInCPU;
import Project.Service;

public class TestManageNextServiceFCFS {
	
	ArrayList<Service> services = new ArrayList<Service>();
	
	Service s1 = Service.create("C","3");
	Service s2 = Service.create("k","9");
	Service s3 = Service.create("C","5");
	Service s4 = Service.create("K","1");
	Service s5 = Service.create("C","3");
	
	public Process makeprocess() {
		
		services.add(s1);
		services.add(s2);
		services.add(s3);
		services.add(s4);
		services.add(s5);
		
    	Process p = Process.create(1, 3, services);
    	return p;
		
	}
	
	@Test
	public void testing1() {
		Process cur_process= makeprocess() ;
		int complete_num = 0;
		int dispatched_tick = 0;
		int cur_tick = 0;
		ArrayList<Process> ready_queue = null;
		ArrayList<Process> processes_done = null;
		ArrayList<Process> block_queue_K = null;
		ProcessInCPU logger= null;
		
		
		ManageNextServiceFCFS.manageNextServiceFcfs(cur_process, complete_num, dispatched_tick, cur_tick, ready_queue, processes_done, block_queue_K, logger);
		
		
		
		
	}
	
	
}
