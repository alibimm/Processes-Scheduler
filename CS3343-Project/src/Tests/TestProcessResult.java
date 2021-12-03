package Tests;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import Exceptions.ExInvalidServiceType;
import Project.Interval;
import Project.Process;
import Project.ProcessInCPU;
import Project.ProcessResult;
import Project.Service;

class TestProcessResult {

	@Test
	void testCreateResultList() {	
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
		
		// Execute createResultList
		ArrayList<ProcessResult> actualResultList = ProcessResult.createResultList(list);
		
		// Assertions
		assertEquals(p1, actualResultList.get(0).getProcess());
		
		Interval expectedInterval = Interval.create(2, 12);
		ArrayList<Interval> expectedIntervals = new ArrayList<Interval>();
		expectedIntervals.add(expectedInterval);
		assertEquals(expectedIntervals.get(0).getStart(), actualResultList.get(0).getServiceItervals().get(0).getStart());
		assertEquals(expectedIntervals.get(0).getEnd(), actualResultList.get(0).getServiceItervals().get(0).getEnd());
		
		assertEquals(12, actualResultList.get(0).getExitTime());
		assertEquals(0, actualResultList.get(0).getQueuingTimeCPU());
		assertEquals(0, actualResultList.get(0).getQueuingTimeIO());
		assertEquals(0, actualResultList.get(0).getQueuingTime());
		assertEquals(10, actualResultList.get(0).getServiceTime());
		assertEquals(10, actualResultList.get(0).getTurnaroundTime());
		assertEquals(1.00, actualResultList.get(0).getratioTS(), 0.00);
		
		// Assertion
		assertEquals(4,actualResultList.size());
	}
	
	@Test
	void testCalcMaxAvgQueuingTime() throws ExInvalidServiceType {
		class ProcessResultStub extends ProcessResult {
			private int queuingTimeStub; 

			public ProcessResultStub(ProcessInCPU processInCPU, int qT) {
				super(processInCPU);
				queuingTimeStub = qT;	
			}
			public int getQueuingTime() {
				return queuingTimeStub;
			}
		}
		
		// Creating mock allServices 
		Service s1 = Service.create("C", "10");
		ArrayList<Service> allServices = new ArrayList<Service>(Arrays.asList(s1));
		
		// Creating mock processes 
		Process p1 = Process.create(1, 2, allServices);
		Process p2 = Process.create(2, 4, allServices);
		Process p3 = Process.create(3, 6, allServices);
		Process p4 = Process.create(4, 8, allServices);

		// Creating mock processInCPUs
		ProcessInCPU pCPU1 = ProcessInCPU.create(p1);
		pCPU1.logWorking(2, 12);
		ProcessInCPU pCPU2 = ProcessInCPU.create(p2);
		pCPU2.logWorking(2, 12);
		ProcessInCPU pCPU3 = ProcessInCPU.create(p3);
		pCPU3.logWorking(2, 12);
		ProcessInCPU pCPU4 = ProcessInCPU.create(p4);
		pCPU4.logWorking(2, 12);


		// Creating processResultStubs
		ProcessResultStub pStub1 = new ProcessResultStub(pCPU1, 10);
		ProcessResultStub pStub2 = new ProcessResultStub(pCPU2, 12);
		ProcessResultStub pStub3 = new ProcessResultStub(pCPU3, 6);
		ProcessResultStub pStub4 = new ProcessResultStub(pCPU4, 15);

		ArrayList<ProcessResult> processResultList = new ArrayList<ProcessResult>(Arrays.asList((ProcessResult)pStub1, (ProcessResult)pStub2, (ProcessResult)pStub3, (ProcessResult)pStub4)); 
	
		// Creating expected 
		double[] expectedMaxAvgQueueTime = new double[] {15, 10.75};
		
		// Executing 
		double[] actualMaxAvgQueueTime = ProcessResult.calcMaxAvgQueuingTime(processResultList);
				
		// Assertion
		assertEquals(expectedMaxAvgQueueTime[0],actualMaxAvgQueueTime[0], 0.00);
		assertEquals(expectedMaxAvgQueueTime[1],actualMaxAvgQueueTime[1], 0.00);
	}

