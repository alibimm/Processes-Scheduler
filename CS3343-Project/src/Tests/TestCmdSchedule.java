package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import Commands.CmdReadFile;
import Commands.CmdSchedule;

class TestCmdSchedule {

	@Test
	void testScheduleAtStart() throws Exception {
		setOutput();
		
		String[] cmdParts = new String[] {"schedule"};
		(new CmdSchedule()).execute(cmdParts);
		
		String expected = "There are no unscheduled inputs.\n";
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	
	@Test
	void testScheduleAfterUpload() throws Exception {
		setOutput();
		
		// Initializing
		String[] cmdParts = new String[] {"readfile", "./src/TestSamples/1-perfect.txt"};
		(new CmdReadFile()).execute(cmdParts);
		
		cmdParts = new String[] {"schedule"};
		(new CmdSchedule()).execute(cmdParts);
		
		String expected = "1 unscheduled inputs scheduled successfully.\n";
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
