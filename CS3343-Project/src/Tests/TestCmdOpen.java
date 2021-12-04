package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Commands.CmdOpen;
import Commands.CmdReadFile;
import Commands.CmdSchedule;
import Exceptions.ExInsufficientCommandArguments;
import Project.MainSystem;

class TestCmdOpen {
	
	@BeforeEach
	void setUp() {
		MainSystem system = MainSystem.getInstance();
		system.clear();
	}

	@Test
	void testOpenAtStart() throws Exception {		
		// Initializing input
		setOutput();
		String[] cmdParts = new String[] {"open", "12345"};
		(new CmdOpen()).execute(cmdParts);
		String actual = getOutput();
		String expected = "Case with ID 12345 was not found\n";
		assertEquals(expected, actual);
	}
	
	@Test
	void testOpenInsufficiientInputs() throws Exception {		
		// Initializing input
		String[] cmdParts = new String[] {"open"};
		ExInsufficientCommandArguments ex = assertThrows(ExInsufficientCommandArguments.class, () -> {(new CmdOpen()).execute(cmdParts);});
		assertEquals("Open Command should have more than 2 arguments", ex.getMessage());
	}
	
	@Test
	void testOpenWrongNumber() throws Exception {		
		// Initializing input
		setOutput();
		String[] cmdParts = new String[] {"open", "1.2"};
		(new CmdOpen()).execute(cmdParts);
		String actual = getOutput();
		String expected = "Sorry, there is no such algorithm.\n";
		assertEquals(expected, actual);
	}
	
