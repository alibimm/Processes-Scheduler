package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Commands.CmdClose;
import Commands.CmdReadFile;
import Exceptions.ExInsufficientCommandArguments;
import Project.MainSystem;

class TestCmdClose {

	ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@BeforeEach
	void setUp() {
		MainSystem system = MainSystem.getInstance();
		system.clear();
	    System.setOut(new PrintStream(outContent));
	}
	
	@Test
	void testCmdCloseInTheRoot() throws ExInsufficientCommandArguments {
		
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
	void testCmdCloseInAlgo() throws Exception {
		
		// Initializing cmdParts that will be inputted

		String[] cmdParts = new String[]{"readfile", "./src/TestSamples/1-perfect.txt"};
		
		(new CmdReadFile()).execute(cmdParts);
		
		cmdParts = new String[]{"schedule"};
		(new CmdClose()).execute(cmdParts);
		
		cmdParts = new String[]{"open", "FCFS"};
		(new CmdClose()).execute(cmdParts);
		
		cmdParts = new String[]{"close"};
		(new CmdClose()).execute(cmdParts);
		
		String expected = "You are already in the root directory"
				+ "\nYou are already in the root directory"
				+ "\nYou are already in the root directory";
		
		String actual = outContent.toString().trim();
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	void testCmdCloseInCase() throws Exception {
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile", "./src/TestSamples/1-perfect.txt"};
		
		// Running execute new Cmd Display
		(new CmdReadFile()).execute(cmdParts);
		
		// Running schedule command
		cmdParts = new String[]{"schedule"};
		(new CmdClose()).execute(cmdParts);
		
		// Running open command
		cmdParts = new String[]{"open", "0"};
		(new CmdClose()).execute(cmdParts);
		
		// Running close command
		cmdParts = new String[]{"close"};
		(new CmdClose()).execute(cmdParts);
		
		String expected = "You are already in the root directory"
				+ "\nYou are already in the root directory"
				+ "\nYou are already in the root directory";
		
		String actual = outContent.toString().trim();
		
		assertEquals(expected, actual);
		
	}

	
}
