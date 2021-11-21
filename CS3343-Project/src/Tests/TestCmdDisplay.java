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
	
	ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tear() {
	}

	@BeforeEach
	void setUp() {
	    System.setOut(new PrintStream(outContent));
	}

	@AfterEach
	void tearDown() {
	}
	
	
	@Test
	void testCmdDisplayInStart() {
		
		// Getting instance of the Main System
		MainSystem system = MainSystem.getInstance();
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"display"};
		
		// Running execute new Cmd Display
		(new CmdDisplay()).execute(cmdParts);
		
		String expected = "Scheduled cases:\n"
				+ "Unscheduled files: 0";
		
		assertEquals(expected, outContent.toString().trim());
		
	}
	
	@Test
	void testCmdDisplayAfterReadFile() {
		

		// Getting instance of the Main System
		MainSystem system = MainSystem.getInstance();
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile", "./src/TestSamples/1-perfect.txt"};
		
		// Running execute new Cmd Display
		(new CmdReadFile()).execute(cmdParts);
		
		cmdParts = new String[] {"display"};
		
		(new CmdDisplay()).execute(cmdParts);
		
		String expected = "Scheduled cases:\n"
				+ "Unscheduled files: 1\n";
		
		assertEquals(expected, outContent.toString().trim());
		
	}
	
	@Test
	void testCmdDisplayAfterSchedule() {
		

		// Getting instance of the Main System
		MainSystem system = MainSystem.getInstance();
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile", "./src/TestSamples/1-perfect.txt"};
		
		// Running execute new Cmd Display
		(new CmdReadFile()).execute(cmdParts);
		
		cmdParts = new String[] {"schedule"};
		
		(new CmdSchedule()).execute(cmdParts);
		
		String expected = "Scheduled cases:\n"
				+ "Unscheduled files: 1\n";
		
		assertEquals(expected, outContent.toString().trim());
		
	}

}