	@Test
	void testCalcMaxAvgTurnaroundTime() throws ExInvalidServiceType {
		class ProcessResultStub extends ProcessResult {
			private int turnaroundStub; 

			public ProcessResultStub(ProcessInCPU processInCPU, int ta) {
				super(processInCPU);
				turnaroundStub = ta;	
			}
			public int getTurnaroundTime() {
				return turnaroundStub;
			}
		}
		
		// Creating mock allServices 
		Service s1 = Service.create("C", "10");
		ArrayList<Service> allServices = new ArrayList<Service>(Arrays.asList(s1));
		
		// Creating mock processes 
		Process p1 = Process.create(1, 2, allServices);
		Process p2 = Process.create(2, 4, allServices);
		Process p3 = Process.create(3, 6, allServices);
		Process p4 = Process.create(4, 8, allServices);

		// Creating mock processInCPUs
		ProcessInCPU pCPU1 = ProcessInCPU.create(p1);
		pCPU1.logWorking(2, 12);
		ProcessInCPU pCPU2 = ProcessInCPU.create(p2);
		pCPU2.logWorking(2, 12);
		ProcessInCPU pCPU3 = ProcessInCPU.create(p3);
		pCPU3.logWorking(2, 12);
		ProcessInCPU pCPU4 = ProcessInCPU.create(p4);
		pCPU4.logWorking(2, 12);


		// Creating processResultStubs
		ProcessResultStub pStub1 = new ProcessResultStub(pCPU1, 10);
		ProcessResultStub pStub2 = new ProcessResultStub(pCPU2, 12);
		ProcessResultStub pStub3 = new ProcessResultStub(pCPU3, 6);
		ProcessResultStub pStub4 = new ProcessResultStub(pCPU4, 15);

		ArrayList<ProcessResult> processResultList = new ArrayList<ProcessResult>(Arrays.asList((ProcessResult)pStub1, (ProcessResult)pStub2, (ProcessResult)pStub3, (ProcessResult)pStub4)); 
	
		// Creating expected 
		double[] expectedMaxAvgTurnaroundTime = new double[] {15, 10.75};
		
		// Executing 
		double[] actualMaxAvgTurnaroundTime = ProcessResult.calcMaxAvgTurnaroundTime(processResultList);
				
		// Assertion
		assertEquals(expectedMaxAvgTurnaroundTime[0],actualMaxAvgTurnaroundTime[0], 0.00);
		assertEquals(expectedMaxAvgTurnaroundTime[1],actualMaxAvgTurnaroundTime[1], 0.00);
	}

	@Test
	void testCalcMaxAvgRatioTS() throws ExInvalidServiceType {
		class ProcessResultStub extends ProcessResult {
			private double ratioTSStub; 

			public ProcessResultStub(ProcessInCPU processInCPU, double rTS) {
				super(processInCPU);
				ratioTSStub = rTS;	
			}
			public double getratioTS() {
				return ratioTSStub;
			}
		}
		
		// Creating mock allServices 
		Service s1 = Service.create("C", "10");
		ArrayList<Service> allServices = new ArrayList<Service>(Arrays.asList(s1));
		
		// Creating mock processes 
		Process p1 = Process.create(1, 2, allServices);
		Process p2 = Process.create(2, 4, allServices);
		Process p3 = Process.create(3, 6, allServices);
		Process p4 = Process.create(4, 8, allServices);

		// Creating mock processInCPUs
		ProcessInCPU pCPU1 = ProcessInCPU.create(p1);
		pCPU1.logWorking(2, 12);
		ProcessInCPU pCPU2 = ProcessInCPU.create(p2);
		pCPU2.logWorking(2, 12);
		ProcessInCPU pCPU3 = ProcessInCPU.create(p3);
		pCPU3.logWorking(2, 12);
		ProcessInCPU pCPU4 = ProcessInCPU.create(p4);
		pCPU4.logWorking(2, 12);


		// Creating processResultStubs
		ProcessResultStub pStub1 = new ProcessResultStub(pCPU1, 10);
		ProcessResultStub pStub2 = new ProcessResultStub(pCPU2, 12);
		ProcessResultStub pStub3 = new ProcessResultStub(pCPU3, 6);
		ProcessResultStub pStub4 = new ProcessResultStub(pCPU4, 15);

		ArrayList<ProcessResult> processResultList = new ArrayList<ProcessResult>(Arrays.asList((ProcessResult)pStub1, (ProcessResult)pStub2, (ProcessResult)pStub3, (ProcessResult)pStub4)); 
	
		// Creating expected 
		double[] expectedMaxAvgRatioTS = new double[] {15, 10.75};
		
		// Executing 
		double[] actualMaxAvgRatioTS = ProcessResult.calcMaxAvgRatioTS(processResultList);
				
		// Assertion
		assertEquals(expectedMaxAvgRatioTS[0],actualMaxAvgRatioTS[0],0.00);
		assertEquals(expectedMaxAvgRatioTS[1],actualMaxAvgRatioTS[1], 0.00);
	
	}

