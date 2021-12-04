package Tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import Project.AlgorithmType;
import Project.FCFS;
import Project.Process;
import Project.ProcessInCPU;
import Project.Service;

public class TestFCFS {
	@Test
	void testGetType() {
		FCFS testFCFS = FCFS.getInstance();
		
		AlgorithmType actual = testFCFS.getType();
		AlgorithmType expected = AlgorithmType.FCFS;
		
		assertEquals(actual, expected);
	}

	@Test
	void testSchedule1() {
		FCFS testFCFS = FCFS.getInstance();
				
		// Creating  ArrayList of Services to create a Process
		ArrayList<Service> testServices0 = new ArrayList<Service>();
		testServices0.add(Service.create("C", "3"));
		testServices0.add(Service.create("K", "9"));
		testServices0.add(Service.create("C", "5"));
		testServices0.add(Service.create("K", "1"));
		testServices0.add(Service.create("C", "3"));
		
		Process testProcess0 = Process.create(0, 0, testServices0);
		
		ArrayList<Service> testServices1 = new ArrayList<Service>();
		testServices1.add(Service.create("C", "1"));
		testServices1.add(Service.create("K", "12"));
		testServices1.add(Service.create("C", "4"));
		
		// Creating Process using its static create method
		Process testProcess1 = Process.create(1, 0, testServices1);
		
		ArrayList<Service> testServices2 = new ArrayList<Service>();
		testServices2.add(Service.create("C", "3"));
		testServices2.add(Service.create("K", "5"));
		testServices2.add(Service.create("C", "5"));
		testServices2.add(Service.create("K", "7"));
		testServices2.add(Service.create("C", "5"));
		
		// Creating Process using its static create method
		Process testProcess2 = Process.create(2, 2, testServices2);
		
		ArrayList<Service> testServices3 = new ArrayList<Service>();
		testServices3.add(Service.create("C", "6"));
		testServices3.add(Service.create("K", "8"));
		testServices3.add(Service.create("C", "5"));

		// Creating Process using its static create method
		Process testProcess3 = Process.create(3, 5, testServices3);
		
		ArrayList<Process> processes = new ArrayList<>();
		processes.add(testProcess0);
		processes.add(testProcess1);
		processes.add(testProcess2);
		processes.add(testProcess3);
		
		//actual result
		ArrayList<ProcessInCPU> actual = testFCFS.schedule(processes);

		// Creating ProcessInCPU instance using its create() static method
		// The sequence of adding ProcessInCPU objects is the sequence in which
		// processes leave the CPU
		ArrayList<ProcessInCPU> expected = new ArrayList<ProcessInCPU>();
		expected.add(ProcessInCPU.create(testProcess1));
		expected.add(ProcessInCPU.create(testProcess3));
		expected.add(ProcessInCPU.create(testProcess0));
		expected.add(ProcessInCPU.create(testProcess2));
		
		for (int i = 0; i < actual.size(); i++) {
			assertEquals(actual.get(i).getId(), expected.get(i).getId());
		}
	}
	
	@Test
	void testSchedule2() {
		FCFS testFCFS = FCFS.getInstance();
				
		// Creating  ArrayList of Services to create a Process
		ArrayList<Service> testServices0 = new ArrayList<Service>();
		testServices0.add(Service.create("C", "15"));
		
		Process testProcess0 = Process.create(0, 0, testServices0);
		
		ArrayList<Service> testServices1 = new ArrayList<Service>();
		testServices1.add(Service.create("C", "5"));
		
		// Creating Process using its static create method
		Process testProcess1 = Process.create(1, 0, testServices1);
		
		ArrayList<Service> testServices2 = new ArrayList<Service>();
		testServices2.add(Service.create("C", "10"));
		
		// Creating Process using its static create method
		Process testProcess2 = Process.create(2, 0, testServices2);
		
		ArrayList<Service> testServices3 = new ArrayList<Service>();
		testServices3.add(Service.create("C", "7"));

		// Creating Process using its static create method
		Process testProcess3 = Process.create(3, 15, testServices3);
		
		ArrayList<Service> testServices4 = new ArrayList<Service>();
		testServices4.add(Service.create("C", "8"));

		// Creating Process using its static create method
		Process testProcess4 = Process.create(4, 15, testServices4);
		
		ArrayList<Process> processes = new ArrayList<>();
		processes.add(testProcess0);
		processes.add(testProcess1);
		processes.add(testProcess2);
		processes.add(testProcess3);
		processes.add(testProcess4);
		
		ArrayList<ProcessInCPU> actual = testFCFS.schedule(processes);

		// Creating ProcessInCPU instance using its create() static method
		// The sequence of adding ProcessInCPU objects is the sequence in which
		// processes leave the CPU
		ArrayList<ProcessInCPU> expected = new ArrayList<ProcessInCPU>();
		expected.add(ProcessInCPU.create(testProcess0));
		expected.add(ProcessInCPU.create(testProcess1));
		expected.add(ProcessInCPU.create(testProcess2));
		expected.add(ProcessInCPU.create(testProcess3));
		expected.add(ProcessInCPU.create(testProcess4));
		
		for (int i = 0; i < actual.size(); i++) {
			assertEquals(actual.get(i).getId(), expected.get(i).getId());
		}
	}
}
