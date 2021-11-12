package Tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertArrayEquals;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.Assert.*;
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
		Process testProcess = Process.create(0, 5, testServices);
		
		// Creating ProcessInCPU instance using its create() static method
		ProcessInCPU testProcessInCPU = ProcessInCPU.create(testProcess);
		
		// Creating result process that is going to be tested with the desirable output
		Process result = testProcessInCPU.getProcess();
		
		assertEquals(result, testProcess);
	}
	
	@Test
	// Testing setProcess() method in ProcessInCPU class
	void testSetProcess() {
		
		// Creating dummy ArrayList of Services to create a Process
		ArrayList<Service> testServices = new ArrayList<Service>();
		
		// Creating Process using its static create method
		Process testProcess1 = Process.create(0, 5, testServices);
		
		// Creating ProcessInCPU instance using its create() static method
		ProcessInCPU testProcessInCPU = ProcessInCPU.create(testProcess1);
		
		// Creating another Process using its static create method
		// to assign it instead of the one previousle assigned with create()
		Process testProcess2 = Process.create(0, 5, testServices);
		
		// Assigning the second process
		testProcessInCPU = ProcessInCPU.create(testProcess2);
		
		// Checking if second process is assigned
		assertEquals(testProcessInCPU.getProcess(), testProcess2);
	}
	
	@Test
	// Testing getServiceTimes() method in ProcessInCPU class
	void testGetServiceTimes() {
		
		// Creating dummy ArrayList of Interval Pairs to create a Service Times Array
		ArrayList<IntervalPair> testServiceTimes = new ArrayList<IntervalPair>();
		
		// Creating dummy ArrayList of Services to create a Process
		ArrayList<Service> testServices = new ArrayList<Service>();
		
		// Creating Process using its static create method
		Process testProcess1 = Process.create(0, 5, testServices);
		
		// Creating ProcessInCPU instance using its create() static method
		ProcessInCPU testProcessInCPU = ProcessInCPU.create(testProcess1);
		
		
		// Executing logWorking() for testProcessInCPU object
		testProcessInCPU.logWorking(0, 5); // Dummy start time and finish time
		testProcessInCPU.logWorking(6, 7);
		testProcessInCPU.logWorking(8, 10);
		
		//Manually adding Interval Pair to our testServiceTimes
		testServiceTimes.add(IntervalPair.create(0, 5));
		testServiceTimes.add(IntervalPair.create(6, 7));
		testServiceTimes.add(IntervalPair.create(8, 10));
		
		//actual result
		ArrayList<IntervalPair> actual = testProcessInCPU.getServiceTimes();
		
		//expected result
		ArrayList<IntervalPair> expected = testServiceTimes;
		
		assertEquals(actual.size(), expected.size());
		
		for(int i=0; i<expected.size();i++) {
			assertEquals(actual.get(i).getStart(), expected.get(i).getStart());
			assertEquals(actual.get(i).getEnd(), expected.get(i).getEnd());
		}
				
	}
	
	@Test
	// Testing logWorking() method in ProcessInCPU class
	void testLogWorking() {
		
		// Creating dummy ArrayList of Services to create a Process
		ArrayList<Service> testServices = new ArrayList<Service>();
		
		// Creating Process using its static create method
		Process testProcess1 = Process.create(0, 5, testServices);
		
		// Creating ProcessInCPU instance using its create() static method
		ProcessInCPU testProcessInCPU = ProcessInCPU.create(testProcess1);
		
		
		// Executing logWorking() for testProcessInCPU object
		testProcessInCPU.logWorking(0, 5); // Dummy start time and finish time
		
		//actual result
		IntervalPair actual = testProcessInCPU.getServiceTimes().get(0);
		
		//expected result
		IntervalPair expected = IntervalPair.create(0, 5);
		
		assertEquals(actual.getStart(), expected.getStart());
		assertEquals(actual.getEnd(), expected.getEnd());

	}
	
	@Test
	// Testing isCurServiceOver() method in ProcessInCPU class
	void testIsCurServiceOver() {
		
		// Creating  ArrayList of Services to create a Process
		ArrayList<Service> testServices = new ArrayList<Service>();
		testServices.add(Service.create("C", "2"));
		testServices.add(Service.create("K", "1"));
		
		// Creating Process using its static create method
		Process testProcess1 = Process.create(0, 2, testServices);
		
		// Creating ProcessInCPU instance using its create() static method
		ProcessInCPU testProcessInCPU = ProcessInCPU.create(testProcess1);
		
		//assign service index to locate second service
		testProcessInCPU.curServiceIndex=1;
		
		// Creating another Process using its static create method
		// to assign it instead of the one previousle assigned with create()
		Process testProcess2 = Process.create(0, 5, testServices);
		
		// Assigning the second process
		testProcessInCPU = ProcessInCPU.create(testProcess2);
		
		// Checking if second process is assigned
		assertEquals(testProcessInCPU.getProcess(), testProcess2);
		
		//actual result
		IntervalPair actual = testProcessInCPU.getServiceTimes().get(0);
		
		//expected result
		IntervalPair expected = true;
		
		assertEquals(actual.getStart(), expected.getStart());
		assertEquals(actual.getEnd(), expected.getEnd());
	}

}