	@Test
	void testPrint() throws ExInvalidServiceType {
		class ProcessResultStub extends ProcessResult {
			public int turnaroundTimeStub;
			public int queuingTimeCPUStub;
			public int queuingTimeIOStub;

			public ProcessResultStub(ProcessInCPU processInCPU, int ta, int qCPU, int qIO) {
				super(processInCPU);
				turnaroundTimeStub = ta;
				queuingTimeCPUStub = qCPU;
				queuingTimeIOStub = qIO;
				
			}
			public int getQueuingTimeCPU() {
				return queuingTimeCPUStub;
			}

			public int getQueuingTimeIO() {
				return queuingTimeIOStub;
			}

			public int getTurnaroundTime() {
				return turnaroundTimeStub;
			}
		}
		
		// Creating mock allServices 
		Service s1 = Service.create("C", "10");
		ArrayList<Service> allServices = new ArrayList<Service>(Arrays.asList(s1));
		
		// Creating mock processes 
		Process p1 = Process.create(1, 2, allServices);

		// Creating mock processInCPUs
		ProcessInCPU pCPU1 = ProcessInCPU.create(p1);
		pCPU1.logWorking(2, 12);

		// Creating processResultStubs
		ProcessResultStub pStub1 = new ProcessResultStub(pCPU1, 10, 0, 12);
		setOutput();

		pStub1.print();
		
		String expectedOutput = "Process #1: 2 12 \nCPU Queuing Time: 0\nKeyboard Queuing Time: 12\nTurnaround Time: 10\n\n";
		String actualOutput = getOutput();
		
		assertEquals(expectedOutput, actualOutput);
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
	
	
	@Test
	void testGetTimeInCPU() throws ExInvalidServiceType {
		// Creating mock allServices 
			Service s1 = Service.create("C", "10");
			ArrayList<Service> allServices = new ArrayList<Service>(Arrays.asList(s1));
			
			// Creating mock processes 
			Process p1 = Process.create(1, 2, allServices);

			// Creating mock processInCPUs
			ProcessInCPU pCPU1 = ProcessInCPU.create(p1);
			pCPU1.logWorking(2, 12);
			pCPU1.logWorking(13, 15);

			ArrayList<ProcessInCPU> list = new ArrayList<ProcessInCPU>(Arrays.asList(pCPU1));
			
			// Execute createResultList
			ArrayList<ProcessResult> processResultList = ProcessResult.createResultList(list);
			
			// Creating expected maxAvgTurnaroundTime
			int expectedTimeInCPU = 12;
			
			// Executing calcMaxAvgTurnaroundTime
			int actualTimeInCPU = processResultList.get(0).getTimeInCPU();
					
			// Assertion
			assertEquals(expectedTimeInCPU,actualTimeInCPU);			
	}
}
