package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import Commands.CmdDisplay;
import Commands.CmdOpen;
import Commands.CmdReadFile;
import Commands.CmdSchedule;
import Project.MainSystem;

class TestCmdDisplay {
	
	@Test
	void testCmdDisplayInStart() throws Exception {
		setOutput();
		
		// Cleaning main system, because it is singleton
		MainSystem system = MainSystem.getInstance();
		system.clear();
		
		String[] cmdParts = new String[]{"display"};
		(new CmdDisplay()).execute(cmdParts);
		
		String actual = getOutput();
		
		String expected = "Scheduled cases:\n"
				+ "Unscheduled files: 0\n";
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testCmdDisplayAfterSchedule() throws Exception {
		setOutput();
		
		// Cleaning main system, because it is singleton
		MainSystem system = MainSystem.getInstance();
		system.clear();

		// Inputting file
		String[] cmdParts = new String[] {"readfile", "./src/TestSamples/1-perfect.txt"};
		(new CmdReadFile()).execute(cmdParts);
		
		cmdParts = new String[] {"schedule"};
		(new CmdSchedule()).execute(cmdParts);
		
		cmdParts = new String[]{"display"};
		(new CmdDisplay()).execute(cmdParts);
		
		String expected = "1 unscheduled inputs scheduled successfully.\n"
				+ "Scheduled cases:\n"
				+ "Case 0\n"
				+ "Unscheduled files: 0\n"
				+ "";
		
		String actual = getOutput();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testCmdDisplayInCase() throws Exception {
		setOutput();
		
		// Cleaning main system, because it is singleton
		MainSystem system = MainSystem.getInstance();
		system.clear();
		
		String[] cmdParts = new String[] {"readfile", "./src/TestSamples/1-perfect.txt"};
		(new CmdReadFile()).execute(cmdParts);
		
		cmdParts = new String[] {"schedule"};
		(new CmdSchedule()).execute(cmdParts);
		
		cmdParts = new String[] {"open", "0"};
		(new CmdOpen()).execute(cmdParts);
		
		String expected = "1 unscheduled inputs scheduled successfully."
				+ "\n"
				+ "Type           Duration       CPU Util       Avg Turnaround Max Turnaround Avg Queuing    Max Queuing    \n"
				+ "FCFS           50             0.80           39.50          48.00          19.00          24.00          \n"
				+ "RR             50             0.80           39.50          48.00          19.00          24.00          \n"
				+ "FB             50             0.80           37.75          48.00          17.25          23.00          \n"
				+ "SPN            48             0.83           35.25          46.00          14.75          22.00          \n"
				+ "SRT            48             0.81           35.00          46.00          14.50          21.00          \n"
				+ "HRRN           50             0.80           39.50          48.00          19.00          24.00          \n"
				+ "\n"
				+ "";
		
		String actual = getOutput();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testCmdDisplayInCaseInAlgo() throws Exception {
		setOutput();
		
		// Cleaning main system, because it is singleton
		MainSystem system = MainSystem.getInstance();
		system.clear();
		
		String expected = "";
		String[] cmdParts = new String[] {"readfile", "./src/TestSamples/1-perfect.txt"};
		(new CmdReadFile()).execute(cmdParts);
		
		cmdParts = new String[] {"schedule"};
		(new CmdSchedule()).execute(cmdParts);
		
		expected += "1 unscheduled inputs scheduled successfully.\n";
		
		cmdParts = new String[] {"open", "0"};
		(new CmdOpen()).execute(cmdParts);
		
		expected += "Type           Duration       CPU Util       Avg Turnaround Max Turnaround Avg Queuing    Max Queuing    \n"
				+ "FCFS           50             0.80           39.50          48.00          19.00          24.00          \n"
				+ "RR             50             0.80           39.50          48.00          19.00          24.00          \n"
				+ "FB             50             0.80           37.75          48.00          17.25          23.00          \n"
				+ "SPN            48             0.83           35.25          46.00          14.75          22.00          \n"
				+ "SRT            48             0.81           35.00          46.00          14.50          21.00          \n"
				+ "HRRN           50             0.80           39.50          48.00          19.00          24.00          \n"
				+ "\n";
		
		cmdParts = new String[] {"open", "FCFS"};
		(new CmdOpen()).execute(cmdParts);
		
		expected += "---------------------------------------------------\n"
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
				+ "\n";
		
		String actual = getOutput();
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	void testCmdDisplayInAlgo() throws Exception {
		setOutput();
		
		// Cleaning main system, because it is singleton
		MainSystem system = MainSystem.getInstance();
		system.clear();
		
		String expected = "";
		
		String[] cmdParts = new String[] {"readfile", "./src/TestSamples/1-perfect.txt"};
		(new CmdReadFile()).execute(cmdParts);
		
		cmdParts = new String[] {"schedule"};
		(new CmdSchedule()).execute(cmdParts);
		
		expected += "1 unscheduled inputs scheduled successfully.\n";
		
		cmdParts = new String[] {"open", "FCFS"};
		(new CmdOpen()).execute(cmdParts);
		
		expected += "Current algorithm filter: First Come First Serve\n"
				+ "Case           Type           Duration       CPU Util       Avg Turnaround Max Turnaround Avg Queuing    Max Queuing    \n"
				+ "Case #0        FCFS           50             0.80           39.50          48.00          19.00          24.00          \n";
		
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
