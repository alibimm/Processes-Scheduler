package Tests;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import Project.Algorithm;
import Project.AlgorithmType;
import Project.Case;
import Project.FB;
import Project.FCFS;
import Project.HRRN;
import Project.Process;
import Project.RR;
import Project.SPN;
import Project.SRT;
import Project.Service;


class TestCase {

	// Creating mock allServices 
	Service s1 = Service.create("C", "15");
	Service s2 = Service.create("C", "5");
	Service s3 = Service.create("C", "10");
	Service s4 = Service.create("C", "7");
	Service s5 = Service.create("C", "8");
	Service s6 = Service.create("K", "10");
	Service s7 = Service.create("C", "1");
	
	ArrayList<Service> allServices1 = new ArrayList<Service>(Arrays.asList(s1));
	ArrayList<Service> allServices2 = new ArrayList<Service>(Arrays.asList(s2));
	ArrayList<Service> allServices3 = new ArrayList<Service>(Arrays.asList(s3));
	ArrayList<Service> allServices4 = new ArrayList<Service>(Arrays.asList(s4));
	ArrayList<Service> allServices5 = new ArrayList<Service>(Arrays.asList(s5,s6, s7));

	// Creating mock processes 
	Process p1 = Process.create(0, 0, allServices1);
	Process p2 = Process.create(1, 0, allServices2);
	Process p3 = Process.create(2, 0, allServices3);
	Process p4 = Process.create(3, 15, allServices4);
	Process p5 = Process.create(4, 15, allServices5);
	ArrayList<Process> processList = new ArrayList<Process>(Arrays.asList(p1,p2,p3,p4,p5));

	// Getting instances of algorithms
	Algorithm fcfs = FCFS.getInstance();
	Algorithm rr = RR.getInstance();
	Algorithm fb = FB.getInstance();
	Algorithm spn = SPN.getInstance();
	Algorithm srt = SRT.getInstance();
	Algorithm hrrn = HRRN.getInstance();
	ArrayList<Algorithm> algoList = new ArrayList<Algorithm>(Arrays.asList(fcfs, rr, fb, srt, spn, hrrn));
	
	@Test
	void testCreate() {
		// Executing create()
		Case actualCase = Case.create(algoList, processList);
		
		// Assertions
		assertEquals(6, actualCase.getResults().size());
		assertEquals(16, actualCase.getId());
	}
	
	@Test
	void testFindCaseWithIdNull() {
		// Executing create()
		Case c1 = Case.create(algoList, processList);
		Case c2 = Case.create(algoList, processList);
		Case c3 = Case.create(algoList, processList);
		Case c4 = Case.create(algoList, processList);
		
		ArrayList<Case> casesList = new ArrayList<Case>(Arrays.asList(c1,c2,c3,c4));
		
		Case actualCase = Case.findCaseWithId(100, casesList);
		assertEquals(null, actualCase);
	}
	
	@Test
	void testFindCaseWithId() {
		// Executing create()
		Case c1 = Case.create(algoList, processList);
		Case c2 = Case.create(algoList, processList);
		Case c3 = Case.create(algoList, processList);
		Case c4 = Case.create(algoList, processList);
		
		ArrayList<Case> casesList = new ArrayList<Case>(Arrays.asList(c1,c2,c3,c4));
		
		Case actualCase = Case.findCaseWithId(12, casesList);
		assertEquals(c1, actualCase);
	}

	@Test
	void testPrintTable() {
		Case c1 = Case.create(algoList, processList);
		setOutput();
		c1.printTable();
		//String output = getOutput();
		String expectedOutput = "Type           Duration       CPU Util       Avg Turnaround Max Turnaround Avg Queuing    Max Queuing    \n"
				+ "FCFS           45             1.00           23.40          30.00          14.40          22.00          \n"
				+ "RR             45             1.00           26.40          40.00          17.40          25.00          \n"
				+ "FB             45             1.00           27.40          45.00          18.40          30.00          \n"
				+ "SRT            45             1.00           17.40          45.00          8.40           30.00          \n"
				+ "SPN            45             1.00           17.40          45.00          8.40           30.00          \n"
				+ "HRRN           45             1.00           23.40          30.00          14.40          22.00          \n\n";
		assertEquals(expectedOutput, getOutput());
	}
	
	@Test
	void testPrintAlgoShort() {
		Case c1 = Case.create(algoList, processList);
		setOutput();
		c1.printAlgoShort(AlgorithmType.FCFS);
		String output = getOutput();
		String expectedOutput = "Case #5        FCFS           45             1.00           23.40          30.00          14.40          22.00          \n";
		assertEquals(expectedOutput, getOutput());
	}
	
	@Test
	void testAlgoDetail() {
		Case c1 = Case.create(algoList, processList);
		setOutput();
		c1.printAlgoDetail(AlgorithmType.RR);
		//String output = getOutput();
		String expectedOutput = "---------------------------------------------------\n"
				+ "Round Robin\nProcess #1: 5 10 \n"
				+ "CPU Queuing Time: 5\n"
				+ "Keyboard Queuing Time: 0\nTurnaround Time: 10\n"
				+ "\nProcess #2: 10 15 20 25 \n"
				+ "CPU Queuing Time: 15\n"
				+ "Keyboard Queuing Time: 0\n"
				+ "Turnaround Time: 25\n\n"
				+ "Process #0: 0 5 15 20 35 40 \n"
				+ "CPU Queuing Time: 25\n"
				+ "Keyboard Queuing Time: 0"
				+ "\nTurnaround Time: 40\n\n"
				+ "Process #3: 25 30 40 42 \n"
				+ "CPU Queuing Time: 20\n"
				+ "Keyboard Queuing Time: 0\n"
				+ "Turnaround Time: 27\n\n"
				+ "Process #4: 30 35 42 45 \n"
				+ "CPU Queuing Time: 22\n"
				+ "Keyboard Queuing Time: 0\n"
				+ "Turnaround Time: 30\n\n\n";
		assertEquals(expectedOutput, getOutput());	
	}
	
	@Test
	void testBestAlgorithmAQT() {
		Case c1 = Case.create(algoList, processList);
		ArrayList<AlgorithmType> actual = c1.bestAlgorithm("AQT");
		assertEquals(AlgorithmType.SRT, actual.get(0));
	}
	
	@Test
	void testBestAlgorithmATRT() {
		Case c1 = Case.create(algoList, processList);
		ArrayList<AlgorithmType> actual = c1.bestAlgorithm("ATRT");
		assertEquals(AlgorithmType.SRT, actual.get(0));
	}
	
	@Test
	void testBestAlgorithmARTS() {
		Case c1 = Case.create(algoList, processList);
		ArrayList<AlgorithmType> actual = c1.bestAlgorithm("ARTS");
		assertEquals(AlgorithmType.SRT, actual.get(0));
		assertEquals(AlgorithmType.SPN, actual.get(1));
	}
	
	@Test
	void testBestAlgorithmCpuUtil() {
		Case c1 = Case.create(algoList, processList);
		ArrayList<AlgorithmType> actual = c1.bestAlgorithm("CpuUtil");
		assertEquals(AlgorithmType.SPN, actual.get(0));
	}
	
	@Test
	void testBestAlgorithmDefault() {
		Case c1 = Case.create(algoList, processList);
		ArrayList<AlgorithmType> actual = c1.bestAlgorithm("");
		assertEquals(AlgorithmType.SRT, actual.get(0));
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
