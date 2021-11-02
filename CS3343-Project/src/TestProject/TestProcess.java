package TestProject;
import java.util.*;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import Commands.CmdReadFile;
import Project.IntervalPair;
import Project.Process;
import Project.Service;
import static org.junit.Assert.assertEquals;

public class TestProcess {
	
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
	public void testArrivalTime() {
		
		Process p = makeprocess();
    	double res = p.getArrivalTime();
    	assertEquals(3.0,res,0.0);
    	
	}
	

	@Test
	public void testId() {
		
		Process p = makeprocess();
    	int res = p.getId();
    	assertEquals(1,res);
    	
	}
	
	@Test
	public void testServices(){
		Process p = makeprocess();
		ArrayList<Service> res = p.getServices();
		assertEquals(services,res);
	}
	
	
	

}
