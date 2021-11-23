package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Commands.CmdReadFile;
import Commands.CmdSchedule;
import Exceptions.ExCaseNotFound;
import Exceptions.ExInvalidServiceType;
import Project.AlgorithmType;
import Project.MainSystem;
import Project.Process;
import Project.Service;

class TestMainSystem {
	
	@BeforeEach
	public void setUp() throws Exception {}
	public void tearDown() {}

	@Test
	void testGetAllInputs() throws ExInvalidServiceType {
		
		// Getting instance of the Main System
		MainSystem system = MainSystem.getInstance();
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile", "./src/TestSamples/1-perfect.txt"};
				
		// Running execute new Cmd Read FIle
		(new CmdReadFile()).execute(cmdParts);
		
		// Initializing actual result
		ArrayList<ArrayList<Process>> actual = system.getAllInputs();
		
		// Initializing expected result
		ArrayList<ArrayList<Process>> expected = new ArrayList<>();
		ArrayList<Process> expected_small = new ArrayList<>();
		
		// Initializing dummy services
				ArrayList<Service> services0 = new ArrayList<>();
				services0.add(system.createService("C", "3"));
				services0.add(system.createService("K", "9"));
				services0.add(system.createService("C", "5"));
				services0.add(system.createService("K", "1"));
				services0.add(system.createService("C", "3"));
				
				// Creating Process with dummy services
				Process p0 = system.createProcess("0", 0, services0);
				expected_small.add(p0);
				
				// Initializing dummy services
				ArrayList<Service> services1 = new ArrayList<>();
				services1.add(system.createService("C", "1"));
				services1.add(system.createService("K", "12"));
				services1.add(system.createService("C", "4"));
				
				// Creating Process with dummy services
				Process p1 = system.createProcess("1", 0, services1);
				expected_small.add(p1);
				
				// Initializing dummy services
				ArrayList<Service> services2 = new ArrayList<>();
				services2.add(system.createService("C", "3"));
				services2.add(system.createService("K", "5"));
				services2.add(system.createService("C", "5"));
				services2.add(system.createService("K", "7"));
				services2.add(system.createService("C", "5"));
				
				// Creating Process with dummy services
				Process p2 = system.createProcess("2", 2, services2);
				expected_small.add(p2);
				
				// Initializing dummy services
				ArrayList<Service> services3 = new ArrayList<>();
				services3.add(system.createService("C", "6"));
				services3.add(system.createService("K", "8"));
				services3.add(system.createService("C", "5"));
				
				// Creating Process with dummy services
				Process p3 = system.createProcess("3", 5, services3);
				expected_small.add(p3);
				
		expected.add(expected_small);
		
		
		// Checking the Results (Asserting the expected arraylist of arraylists attributes)
		for (int i = 0; i < expected.size(); ++i) {
			for (int j = 0; j < expected.get(i).size(); ++j) {
				assertEquals(expected.get(i).get(j).getId(), actual.get(i).get(j).getId());
				assertEquals(expected.get(i).get(j).getArrivalTime(), actual.get(i).get(j).getArrivalTime());
				assertEquals(expected.get(i).get(j).getServicesCount(), actual.get(i).get(j).getServicesCount());
			}
		}
	}
	
