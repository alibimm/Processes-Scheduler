package Tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import Project.AlgorithmType;
import Project.RR;
import Project.Process;
import Project.ProcessInCPU;
import Project.Service;

public class TestRR {
	@Test
	// Testing getType() method in RR class
	void testGetType() {
		
		//creating an instance of RR class
		RR testRR = RR.getInstance();
		
		//actual result
		AlgorithmType actual = testRR.getType();
		
		//expected result
		AlgorithmType expected = AlgorithmType.RR;
		
		assertEquals(actual, expected);
	}
	
	@Test
	// Testing schedule() method in RR class
	void testSchedule() {
		//creating an instance of RR class
		RR testRR = RR.getInstance();
				
		// Creating  ArrayList of Services to create a Process
		ArrayList<Service> testServices = new ArrayList<Service>();
		testServices.add(Service.create("C", "20"));
		testServices.add(Service.create("K", "20"));
		testServices.add(Service.create("C", "20"));
		
		// Creating Process using its static create method
		Process testProcess1 = Process.create(0, 2, testServices);
		
		ArrayList<Process> processes_arr = new ArrayList<>();
		processes_arr.add(testProcess1);
		
		// Creating  ArrayList of Services to create a Process
		ArrayList<Service> testServices2 = new ArrayList<Service>();
		testServices2.add(Service.create("C", "20"));
		testServices2.add(Service.create("K", "20"));
		testServices2.add(Service.create("C", "20"));
		
		// Creating Process using its static create method
		Process testProcess2 = Process.create(0, 2, testServices2);

		// Creating ProcessInCPU instance using its create() static method
		ProcessInCPU testProcessInCPU = ProcessInCPU.create(testProcess2);
		
		//actual result
		ArrayList<ProcessInCPU> actual = testRR.schedule(processes_arr);
		
		//expected result
		ArrayList<ProcessInCPU> expected = new ArrayList<>();
		expected.add(testProcessInCPU);
		
		assertEquals(actual.get(0).getId(), expected.get(0).getId());
	}
	
	
}