	@Test
	void testOpenOpenFCFS() throws Exception {		
		String[] cmdParts = new String[] {"readfile", "./src/TestSamples/1-perfect.txt"};
		(new CmdReadFile()).execute(cmdParts);
		
		cmdParts = new String[] {"schedule"};
		(new CmdSchedule()).execute(cmdParts);
		setOutput();
		// Initializing input
		cmdParts = new String[] {"open", "FCFS"};
		(new CmdOpen()).execute(cmdParts);
		
		String expected = "Current algorithm filter: First Come First Serve\n" 
				+ "Case           Type           Duration       CPU Util       Avg Turnaround Max Turnaround Avg Queuing    Max Queuing    \n"
				+ "Case #0        FCFS           50             0.80           39.50          48.00          19.00          24.00          \n";
				
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	
	@Test
	void testOpenOpenRR() throws Exception {
		String[] cmdParts = new String[] {"readfile", "./src/TestSamples/1-perfect.txt"};
		(new CmdReadFile()).execute(cmdParts);
		
		cmdParts = new String[] {"schedule"};
		(new CmdSchedule()).execute(cmdParts);
		setOutput();
		// Initializing input
		cmdParts = new String[] {"open", "RR"};
		(new CmdOpen()).execute(cmdParts);
		
		String expected = "Current algorithm filter: Round Robin\n" 
				+ "Case           Type           Duration       CPU Util       Avg Turnaround Max Turnaround Avg Queuing    Max Queuing    \n"
				+ "Case #0        RR             50             0.80           39.50          48.00          19.00          24.00          \n";
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	
	@Test
	void testOpenOpenFB() throws Exception {	
		String[] cmdParts = new String[] {"readfile", "./src/TestSamples/1-perfect.txt"};
		(new CmdReadFile()).execute(cmdParts);
		
		cmdParts = new String[] {"schedule"};
		(new CmdSchedule()).execute(cmdParts);
		setOutput();
		// Initializing input
		cmdParts = new String[] {"open", "FB"};
		(new CmdOpen()).execute(cmdParts);
		
		String expected = "Current algorithm filter: Feedback\n" 
				+ "Case           Type           Duration       CPU Util       Avg Turnaround Max Turnaround Avg Queuing    Max Queuing    \n"
				+ "Case #0        FB             50             0.80           37.75          48.00          17.25          23.00          \n";
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	
	@Test
	void testOpenOpenHRRN() throws Exception {	
		String[] cmdParts = new String[] {"readfile", "./src/TestSamples/1-perfect.txt"};
		(new CmdReadFile()).execute(cmdParts);
		
		cmdParts = new String[] {"schedule"};
		(new CmdSchedule()).execute(cmdParts);
		setOutput();
		// Initializing input
		cmdParts = new String[] {"open", "HRRN"};
		(new CmdOpen()).execute(cmdParts);
		
		String expected = "Current algorithm filter: Highest Response Ratio Next\n" 
				+ "Case           Type           Duration       CPU Util       Avg Turnaround Max Turnaround Avg Queuing    Max Queuing    \n"
				+ "Case #0        HRRN           50             0.80           39.50          48.00          19.00          24.00          \n";
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	
	@Test
	void testOpenOpenSRT() throws Exception {
		setOutput();
		// Initializing input
		String[] cmdParts = new String[] {"open", "SRT"};
		(new CmdOpen()).execute(cmdParts);
		
		String expected = "Current algorithm filter: Shortest Remaining Time Next\n"
				+ String.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s\n", 
						"Case", 
						"Type",
						"Duration",
						"CPU Util",
						"Avg Turnaround",
						"Max Turnaround",
						"Avg Queuing",
						"Max Queuing");
		
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	
	@Test
	void testOpenOpenSPN() throws Exception {
		setOutput();
		// Initializing input
		String[] cmdParts = new String[] {"open", "SPN"};
		(new CmdOpen()).execute(cmdParts);
		
		String expected = "Current algorithm filter: Shortest Process Next\nCase           Type           Duration       CPU Util       Avg Turnaround Max Turnaround Avg Queuing    Max Queuing    \n";
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	
	@Test
	void testOpenOpenAlgorithm() throws Exception {		
		
		// Initializing input
		String[] cmdParts = new String[] {"readfile", "./src/TestSamples/1-perfect.txt"};
		(new CmdReadFile()).execute(cmdParts);
		
		cmdParts = new String[] {"schedule"};
		(new CmdSchedule()).execute(cmdParts);
		
		cmdParts = new String[] {"open", "SPN"};
		(new CmdOpen()).execute(cmdParts);
		setOutput();
		
		cmdParts = new String[] {"open", "FCFS"};
		(new CmdOpen()).execute(cmdParts);
		
		String expected = "There is already algorithm open.\n";
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	
	@Test
	void testOpenOpenCase() throws Exception {	
		String[] cmdParts = new String[] {"open", "SRT"};
		(new CmdOpen()).execute(cmdParts);
		setOutput();
		// Initializing input
		cmdParts = new String[] {"readfile", "./src/TestSamples/1-perfect.txt"};
		(new CmdReadFile()).execute(cmdParts);
		
		cmdParts = new String[] {"schedule"};
		(new CmdSchedule()).execute(cmdParts);
		
		cmdParts = new String[] {"open", "0"};
		(new CmdOpen()).execute(cmdParts);		
		
		String expected = "1 unscheduled inputs scheduled successfully.\n"
				+ "---------------------------------------------------\n"
				+ "Shortest Remaining Time Next\n"
				+ "Process #1: 0 1 13 17 \n"
				+ "CPU Queuing Time: 0\n"
				+ "Keyboard Queuing Time: 0\n"
				+ "Turnaround Time: 17\n"
				+ "\n"
				+ "Process #0: 1 4 22 27 36 39 \n"
				+ "CPU Queuing Time: 1\n"
				+ "Keyboard Queuing Time: 17\n"
				+ "Turnaround Time: 39\n"
				+ "\n"
				+ "Process #3: 7 13 39 43 \n"
				+ "CPU Queuing Time: 5\n"
				+ "Keyboard Queuing Time: 14\n"
				+ "Turnaround Time: 38\n"
				+ "\n"
				+ "Process #2: 4 7 27 32 43 48 \n"
				+ "CPU Queuing Time: 2\n"
				+ "Keyboard Queuing Time: 19\n"
				+ "Turnaround Time: 46\n"
				+ "\n"
				+ "\n"
				+ "";
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	
	@Test
	void testOpenOpenCase2() throws Exception {		
		
		// Initializing input
		String[] cmdParts = new String[] {"readfile", "./src/TestSamples/1-perfect.txt"};
		(new CmdReadFile()).execute(cmdParts);
		
		cmdParts = new String[] {"schedule"};
		(new CmdSchedule()).execute(cmdParts);
		
		cmdParts = new String[] {"open", "0"};
		(new CmdOpen()).execute(cmdParts);
		
		setOutput();
		
		cmdParts = new String[] {"open", "0"};
		(new CmdOpen()).execute(cmdParts);
		
		String expected =  "There is already case open.\n";
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	
	// Handling console output
	PrintStream oldPrintStream;
	ByteArrayOutputStream bos;
	
	private void setOutput() throws Exception {
		oldPrintStream = System.out;
	    bos = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(bos));
	}
	
	private String getOutput() { 
	    System.setOut(oldPrintStream);
	    return bos.toString();
	}
}
