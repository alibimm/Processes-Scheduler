package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;




import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Commands.CmdDisplay;
import Commands.CmdReadFile;
import Commands.CmdSchedule;
import Project.MainSystem;

class TestCmdDisplay {
	
//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//
//	}
//
//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//	}
//
//	@BeforeEach
//	void setUp() throws Exception {
//	}
//
//	@AfterEach
//	void tearDown() throws Exception {
//	}
	
	@Test
	void testCmdDisplayInStart() throws Exception {
		
		setOutput();
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"display"};
		
		// Running execute new Cmd Display
		(new CmdDisplay()).execute(cmdParts);
		
		String expected = "Scheduled cases:\n"
				+ "Unscheduled files: 2\n";
		
		String wws = getOutput();
		
		assertEquals(expected, getOutput());
		
	}
	


	@Test
	void testCmdDisplayAfterReadFile2() throws Exception {

		setOutput();
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile", "./src/TestSamples/1-perfect.txt"};
		
		// Running execute new Cmd Display
		(new CmdReadFile()).execute(cmdParts);
		
		
		// Initializing cmdParts that will be inputted
		cmdParts = new String[]{"display"};
		
		// Running execute new Cmd Display
		(new CmdDisplay()).execute(cmdParts);
		
		String expected =  
				 "Scheduled cases:\n"
				+ "Unscheduled files: 2\n";
		
		assertEquals(expected, getOutput());
		
	}
	
	@Test
	void testCmdDisplayAfterReadFile() throws Exception {

		setOutput();
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile", "./src/TestSamples/1-perfect.txt"};
		
		// Running execute new Cmd Display
		(new CmdReadFile()).execute(cmdParts);
		
		
		
		// Initializing cmdParts that will be inputted
		cmdParts = new String[]{"display"};
		
		// Running execute new Cmd Display
		(new CmdDisplay()).execute(cmdParts);
		
		String expected = "Scheduled cases:\n"
				+ "Unscheduled files: 1\n";
		
		assertEquals(expected, getOutput());
		
	}
	
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
