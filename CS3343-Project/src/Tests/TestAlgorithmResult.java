package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import Project.AlgorithmResult;
import Project.AlgorithmType;
import Project.Process;
import Project.ProcessInCPU;
import Project.Service;

class TestAlgorithmResult {

	@Test
	void testCreate() {
		// Creating mock allServices 
		Service s1 = Service.create("C", "10");
		ArrayList<Service> allServices = new ArrayList<Service>(Arrays.asList(s1));
		
		// Creating mock processes 
		Process p1 = Process.create(1, 2, allServices);
		Process p2 = Process.create(2, 2, allServices);
		Process p3 = Process.create(3, 2, allServices);
		Process p4 = Process.create(4, 2, allServices);

		// Creating mock processInCPUs
		ProcessInCPU pCPU1 = ProcessInCPU.create(p1);
		pCPU1.logWorking(2, 12);
		ProcessInCPU pCPU2 = ProcessInCPU.create(p2);
		pCPU2.logWorking(2, 12);
		ProcessInCPU pCPU3 = ProcessInCPU.create(p3);
		pCPU3.logWorking(2, 12);
		ProcessInCPU pCPU4 = ProcessInCPU.create(p4);
		pCPU4.logWorking(2, 13);

		// Creating mock ArrayList
		ArrayList<ProcessInCPU> list = new ArrayList<ProcessInCPU>(Arrays.asList(pCPU1,pCPU2,pCPU3,pCPU4));
		
		// Executing the create
		AlgorithmResult actualResult = AlgorithmResult.create(list, AlgorithmType.FB);
		
		// Assertions
		assertEquals(AlgorithmType.FB, actualResult.getAlgorithmType() );
		assertEquals(0.0, actualResult.getAvgQueuingTime(), 0.00);
		assertEquals(10.25, actualResult.getAvgTurnaroundTime(), 0.00);
		assertEquals(1.0, actualResult.getAvgRatioTS(), 0.00);
		assertEquals(0.0, actualResult.getMaxQueuingTime(), 0.00);
		assertEquals(11.0, actualResult.getMaxTurnaroundTime(), 0.00);
		assertEquals(3.1538461538461537, actualResult.getCpuUtil(), 0.00);		
	}
	
	@Test
	void testGetAvgTurnaroundTime() {
		// Creating mock allServices 
		Service s1 = Service.create("C", "10");
		ArrayList<Service> allServices = new ArrayList<Service>(Arrays.asList(s1));
		
		// Creating mock processes 
		Process p1 = Process.create(1, 2, allServices);
		Process p2 = Process.create(2, 2, allServices);
		Process p3 = Process.create(3, 2, allServices);
		Process p4 = Process.create(4, 2, allServices);

		// Creating mock processInCPUs
		ProcessInCPU pCPU1 = ProcessInCPU.create(p1);
		pCPU1.logWorking(2, 12);
		ProcessInCPU pCPU2 = ProcessInCPU.create(p2);
		pCPU2.logWorking(2, 12);
		ProcessInCPU pCPU3 = ProcessInCPU.create(p3);
		pCPU3.logWorking(2, 12);
		ProcessInCPU pCPU4 = ProcessInCPU.create(p4);
		pCPU4.logWorking(2, 12);

		// Creating mock ArrayList
		ArrayList<ProcessInCPU> list = new ArrayList<ProcessInCPU>(Arrays.asList(pCPU1,pCPU2,pCPU3,pCPU4));
		
		// Executing the create
		AlgorithmResult algoResult = AlgorithmResult.create(list, AlgorithmType.FB);
		
		double actualType = algoResult.getAvgTurnaroundTime();
		
		assertEquals(10, actualType, 0.00);
	}

	@Test
	void testGetAlgorithmType() {
		// Creating mock allServices 
		Service s1 = Service.create("C", "10");
		ArrayList<Service> allServices = new ArrayList<Service>(Arrays.asList(s1));
		
		// Creating mock processes 
		Process p1 = Process.create(1, 2, allServices);
		Process p2 = Process.create(2, 2, allServices);
		Process p3 = Process.create(3, 2, allServices);
		Process p4 = Process.create(4, 2, allServices);

		// Creating mock processInCPUs
		ProcessInCPU pCPU1 = ProcessInCPU.create(p1);
		pCPU1.logWorking(2, 12);
		ProcessInCPU pCPU2 = ProcessInCPU.create(p2);
		pCPU2.logWorking(2, 12);
		ProcessInCPU pCPU3 = ProcessInCPU.create(p3);
		pCPU3.logWorking(2, 12);
		ProcessInCPU pCPU4 = ProcessInCPU.create(p4);
		pCPU4.logWorking(2, 12);

		// Creating mock ArrayList
		ArrayList<ProcessInCPU> list = new ArrayList<ProcessInCPU>(Arrays.asList(pCPU1,pCPU2,pCPU3,pCPU4));
		
		// Executing the create
		AlgorithmResult algoResult = AlgorithmResult.create(list, AlgorithmType.FB);
		
		AlgorithmType actualType = algoResult.getAlgorithmType();
		
		assertEquals(AlgorithmType.FB, actualType);
	}
	