	@Test
	void testStopReading() throws ExInvalidServiceType {
		

		// Getting instance of the Main System
		MainSystem system = MainSystem.getInstance();
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/2-number_of_processes_float_number.txt"};
				
		// Running execute new Cmd Read FIle
		(new CmdReadFile()).execute(cmdParts);
		
		// Initializing actual result
		int actual = system.getAllInputs().size() - 1;
		
		// Initializing expected result
		int expected = -1;
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	void testStartReading() throws ExInvalidServiceType {
		

		// Getting instance of the Main System
		MainSystem system = MainSystem.getInstance();
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/1-perfect.txt"};
				
		// Running execute new Cmd Read FIle
		(new CmdReadFile()).execute(cmdParts);
		
		// Initializing actual result
		int actual = system.getAllInputs().size() - 1;
		
		// Initializing expected result
		int expected = 1;
		
		assertEquals(expected, actual);
		
	}
	
//	@Test
//	void testClear() throws ExInvalidServiceType {
//		
//
//		// Getting instance of the Main System
//		MainSystem system = MainSystem.getInstance();
//		
//		// Initializing cmdParts that will be inputted
//		String[] cmdParts = new String[]{"readfile","./src/TestSamples/1-perfect.txt"};
//				
//		// Running execute new Cmd Read FIle
//		(new CmdReadFile()).execute(cmdParts);
//		
//		// Initializing cmdParts that will be inputted
//		cmdParts = new String[]{"schedule"};
//				
//		// Running execute new Cmd Read FIle
//		(new CmdSchedule()).execute(cmdParts);
//		
////		system.clear();
//		
//		
//		// Initializing actual result
//		int actual = system.getAllInputs().size() - 1;
//		
//		// Initializing expected result
//		int expected = -1;
//		
//		assertEquals(expected, actual);
//		
//	}
//
	@Test
	void testScheduleAlgorithmsWith0() {
		

		// Getting instance of the Main System
		MainSystem system = MainSystem.getInstance();
		
		int actual = system.scheduleAlgorithms();
		
		// Initializing expected result
		int expected = 0;
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	void testScheduleAlgorithmsWith1() {
		

		// Getting instance of the Main System
		MainSystem system = MainSystem.getInstance();
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/1-perfect.txt"};
				
		// Running execute new Cmd Read FIle
		(new CmdReadFile()).execute(cmdParts);
		
		int actual = system.scheduleAlgorithms();
		
		// Initializing expected result
		int expected = 1;
		
		assertEquals(expected, actual);
		
	}

	@Test
	void testScheduleAlgorithmsWith2() {
		

		// Getting instance of the Main System
		MainSystem system = MainSystem.getInstance();
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/1-perfect.txt"};
				
		// Running execute new Cmd Read FIle
		(new CmdReadFile()).execute(cmdParts);
		
		// Initializing cmdParts that will be inputted
		cmdParts = new String[]{"readfile","./src/TestSamples/1-perfect.txt"};
				
		// Running execute new Cmd Read FIle
		(new CmdReadFile()).execute(cmdParts);
		
		int actual = system.scheduleAlgorithms();
		
		// Initializing expected result
		int expected = 2;
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	void testOpenAlgoWithNone() {
		

		// Getting instance of the Main System
		MainSystem system = MainSystem.getInstance();
		
		boolean actual = system.openAlgo(AlgorithmType.None);
		
		// Initializing expected result
		boolean expected = true;
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	void testOpenAlgoWithNotNone() {
		

		// Getting instance of the Main System
		MainSystem system = MainSystem.getInstance();
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/1-perfect.txt"};
				
		// Running execute new Cmd Read FIle
		(new CmdReadFile()).execute(cmdParts);
		
		boolean actual = system.openAlgo(AlgorithmType.FCFS);
		
		// Initializing expected result
		boolean expected = true;
		
		assertEquals(expected, actual);
		
	}
	
//	@Test
//	void testOpenCaseWithAlreadyOpen() throws ExCaseNotFound {
//
//		// Getting instance of the Main System
//		MainSystem system = MainSystem.getInstance();
//		
//		// Initializing cmdParts that will be inputted
//		String[] cmdParts = new String[]{"readfile","./src/TestSamples/1-perfect.txt"};
//		
//		// Running execute new Cmd Read FIle
//		(new CmdReadFile()).execute(cmdParts);
//		
//		// Initializing cmdParts that will be inputted
//		cmdParts = new String[]{"schedule"};
//		
//		// Running execute new Cmd Read FIle
//		(new CmdSchedule()).execute(cmdParts);
//		
//		
//		boolean wws = system.openCase(0);
//		boolean expected = false;
//		boolean actual = system.openCase(1);
//		assertEquals(expected, actual);
//		
//	}
	
	@Test
	void testOpenCaseWithNormal() throws ExCaseNotFound {

		// Getting instance of the Main System
		MainSystem system = MainSystem.getInstance();
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/1-perfect.txt"};
		
		// Running execute new Cmd Read FIle
		(new CmdReadFile()).execute(cmdParts);
		
		// Initializing cmdParts that will be inputted
		cmdParts = new String[]{"schedule"};
		
		// Running execute new Cmd Read FIle
		(new CmdSchedule()).execute(cmdParts);
		
		
		boolean expected = true;
		boolean actual = system.openCase(0);
		assertEquals(expected, actual);
		
	}
	
//	@Test
//	void testOpenCaseWithNotFound() throws ExCaseNotFound {
//
//		// Getting instance of the Main System
//		MainSystem system = MainSystem.getInstance();
//		
////		// Initializing cmdParts that will be inputted
////		String[] cmdParts = new String[]{"readfile","./src/TestSamples/1-perfect.txt"};
//
//		
//		ExCaseNotFound ex = assertThrows(ExCaseNotFound.class, () -> {system.openCase(2);});
//	}

	
}
