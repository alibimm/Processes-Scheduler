package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Project.IntervalPair;
import Project.MainSystem;
import Project.ProcessInCPU;
import Project.Service;
import Project.Process;

class TestProcessInCPU {

	@Test
	// Testing getProcess() method in ProcessInCPU class
	void testGetProcess() {
		
		// Creating dummy ArrayList of Services to create a Process
		ArrayList<Service> testServices = new ArrayList<Service>();
		
		// Creating Process using its static create method
		Process testProcess = Project.Process.create(0, 5.00, testServices);
		
		// Creating ProcessInCPU instance using its create() static method
		ProcessInCPU testProcessInCPU = ProcessInCPU.create(testProcess);
		
		// Creating result process that is going to be tested with the desirable output
		Process result = testProcessInCPU.getProcess();
		
		assertEquals(result, testProcessInCPU);
	}
	
	@Test
	// Testing setProcess() method in ProcessInCPU class
	void testSetProcess() {
		
		// Creating dummy ArrayList of Services to create a Process
		ArrayList<Service> testServices = new ArrayList<Service>();
		
		// Creating Process using its static create method
		Process testProcess1 = Process.create(0, 5.00, testServices);
		
		// Creating ProcessInCPU instance using its create() static method
		ProcessInCPU testProcessInCPU = ProcessInCPU.create(testProcess1);
		
		// Creating another Process using its static create method
		// to assign it instead of the one previousle assigned with create()
		Process testProcess2 = Process.create(0, 5.00, testServices);
		
		// Assigning the second process
		testProcessInCPU.setProcess(testProcess2);
		
		// Checking if second process is assigned
		assertEquals(testProcessInCPU.getProcess(), testProcess2);
	}
	
	@Test
	// Testing getServiceTimes() method in ProcessInCPU class
	void testGetServiceTimes() {
		
		// Implement getServiceTimes test
		
	}
	
	@Test
	// Testing logWorking() method in ProcessInCPU class
	void testLogWorking() {
		
		// Creating dummy ArrayList of Services to create a Process
		ArrayList<Service> testServices = new ArrayList<Service>();
		
		// Creating Process using its static create method
		Process testProcess1 = Process.create(0, 5.00, testServices);
		
		// Creating ProcessInCPU instance using its create() static method
		ProcessInCPU testProcessInCPU = ProcessInCPU.create(testProcess1);
		
		
		// Executing logWorking() for testProcessInCPU object
		testProcessInCPU.logWorking(0, 5); // Dummy start time and finish time
		
		assertEquals(testProcessInCPU.getServiceTimes().get(0), IntervalPair.create(0, 5));
		
	}
	

}
