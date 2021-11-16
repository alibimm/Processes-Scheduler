package Tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Exceptions.ExInvalidServiceType;
import Project.Interval;
import Project.Process;
import Project.ProcessInCPU;
import Project.Service;
import Project.Util;

public class TestUtil {

	@Test
	// Testing moveProcessFrom() method in Util class
	void TestMoveProcessFrom() throws ExInvalidServiceType {
		
		//creating dummy ArrayList<ProcessInCPU>
		ArrayList<ProcessInCPU> testProcessesArr1 = new ArrayList<ProcessInCPU>();
		
		//creating dummy ArrayList<ProcessInCPU>
		ArrayList<ProcessInCPU> testProcessesArr2 = new ArrayList<ProcessInCPU>();
		
		// Creating  ArrayList of Services to create a Process
		ArrayList<Service> testServices = new ArrayList<Service>();
		testServices.add(Service.create("C", "2"));
		testServices.add(Service.create("K", "1"));
		
		// Creating Process using its static create method
		Process testProcess1 = Process.create(1, 2, testServices);
		
		// Creating ProcessInCPU instance using its create() static method
		ProcessInCPU testProcessInCPU1 = ProcessInCPU.create(testProcess1);
		
		// Creating  ArrayList of Services to create a Process
		ArrayList<Service> testServices2 = new ArrayList<Service>();
		testServices2.add(Service.create("C", "2"));
		testServices2.add(Service.create("K", "1"));
		
		// Creating Process using its static create method
		Process testProcess2 = Process.create(2, 2, testServices2);
		
		// Creating ProcessInCPU instance using its create() static method
		ProcessInCPU testProcessInCPU2 = ProcessInCPU.create(testProcess2);
		
		// Creating  ArrayList of Services to create a Process
		ArrayList<Service> testServices3 = new ArrayList<Service>();
		testServices3.add(Service.create("C", "2"));
		testServices3.add(Service.create("K", "1"));
		
		// Creating Process using its static create method
		Process testProcess3 = Process.create(3, 2, testServices3);
		
		// Creating ProcessInCPU instance using its create() static method
		ProcessInCPU testProcessInCPU3 = ProcessInCPU.create(testProcess3);
		
		//add processInCPU to arraylists
		testProcessesArr1.add(testProcessInCPU1);
		testProcessesArr1.add(testProcessInCPU2);
		testProcessesArr2.add(testProcessInCPU3);
		
		//execute Util.moveProcessFrom
		Util.moveProcessFrom(testProcessesArr1, testProcessesArr2);
		
		assertEquals(testProcessesArr1.get(0).getId(), 2);
		assertEquals(testProcessesArr2.get(0).getId(), 3);
		assertEquals(testProcessesArr2.get(1).getId(), 1);
	}
	
	@Test
	// Testing updateQueingTime() method in Util class
	void TestUpdateQueingTime() throws ExInvalidServiceType {
		//creating dummy ArrayList<ProcessInCPU>
		ArrayList<ProcessInCPU> testProcessesArr = new ArrayList<ProcessInCPU>();
		
		// Creating  ArrayList of Services to create a Process
		ArrayList<Service> testServices = new ArrayList<Service>();
		testServices.add(Service.create("C", "2"));
		testServices.add(Service.create("K", "1"));
		
		// Creating Process using its static create method
		Process testProcess1 = Process.create(1, 2, testServices);
		
		// Creating ProcessInCPU instance using its create() static method
		ProcessInCPU testProcessInCPU1 = ProcessInCPU.create(testProcess1);
		
		// Creating  ArrayList of Services to create a Process
		ArrayList<Service> testServices2 = new ArrayList<Service>();
		testServices2.add(Service.create("K", "1"));
		testServices2.add(Service.create("C", "2"));
		
		// Creating Process using its static create method
		Process testProcess2 = Process.create(2, 2, testServices2);
		
		// Creating ProcessInCPU instance using its create() static method
		ProcessInCPU testProcessInCPU2 = ProcessInCPU.create(testProcess2);
		
		// Creating  ArrayList of Services to create a Process
		ArrayList<Service> testServices3 = new ArrayList<Service>();
		testServices3.add(Service.create("C", "2"));
		testServices3.add(Service.create("K", "1"));
		
		// Creating Process using its static create method
		Process testProcess3 = Process.create(3, 2, testServices3);
		
		// Creating ProcessInCPU instance using its create() static method
		ProcessInCPU testProcessInCPU3 = ProcessInCPU.create(testProcess3);
		
		//add processInCPU to array lists
		testProcessesArr.add(testProcessInCPU1);
		testProcessesArr.add(testProcessInCPU2);
		testProcessesArr.add(testProcessInCPU3);
		
		//expected queuing times
		int expectedQTime1 = testProcessInCPU1.getQueuingTimeCPU() + testProcessInCPU1.getQueuingTimeIO() + 1;
		int expectedQTime2 = testProcessInCPU2.getQueuingTimeCPU() + testProcessInCPU2.getQueuingTimeIO() + 1;
		int expectedQTime3 = testProcessInCPU3.getQueuingTimeCPU() + testProcessInCPU3.getQueuingTimeIO() + 1;

		Util.updateQueingTime(testProcessesArr, 0, 3);
		
		//actual queuing times
		int actualQTime1 = testProcessInCPU1.getQueuingTimeCPU() + testProcessInCPU1.getQueuingTimeIO();
		int actualQTime2 = testProcessInCPU2.getQueuingTimeCPU() + testProcessInCPU2.getQueuingTimeIO();
		int actualQTime3 = testProcessInCPU3.getQueuingTimeCPU() + testProcessInCPU3.getQueuingTimeIO();
		
		assertEquals(expectedQTime1, actualQTime1);
		assertEquals(expectedQTime2, actualQTime2);
		assertEquals(expectedQTime3, actualQTime3);
	}
}
