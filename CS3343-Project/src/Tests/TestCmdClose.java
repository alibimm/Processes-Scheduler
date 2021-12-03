package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
	
	@Test
	void testCmdCloseInTheRoot() {
		
		MainSystem system = MainSystem.getInstance();
		
		// Initializing cmdParts that will be input
		String[] cmdParts = new String[]{"readfile", "./src/TestSamples/1-perfect.txt"};
		
		(new CmdReadFile()).execute(cmdParts);
		
		cmdParts = new String[] {"close"};
		
		(new CmdClose()).execute(cmdParts);
		
		boolean expected = false;
		
		String expected_str = "You are already in the root directory";
		
		boolean actual = system.close();
		
		assertEquals(expected, actual);
		
		assertEquals(expected_str, outContent.toString().trim());
		
	}
	
	@Test
	void testCmdCloseInTheRoot2() {
		
		MainSystem system = MainSystem.getInstance();
		
		// Initializing cmdParts that will be input
		String[] cmdParts = new String[]{"readfile", "./src/TestSamples/1-perfect.txt"};
		
		(new CmdReadFile()).execute(cmdParts);
		
		cmdParts = new String[] {"close"};
		
		(new CmdClose()).execute(cmdParts);
		
		boolean expected = false;
		
		String expected_str = "You are already in the root directory";
		
		boolean actual = system.close();
		
		assertEquals(expected, actual);
		
		assertEquals(expected_str, outContent.toString().trim());
		
	}
	
	@Test
	void testCmdCloseInAlgo() {	
		// Initializing cmdParts that will be input
		String[] cmdParts = new String[]{"readfile", "./src/TestSamples/1-perfect.txt"};
		
		(new CmdReadFile()).execute(cmdParts);
		
		cmdParts = new String[]{"schedule"};
		(new CmdClose()).execute(cmdParts);
		
		cmdParts = new String[]{"open", "FCFS"};
		(new CmdClose()).execute(cmdParts);
		
		cmdParts = new String[]{"close"};
		(new CmdClose()).execute(cmdParts);
		
		boolean expected = true; 
		
		assertEquals(expected, true);
		
	}

}
