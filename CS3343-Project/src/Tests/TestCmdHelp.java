package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Commands.CmdHelp;
import Exceptions.ExInsufficientCommandArguments;
import Exceptions.ExInvalidInput;
import Exceptions.ExInvalidServiceType;

class TestCmdHelp {
	
	ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@BeforeAll
	static void setUpBeforeClass() {
	}

	@AfterAll
	static void tearDownAfterClass() {
	}

	@BeforeEach
	void setUp() {
	    System.setOut(new PrintStream(outContent));
	}

	@AfterEach
	void tearDown() {
	}
	

	@Test
	void testHelp() throws ExInvalidServiceType, ExInvalidInput, ExInsufficientCommandArguments  {
		
		String[] cmdParts = new String[]{"help"};
		
		// Running execute new Cmd Help
		(new CmdHelp()).execute(cmdParts);
		
		String expected = "There are 8 commands used in various situations:\n"
				+ "\n"
				+ "Working area\n"
				+ "	readfile [filepathname]			Let the scheduler read the file\n"
				+ "	schedule				Start the scheduling\n"
				+ "	suggest					To get suggestions on best algorithm according to TurnAround Time for the given files\n"
				+ "	display					Print the current information\n"
				+ "	help					Get help for execution and navigation\n"
				+ "\n"
				+ "To navigate through given files and algorithms for specific results\n"
				+ "\n"
				+ "	open [index of file]			Open the detailed results of the specified file\n"
				+ "	open [algorithm]			Open the detailed results of the specified algorithm\n"
				+ "	close					Go back to previous information\n"
				+ "\n"
				+ "";
		
		assertEquals(expected, outContent.toString().trim());
	}
	
}

