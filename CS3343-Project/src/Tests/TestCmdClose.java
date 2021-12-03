package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Commands.CmdClose;
import Commands.CmdReadFile;
import Project.MainSystem;

class TestCmdClose {

	ByteArrayOutputStream outContent = new ByteArrayOutputStream();


	@BeforeEach
	void setUp() {
	    System.setOut(new PrintStream(outContent));
	}

	@AfterEach
	void tearDown() {
	}
	
	@Test
	void testCmdCloseInTheRoot() {
		
		// Getting instance of the Main System
		MainSystem system = MainSystem.getInstance();
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile", "./src/TestSamples/1-perfect.txt"};
		
		// Running execute new Cmd Display
		(new CmdReadFile()).execute(cmdParts);
		
		cmdParts = new String[] {"close"};
		
		// Running execute new Cmd close
		(new CmdClose()).execute(cmdParts);
		
		boolean expected = false;
		
		String expected_str = "You are already in the root directory";
		
		boolean actual = system.close();
		
		assertEquals(expected, actual);
		
		assertEquals(expected_str, outContent.toString().trim());
		
	}
	
	@Test
	void testCmdCloseInTheRoot2() {
		
		// Getting instance of the Main System
		MainSystem system = MainSystem.getInstance();
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile", "./src/TestSamples/1-perfect.txt"};
		
		// Running execute new Cmd Display
		(new CmdReadFile()).execute(cmdParts);
		
		cmdParts = new String[] {"close"};
		
		// Running execute new Cmd close
		(new CmdClose()).execute(cmdParts);
		
		boolean expected = false;
		
		String expected_str = "You are already in the root directory";
		
		boolean actual = system.close();
		
		assertEquals(expected, actual);
		
		assertEquals(expected_str, outContent.toString().trim());
		
	}
	
	@Test
	void testCmdCloseInAlgo() {	
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile", "./src/TestSamples/1-perfect.txt"};
		
		// Running execute new Cmd Display
		(new CmdReadFile()).execute(cmdParts);
		
		// Running schedule command
		cmdParts = new String[]{"schedule"};
		(new CmdClose()).execute(cmdParts);
		
		// Running open command
		cmdParts = new String[]{"open", "FCFS"};
		(new CmdClose()).execute(cmdParts);
		
		// Running open command
		cmdParts = new String[]{"close"};
		(new CmdClose()).execute(cmdParts);
		
		boolean expected = true; 
		
		assertEquals(expected, true);
		
	}

}
