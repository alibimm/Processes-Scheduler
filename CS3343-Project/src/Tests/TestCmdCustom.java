package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import Commands.CmdCustom;
import Exceptions.ExInsufficientCommandArguments;

class TestCmdCustom {

	@Test
	void testCmdCustomWrongInput() throws Exception {		
		// Initializing input
		String[] cmdParts = new String[] {"custom"};
		ExInsufficientCommandArguments ex = assertThrows(ExInsufficientCommandArguments.class, () -> {
			(new CmdCustom()).execute(cmdParts);
		});
		String actual = ex.getMessage();
		String expected = "Custom Command should have 2 arguments";
		assertTrue(actual.contains(expected));
	}
	
	@Test
	void testCmdCustomWrongInput2() throws Exception {		
		setOutput();
		// Initializing input
		String[] cmdParts = new String[] {"custom", "qwerty"};
		(new CmdCustom()).execute(cmdParts);
		
		String expected = "The is no such indicator for the best algorithm, please use any of: AQT, ATRT, ARTS, CpuUtil.\n";
		String actual = getOutput();
		System.out.println(actual);
		assertEquals(expected, actual);
	}
	
	@Test
	void testCmdCustomAQT() throws Exception {		
		setOutput();
		// Initializing input
		String[] cmdParts = new String[] {"custom", "AQT"};
		(new CmdCustom()).execute(cmdParts);
		
		String expected = "Changed the best algorithm indicator to AQT.\n";
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	
	@Test
	void testCmdCustomATRT() throws Exception {		
		setOutput();
		// Initializing input
		String[] cmdParts = new String[] {"custom", "ATRT"};
		(new CmdCustom()).execute(cmdParts);
		
		String expected = "Changed the best algorithm indicator to ATRT.\n";
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	
	@Test
	void testCmdCustomARTS() throws Exception {		
		setOutput();
		// Initializing input
		String[] cmdParts = new String[] {"custom", "ARTS"};
		(new CmdCustom()).execute(cmdParts);
		
		String expected = "Changed the best algorithm indicator to ARTS.\n";
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	
	@Test
	void testCmdCustomCpuUtil() throws Exception {		
		setOutput();
		// Initializing input
		String[] cmdParts = new String[] {"custom", "CpuUtil"};
		(new CmdCustom()).execute(cmdParts);
		
		String expected = "Changed the best algorithm indicator to CpuUtil.\n";
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
