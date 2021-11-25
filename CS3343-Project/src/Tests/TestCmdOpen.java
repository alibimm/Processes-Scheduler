package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import Commands.CmdClose;
import Commands.CmdOpen;
import Commands.CmdReadFile;
import Commands.CmdSchedule;
import Exceptions.ExCaseNotFound;
import Exceptions.ExInsufficientCommandArguments;

class TestCmdOpen {

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
		setOutput();
		String[] cmdParts = new String[] {"close"};
		(new CmdClose()).execute(cmdParts);
		// Initializing input
		cmdParts = new String[] {"open", "FCFS"};
		(new CmdOpen()).execute(cmdParts);
		
		String expected = "---------------------------------------------------\n"
				+ "First Come First Serve\n"
				+ "Process #1: 3 4 24 28 \n"
				+ "CPU Queuing Time: 3\n"
				+ "Keyboard Queuing Time: 8\n"
				+ "Turnaround Time: 28\n"
				+ "\n"
				+ "Process #3: 7 13 37 42 \n"
				+ "CPU Queuing Time: 2\n"
				+ "Keyboard Queuing Time: 16\n"
				+ "Turnaround Time: 37\n"
				+ "\n"
				+ "Process #0: 0 3 13 18 42 45 \n"
				+ "CPU Queuing Time: 5\n"
				+ "Keyboard Queuing Time: 19\n"
				+ "Turnaround Time: 45\n"
				+ "\n"
				+ "Process #2: 4 7 29 34 45 50 \n"
				+ "CPU Queuing Time: 2\n"
				+ "Keyboard Queuing Time: 21\n"
				+ "Turnaround Time: 48\n"
				+ "\n"
				+ "\n"
				+ "";
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	
	@Test
	void testOpenOpenRR() throws Exception {	
		setOutput();
		String[] cmdParts = new String[] {"close"};
		(new CmdClose()).execute(cmdParts);
		// Initializing input
		cmdParts = new String[] {"open", "RR"};
		(new CmdOpen()).execute(cmdParts);
		
		String expected = "---------------------------------------------------\n"
				+ "Round Robin\n"
				+ "Process #1: 3 4 24 28 \n"
				+ "CPU Queuing Time: 3\n"
				+ "Keyboard Queuing Time: 8\n"
				+ "Turnaround Time: 28\n"
				+ "\n"
				+ "Process #3: 7 12 12 13 37 42 \n"
				+ "CPU Queuing Time: 2\n"
				+ "Keyboard Queuing Time: 16\n"
				+ "Turnaround Time: 37\n"
				+ "\n"
				+ "Process #0: 0 3 13 18 42 45 \n"
				+ "CPU Queuing Time: 5\n"
				+ "Keyboard Queuing Time: 19\n"
				+ "Turnaround Time: 45\n"
				+ "\n"
				+ "Process #2: 4 7 29 34 45 50 \n"
				+ "CPU Queuing Time: 2\n"
				+ "Keyboard Queuing Time: 21\n"
				+ "Turnaround Time: 48\n"
				+ "\n"
				+ "\n"
				+ "";
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	
	@Test
	void testOpenOpenFB() throws Exception {	
		setOutput();
		String[] cmdParts = new String[] {"close"};
		(new CmdClose()).execute(cmdParts);
		// Initializing input
		cmdParts = new String[] {"open", "FB"};
		(new CmdOpen()).execute(cmdParts);
		
		String expected = "---------------------------------------------------\n"
				+ "Feedback\n"
				+ "Process #1: 3 4 24 28 \n"
				+ "CPU Queuing Time: 3\n"
				+ "Keyboard Queuing Time: 8\n"
				+ "Turnaround Time: 28\n"
				+ "\n"
				+ "Process #0: 0 3 12 17 34 37 \n"
				+ "CPU Queuing Time: 4\n"
				+ "Keyboard Queuing Time: 12\n"
				+ "Turnaround Time: 37\n"
				+ "\n"
				+ "Process #3: 7 12 17 18 38 43 \n"
				+ "CPU Queuing Time: 7\n"
				+ "Keyboard Queuing Time: 12\n"
				+ "Turnaround Time: 38\n"
				+ "\n"
				+ "Process #2: 4 7 29 34 45 50 \n"
				+ "CPU Queuing Time: 2\n"
				+ "Keyboard Queuing Time: 21\n"
				+ "Turnaround Time: 48\n"
				+ "\n"
				+ "\n"
				+ "";
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	
	@Test
	void testOpenOpenHRRN() throws Exception {		
		setOutput();
		String[] cmdParts = new String[] {"close"};
		(new CmdClose()).execute(cmdParts);
		// Initializing input
		cmdParts = new String[] {"open", "HRRN"};
		(new CmdOpen()).execute(cmdParts);
		
		String expected = "---------------------------------------------------\n"
				+ "Highest Response Ratio Next\n"
				+ "Process #1: 3 4 24 28 \n"
				+ "CPU Queuing Time: 3\n"
				+ "Keyboard Queuing Time: 8\n"
				+ "Turnaround Time: 28\n"
				+ "\n"
				+ "Process #3: 7 13 37 42 \n"
				+ "CPU Queuing Time: 2\n"
				+ "Keyboard Queuing Time: 16\n"
				+ "Turnaround Time: 37\n"
				+ "\n"
				+ "Process #0: 0 3 13 18 42 45 \n"
				+ "CPU Queuing Time: 5\n"
				+ "Keyboard Queuing Time: 19\n"
				+ "Turnaround Time: 45\n"
				+ "\n"
				+ "Process #2: 4 7 29 34 45 50 \n"
				+ "CPU Queuing Time: 2\n"
				+ "Keyboard Queuing Time: 21\n"
				+ "Turnaround Time: 48\n"
				+ "\n"
				+ "\n"
				+ "";
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	
	@Test
	void testOpenOpenSRT() throws Exception {		
		setOutput();
		String[] cmdParts = new String[] {"close"};
		(new CmdClose()).execute(cmdParts);
		// Initializing input
		cmdParts = new String[] {"open", "SRT"};
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
	void testOpenOpenCase() throws Exception {		
		setOutput();
		// Initializing input
		String[] cmdParts = new String[] {"readfile", "./src/TestSamples/1-perfect.txt"};
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
		setOutput();
		// Initializing input
		String[] cmdParts = new String[] {"readfile", "./src/TestSamples/1-perfect.txt"};
		(new CmdReadFile()).execute(cmdParts);
		
		cmdParts = new String[] {"schedule"};
		(new CmdSchedule()).execute(cmdParts);
		
		cmdParts = new String[] {"open", "0"};
		(new CmdOpen()).execute(cmdParts);
		
		cmdParts = new String[] {"open", "0"};
		(new CmdOpen()).execute(cmdParts);
		
		String expected = "1 unscheduled inputs scheduled successfully.\n"
				+ "There is already case open.\n"
				+ "There is already case open.\n"
				+ "";
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

	  private String getOutput() { // throws Exception
	    System.setOut(oldPrintStream);
	    return bos.toString();
	  }


}
