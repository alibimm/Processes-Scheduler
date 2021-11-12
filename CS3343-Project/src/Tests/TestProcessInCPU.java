package Tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertArrayEquals;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.Assert.*;
import org.junit.jupiter.api.Test;

import Project.Interval;
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
		ArrayList<Interval> testServiceTimes = new ArrayList<Interval>();
		
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
		testServiceTimes.add(Interval.create(0, 5));
		testServiceTimes.add(Interval.create(6, 7));
		testServiceTimes.add(Interval.create(8, 10));
		
		//actual result
		ArrayList<Interval> actual = testProcessInCPU.getServiceTimes();
		
		//expected result
		ArrayList<Interval> expected = testServiceTimes;
		
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
		Interval actual = testProcessInCPU.getServiceTimes().get(0);
		
		//expected result
		Interval expected = Interval.create(0, 5);
		
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
		
		//service index is already located to index 0
		
		//assign current service tick = 1
		testProcessInCPU.incrementCurServiceTick();
		
		//actual result
		boolean actual = testProcessInCPU.isCurServiceOver();
		
		//expected result
		boolean expected = false;
		
		assertEquals(actual, expected);
		
		//assign current service tick = 2 a.k.a first service is finished
		testProcessInCPU.incrementCurServiceTick();
		
		//actual result
		 actual = testProcessInCPU.isCurServiceOver();
		
		//expected result
		 expected = true;
		
		assertEquals(actual, expected);
	}

	
	@Test
	// Testing proceedToNextService() method in ProcessInCPU class
	void testProceedToNextService() {
		
		// Creating  ArrayList of Services to create a Process
		ArrayList<Service> testServices = new ArrayList<Service>();
		testServices.add(Service.create("C", "2"));
		testServices.add(Service.create("K", "1"));
		
		// Creating Process using its static create method
		Process testProcess1 = Process.create(0, 2, testServices);
		
		// Creating ProcessInCPU instance using its create() static method
		ProcessInCPU testProcessInCPU = ProcessInCPU.create(testProcess1);
		
		//locate service index to the end of list
		testProcessInCPU.setCurServiceIndex(1);
		
		//actual result
		 boolean actual = testProcessInCPU.proceedToNextService();
		
		//expected result
		 boolean expected = true;
		
		assertEquals(actual, expected);
		
		//locate service index to the beginning of list
		testProcessInCPU.setCurServiceIndex(0);
		
		//actual result
		  actual = testProcessInCPU.proceedToNextService();
		
		//expected result
		  expected = false;
		
		assertEquals(actual, expected);
	}
	
	@Test
	// Testing updateQueueingTime() method in ProcessInCPU class
	void testUpdateQueueingTime() {

		// Creating  ArrayList of Services to create a Process
		ArrayList<Service> testServices = new ArrayList<Service>();
		testServices.add(Service.create("C", "2"));
		testServices.add(Service.create("K", "1"));
		
		// Creating Process using its static create method
		Process testProcess1 = Process.create(0, 2, testServices);
		
		// Creating ProcessInCPU instance using its create() static method
		ProcessInCPU testProcessInCPU = ProcessInCPU.create(testProcess1);
		
		//locate service index to the end of list
		testProcessInCPU.setCurServiceIndex(1);
		
		//expected result
		 int expected = testProcessInCPU.getQueuingTimeIO()+1;
		
		 //execute updateQueueingTime()
		 testProcessInCPU.updateQueueingTime();
		 
		//actual result
		 int actual = testProcessInCPU.getQueuingTimeIO();
		
		assertEquals(actual, expected);
		
		//locate service index to the beginning of list
		testProcessInCPU.setCurServiceIndex(0);
		
		//expected result
		  expected = testProcessInCPU.getQueuingTimeCPU()+1;

		 //execute updateQueueingTime()
		 testProcessInCPU.updateQueueingTime();
		 
		//actual result
		  actual = testProcessInCPU.getQueuingTimeCPU();
		
		
		assertEquals(actual, expected);
	}
	
	@Test
	// Testing findShortestServiceNextProcess() method in ProcessInCPU class
	void testFindShortestServiceNextProcess() {

		//creating dummy ArrayList<ProcessInCPU>
		ArrayList<ProcessInCPU> testProcesses = new ArrayList<ProcessInCPU>();
		
		//expected result
		int expected = -1;
		
		//actual result
		int actual = ProcessInCPU.findShortestRemainingTimeProcess(testProcesses);
		
		assertEquals(actual, expected);
		
		// Creating  ArrayList of Services to create a Process
		ArrayList<Service> testServices = new ArrayList<Service>();
		testServices.add(Service.create("C", "2"));
		testServices.add(Service.create("K", "1"));
		
		// Creating Process using its static create method
		Process testProcess1 = Process.create(0, 2, testServices);
		
		// Creating ProcessInCPU instance using its create() static method
		ProcessInCPU testProcessInCPU1 = ProcessInCPU.create(testProcess1);
		
		//locate service index to the end of list
		testProcessInCPU1.setCurServiceIndex(1);
		
		
		// Creating  ArrayList of Services to create a Process
		ArrayList<Service> testServices2 = new ArrayList<Service>();
		testServices2.add(Service.create("C", "2"));
		testServices2.add(Service.create("K", "1"));
		
		// Creating Process using its static create method
		Process testProcess2 = Process.create(0, 2, testServices);
		
		// Creating ProcessInCPU instance using its create() static method
		ProcessInCPU testProcessInCPU2 = ProcessInCPU.create(testProcess1);
		
		//locate service index to the end of list
		testProcessInCPU2.setCurServiceIndex(0);
		
		//add these entries to ArrayList
		testProcesses.add(testProcessInCPU1);
		testProcesses.add(testProcessInCPU2);
		
		expected = 0;
		
		actual = ProcessInCPU.findShortestRemainingTimeProcess(testProcesses);
		
		assertEquals(actual, expected);
		
		//locate service index to the beginning of list
		testProcessInCPU1.setCurServiceIndex(0);
		
		//locate service index to the end of list
		testProcessInCPU2.setCurServiceIndex(1);
		
		expected = 1;
		
		actual = ProcessInCPU.findShortestRemainingTimeProcess(testProcesses);
		
		assertEquals(actual, expected);
	}
	
	@Test
	// Testing findHighestResponseRatioProcess() method in ProcessInCPU class
	void testFindHighestResponseRatioProcess() {
		
		//creating dummy ArrayList<ProcessInCPU>
		ArrayList<ProcessInCPU> testProcesses = new ArrayList<ProcessInCPU>();
		
		// Creating  ArrayList of Services to create a Process
		ArrayList<Service> testServices = new ArrayList<Service>();
		testServices.add(Service.create("C", "2"));
		testServices.add(Service.create("K", "1"));
		
		// Creating Process using its static create method
		Process testProcess1 = Process.create(0, 2, testServices);
		
		// Creating ProcessInCPU instance using its create() static method
		ProcessInCPU testProcessInCPU1 = ProcessInCPU.create(testProcess1);
		
		// Creating  ArrayList of Services to create a Process
		ArrayList<Service> testServices2 = new ArrayList<Service>();
		testServices2.add(Service.create("C", "2"));
		testServices2.add(Service.create("K", "1"));
		
		// Creating Process using its static create method
		Process testProcess2 = Process.create(0, 2, testServices2);
		
		// Creating ProcessInCPU instance using its create() static method
		ProcessInCPU testProcessInCPU2 = ProcessInCPU.create(testProcess1);
		

		//add these entries to ArrayList
		testProcesses.add(testProcessInCPU1);
		testProcesses.add(testProcessInCPU2);
		
		//expected result
		int expected = 0;
		
		//actual result
		int actual = ProcessInCPU.findHighestResponseRatioProcess(testProcesses);
		
		assertEquals(actual, expected);
		
		//creating dummy ArrayList<ProcessInCPU>
		ArrayList<ProcessInCPU> testProcesses2 = new ArrayList<ProcessInCPU>();
		
		// Creating  ArrayList of Services to create a Process
		ArrayList<Service> testServices3 = new ArrayList<Service>();
		testServices.add(Service.create("C", "2"));
		testServices.add(Service.create("K", "1"));
		
		// Creating Process using its static create method
		Process testProcess3 = Process.create(0, 2, testServices3);
		
		// Creating ProcessInCPU instance using its create() static method
		ProcessInCPU testProcessInCPU3 = ProcessInCPU.create(testProcess1);
		
		// Creating  ArrayList of Services to create a Process
		ArrayList<Service> testServices4 = new ArrayList<Service>();
		testServices2.add(Service.create("C", "2"));
		testServices2.add(Service.create("K", "1"));
		testServices2.add(Service.create("C", "2"));
		
		// Creating Process using its static create method
		Process testProcess4 = Process.create(0, 2, testServices4);
		
		// Creating ProcessInCPU instance using its create() static method
		ProcessInCPU testProcessInCPU4 = ProcessInCPU.create(testProcess1);
		
		//locate service index to the middle of list
		testProcessInCPU4.setCurServiceIndex(0);
		
		 //execute updateQueueingTime()
		 testProcessInCPU4.updateQueueingTime();

		//locate service index to the end of list
		testProcessInCPU4.setCurServiceIndex(1);
		 
		//add these entries to ArrayList
		testProcesses2.add(testProcessInCPU3);
		testProcesses2.add(testProcessInCPU4);
		
		//expected result
		expected = 1;
		
		//actual result
		actual = ProcessInCPU.findHighestResponseRatioProcess(testProcesses2);
		
		assertEquals(actual, expected);
	}
}
