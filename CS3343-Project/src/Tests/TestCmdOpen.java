package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import Commands.CmdOpen;
import Commands.CmdReadFile;
import Commands.CmdSchedule;
import Exceptions.ExCaseNotFound;
import Exceptions.ExInsufficientCommandArguments;

class TestCmdOpen {

	@Test
	void testOpenAtStart() throws Exception {		
		// Initializing input
		String[] cmdParts = new String[] {"open", "0"};
		ExCaseNotFound ex = assertThrows(ExCaseNotFound.class, () -> {(new CmdOpen()).execute(cmdParts);});
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
		String[] cmdParts = new String[] {"open", "1.2"};
		ExInsufficientCommandArguments ex = assertThrows(ExInsufficientCommandArguments.class, () -> {(new CmdOpen()).execute(cmdParts);});
	}
	
	@Test
	void testOpenOpenFCFS() throws Exception {		
		// Initializing input
		String[] cmdParts = new String[] {"open", "FCFS"};
		(new CmdOpen()).execute(cmdParts);
	}
	
	@Test
	void testOpenOpenRR() throws Exception {		
		// Initializing input
		String[] cmdParts = new String[] {"open", "RR"};
		(new CmdOpen()).execute(cmdParts);
	}
	
	@Test
	void testOpenOpenFB() throws Exception {		
		// Initializing input
		String[] cmdParts = new String[] {"open", "FB"};
		(new CmdOpen()).execute(cmdParts);
	}
	
	@Test
	void testOpenOpenHRRN() throws Exception {		
		// Initializing input
		String[] cmdParts = new String[] {"open", "HRRN"};
		(new CmdOpen()).execute(cmdParts);
	}
	
	@Test
	void testOpenOpenSRT() throws Exception {		
		// Initializing input
		String[] cmdParts = new String[] {"open", "SRT"};
		(new CmdOpen()).execute(cmdParts);
	}
	
	@Test
	void testOpenOpenSPN() throws Exception {		
		// Initializing input
		String[] cmdParts = new String[] {"open", "SPN"};
		(new CmdOpen()).execute(cmdParts);
	}
	
	@Test
	void testOpenOpenCase() throws Exception {		
		// Initializing input
		String[] cmdParts = new String[] {"readfile", "./src/TestSamples/1-perfect.txt"};
		(new CmdReadFile()).execute(cmdParts);
		
		cmdParts = new String[] {"schedule"};
		(new CmdSchedule()).execute(cmdParts);
		
		cmdParts = new String[] {"open", "0"};
		(new CmdOpen()).execute(cmdParts);
	}
	
	@Test
	void testOpenOpenCase2() throws Exception {		
		// Initializing input
		String[] cmdParts = new String[] {"readfile", "./src/TestSamples/1-perfect.txt"};
		(new CmdReadFile()).execute(cmdParts);
		
		cmdParts = new String[] {"schedule"};
		(new CmdSchedule()).execute(cmdParts);
		
		cmdParts = new String[] {"open", "0"};
		(new CmdOpen()).execute(cmdParts);
		
		cmdParts = new String[] {"open", "0"};
		(new CmdOpen()).execute(cmdParts);
	}

}