	@Test
	void testPrintStats() {
		// Creating mock allServices 
		Service s1 = Service.create("C", "10");
		ArrayList<Service> allServices = new ArrayList<Service>(Arrays.asList(s1));
		
		// Creating mock processes 
		Process p1 = Process.create(1, 2, allServices);
		Process p2 = Process.create(2, 2, allServices);
		Process p3 = Process.create(3, 2, allServices);
		Process p4 = Process.create(4, 2, allServices);

		// Creating mock processInCPUs
		ProcessInCPU pCPU1 = ProcessInCPU.create(p1);
		pCPU1.logWorking(2, 12);
		ProcessInCPU pCPU2 = ProcessInCPU.create(p2);
		pCPU2.logWorking(2, 12);
		ProcessInCPU pCPU3 = ProcessInCPU.create(p3);
		pCPU3.logWorking(2, 12);
		ProcessInCPU pCPU4 = ProcessInCPU.create(p4);
		pCPU4.logWorking(2, 12);

		// Creating mock ArrayList
		ArrayList<ProcessInCPU> list = new ArrayList<ProcessInCPU>(Arrays.asList(pCPU1,pCPU2,pCPU3,pCPU4));
		
		// Executing the create
		AlgorithmResult algoResult = AlgorithmResult.create(list, AlgorithmType.FB);
				
		setOutput();
		algoResult.printStats();
		String expectedOutput = String.format("%-15s%-15.0f%-15.2f%-15.2f%-15.2f%-15.2f%-15.2f\n", 
						"FB", 
						12.00,
						3.33,
						10.00,
						10.00,
						0.00,
						0.00);
		
		assertEquals(expectedOutput, getOutput());
		
	}
	
	@Test
	void testPrintDetails() {
		// Creating mock allServices 
		Service s1 = Service.create("C", "10");
		ArrayList<Service> allServices = new ArrayList<Service>(Arrays.asList(s1));
		
		// Creating mock processes 
		Process p1 = Process.create(1, 2, allServices);
		Process p2 = Process.create(2, 2, allServices);
		Process p3 = Process.create(3, 2, allServices);
		Process p4 = Process.create(4, 2, allServices);

		// Creating mock processInCPUs
		ProcessInCPU pCPU1 = ProcessInCPU.create(p1);
		pCPU1.logWorking(2, 12);
		ProcessInCPU pCPU2 = ProcessInCPU.create(p2);
		pCPU2.logWorking(2, 12);
		ProcessInCPU pCPU3 = ProcessInCPU.create(p3);
		pCPU3.logWorking(2, 12);
		ProcessInCPU pCPU4 = ProcessInCPU.create(p4);
		pCPU4.logWorking(2, 12);

		// Creating mock ArrayList
		ArrayList<ProcessInCPU> list = new ArrayList<ProcessInCPU>(Arrays.asList(pCPU1,pCPU2,pCPU3,pCPU4));
		
		// Executing the create
		AlgorithmResult algoResult = AlgorithmResult.create(list, AlgorithmType.FB);
				
		setOutput();
		algoResult.printDetails();
		String expectedOutput = "---------------------------------------------------\n"
				+ "Feedback\n"
				+ "Process #1: 2 12 \n"
				+ "CPU Queuing Time: 0\n"
				+ "Keyboard Queuing Time: 0\n"
				+ "Turnaround Time: 10\n"
				+ "\n"
				+ "Process #2: 2 12 \n"
				+ "CPU Queuing Time: 0\n"
				+ "Keyboard Queuing Time: 0\n"
				+ "Turnaround Time: 10\n"
				+ "\n"
				+ "Process #3: 2 12 \n"
				+ "CPU Queuing Time: 0\n"
				+ "Keyboard Queuing Time: 0\n"
				+ "Turnaround Time: 10\n"
				+ "\n"
				+ "Process #4: 2 12 \n"
				+ "CPU Queuing Time: 0\n"
				+ "Keyboard Queuing Time: 0\n"
				+ "Turnaround Time: 10\n"
				+ "\n\n";
		assertEquals(expectedOutput, getOutput());		
	}
	
	@Test
	void testGetAvgQueuingTime() {
		// Creating mock allServices 
		Service s1 = Service.create("C", "10");
		ArrayList<Service> allServices = new ArrayList<Service>(Arrays.asList(s1));
		
		// Creating mock processes 
		Process p1 = Process.create(1, 2, allServices);
		Process p2 = Process.create(2, 2, allServices);
		Process p3 = Process.create(3, 2, allServices);
		Process p4 = Process.create(4, 2, allServices);

		// Creating mock processInCPUs
		ProcessInCPU pCPU1 = ProcessInCPU.create(p1);
		pCPU1.logWorking(2, 12);
		ProcessInCPU pCPU2 = ProcessInCPU.create(p2);
		pCPU2.logWorking(2, 12);
		ProcessInCPU pCPU3 = ProcessInCPU.create(p3);
		pCPU3.logWorking(2, 12);
		ProcessInCPU pCPU4 = ProcessInCPU.create(p4);
		pCPU4.logWorking(2, 12);

		for (int i=0; i<5;i++) {
			pCPU1.updateQueueingTime();
			pCPU2.updateQueueingTime();
			pCPU3.updateQueueingTime();
		}
		// Creating mock ArrayList
		ArrayList<ProcessInCPU> list = new ArrayList<ProcessInCPU>(Arrays.asList(pCPU1,pCPU2,pCPU3,pCPU4));
		
		
		// Executing the create
		AlgorithmResult algoResult = AlgorithmResult.create(list, AlgorithmType.FB);
		
		Double actualAvgQueuingTime = algoResult.getAvgQueuingTime();
		
		assertEquals(3.75, actualAvgQueuingTime);
	}
	
	
	//Necessary for print test
		PrintStream oldPrintStream;
		ByteArrayOutputStream bos;

		private void setOutput() {
			oldPrintStream = System.out;
			bos = new ByteArrayOutputStream();
			System.setOut(new PrintStream(bos));
		}

		private String getOutput() { 
			System.setOut(oldPrintStream);
			return bos.toString();
		}
}
