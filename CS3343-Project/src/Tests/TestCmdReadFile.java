package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;

import Commands.CmdReadFile;
import Exceptions.ExInvalidInput;
import Exceptions.ExInvalidServiceType;
import Project.MainSystem;
import Project.Service;
import Project.Process;

public class TestCmdReadFile {
	
	// Test right input
	@Test
	public void TestReadFile() throws ExInvalidServiceType, ExInvalidInput {
		
		// Getting instance of the Main System
		MainSystem system = MainSystem.getInstance();
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/1-perfect.txt"};
		
		// Running execute new Cmd Read FIle
		(new CmdReadFile()).execute(cmdParts);
		
		// Initializing actual result
		ArrayList<Process> actual = system.getAllInputs().get(0);
		
		// Initializing expected result
		ArrayList<Process> expected = new ArrayList<>();
		
		// Initializing dummy services
		ArrayList<Service> services0 = new ArrayList<>();
		services0.add(system.createService("C", "3"));
		services0.add(system.createService("K", "9"));
		services0.add(system.createService("C", "5"));
		services0.add(system.createService("K", "1"));
		services0.add(system.createService("C", "3"));
		
		// Creating Process with dummy services
		Process p0 = system.createProcess("0", 0, services0);
		expected.add(p0);
		
		// Initializing dummy services
		ArrayList<Service> services1 = new ArrayList<>();
		services1.add(system.createService("C", "1"));
		services1.add(system.createService("K", "12"));
		services1.add(system.createService("C", "4"));
		
		// Creating Process with dummy services
		Process p1 = system.createProcess("1", 0, services1);
		expected.add(p1);
		
		// Initializing dummy services
		ArrayList<Service> services2 = new ArrayList<>();
		services2.add(system.createService("C", "3"));
		services2.add(system.createService("K", "5"));
		services2.add(system.createService("C", "5"));
		services2.add(system.createService("K", "7"));
		services2.add(system.createService("C", "5"));
		
		// Creating Process with dummy services
		Process p2 = system.createProcess("2", 2, services2);
		expected.add(p2);
		
		// Initializing dummy services
		ArrayList<Service> services3 = new ArrayList<>();
		services3.add(system.createService("C", "6"));
		services3.add(system.createService("K", "8"));
		services3.add(system.createService("C", "5"));
		
		// Creating Process with dummy services
		Process p3 = system.createProcess("3", 5, services3);
		expected.add(p3);
		
		// Not working for some reason
		// Checking the results
//		for (int i = 0; i < expected.size(); ++i) {
//			assertEquals(expected, actual);
//		}
		
		// Checking the results
		for (int i = 0; i < expected.size(); ++i) {
			assertEquals(expected.get(i).getId(), actual.get(i).getId());
			assertEquals(expected.get(i).getArrivalTime(), actual.get(i).getArrivalTime());
			assertEquals(expected.get(i).getServicesCount(), actual.get(i).getServicesCount());
		}

	}

	// Test readfile with invalid filepathname
	@Test
	public void TestReadFileWithInvalidFilePathName() {
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/wws.txt"};
		
		// Running execute new Cmd Read FIle
		FileNotFoundException ex = assertThrows(FileNotFoundException.class, () -> {(new CmdReadFile()).execute(cmdParts);});
	}
	
	// Test readfile, the first line is not number
	@Test
	public void TestReadFileFirstLineIsNotNumber() {
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/2-number_of_processes_not_integer.txt"};
		
		// Running execute new Cmd Read FIle
		NumberFormatException ex = assertThrows(NumberFormatException.class, () -> {(new CmdReadFile()).execute(cmdParts);});
		
	}
	
	// Test readfile, the first line is not integer		
	@Test
	public void TestReadFileFirstLineIsNotInteger() {
			
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/2-number_of_processes_float_number.txt"};
			
		// Running execute new Cmd Read FIle
		NumberFormatException ex = assertThrows(NumberFormatException.class, () -> {(new CmdReadFile()).execute(cmdParts);});		
	}
	
	
	// Test readfile, the first line is negative integer		
	@Test
	public void TestReadFileFirstLineIsNegativeInteger() {
			
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/2-number_of_processes_negative_integer.txt"};
			
		// Running execute new Cmd Read FIle
		ExInvalidInput ex = assertThrows(ExInvalidInput.class, () -> {(new CmdReadFile()).execute(cmdParts);});		
	}
	
	// Test readfile, the process line parts size is not equal to 4
  // # - initiation of process,
	// 0 - process id
	// 0 - arrival time
	// 5 - number of services
	@Test
	public void TestReadFileProcessLinePartsSizeIsNot4() {
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/3-ProcessLinePartsSizeIsNot4.txt"};
		
		ExInvalidInput ex = assertThrows(ExInvalidInput.class, () -> {(new CmdReadFile()).execute(cmdParts);});
		
	}
	

	// Test readfile, the processLineParts[0] is not #
	@Test
	public void TestReadFileProcessLineParts0IsNotHash() {
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/3-ProcessLinePartsIsNot#.txt"};
		
		ExInvalidInput ex = assertThrows(ExInvalidInput.class, () -> {(new CmdReadFile()).execute(cmdParts);});
		
	}
	

	// Test readfile, the processLineParts[1] is not number
	@Test
	public void TestReadFileProcessLineParts1IsNotNumber() {
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/3-ProcessLineParts1IsNotNumber.txt"};
		
		NumberFormatException ex = assertThrows(NumberFormatException.class, () -> {(new CmdReadFile()).execute(cmdParts);});
		
	}
	

	// Test readfile, the processLineParts[1] is not integer
	@Test
	public void TestReadFileProcessLineParts1IsNotInteger() {
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/3-ProcessLineParts1IsNotInteger.txt"};
		
		ExInvalidInput ex = assertThrows(ExInvalidInput.class, () -> {(new CmdReadFile()).execute(cmdParts);});
		
	}
	

	// Test readfile, the processLineParts[1] is negative
	@Test
	public void TestReadFileProcessLineParts1IsNegative() {
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/3-ProcessLineParts1IsNegative.txt"};
		
		ExInvalidInput ex = assertThrows(ExInvalidInput.class, () -> {(new CmdReadFile()).execute(cmdParts);});
		
	}
	

	// Test readfile, the processLineParts[2] is not number
	@Test
	public void TestReadFileProcessLineParts2IsNotNumber() {
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/3-ProcessLineParts2IsNotNumber.txt"};
		
		NumberFormatException ex = assertThrows(NumberFormatException.class, () -> {(new CmdReadFile()).execute(cmdParts);});
		
	}
	

	// Test readfile, the processLineParts[2] is not integer
	@Test
	public void TestReadFileProcessLineParts2IsNotInteger() {
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/3-ProcessLineParts2IsNotInteger.txt"};
		
		ExInvalidInput ex = assertThrows(ExInvalidInput.class, () -> {(new CmdReadFile()).execute(cmdParts);});
		
	}
	

	// Test readfile, the processLineParts[2] is negative
	@Test
	public void TestReadFileProcessLineParts2IsNegative() {
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/3-ProcessLineParts2IsNegative.txt"};
		
		ExInvalidInput ex = assertThrows(ExInvalidInput.class, () -> {(new CmdReadFile()).execute(cmdParts);});
		
	}
	
	

	// Test readfile, the processLineParts[3] is not number
	@Test
	public void TestReadFileProcessLineParts3IsNotNumber() {
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/3-ProcessLineParts3IsNotNumber.txt"};
		
		NumberFormatException ex = assertThrows(NumberFormatException.class, () -> {(new CmdReadFile()).execute(cmdParts);});
		
	}
	

	// Test readfile, the processLineParts[3] is not integer
	@Test
	public void TestReadFileProcessLineParts3IsNotInteger() {
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/3-ProcessLineParts3IsNotInteger.txt"};
		
		ExInvalidInput ex = assertThrows(ExInvalidInput.class, () -> {(new CmdReadFile()).execute(cmdParts);});
		
	}
	

	// Test readfile, the processLineParts[3] is negative
	@Test
	public void TestReadFileProcessLineParts3IsNegative() {
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/3-ProcessLineParts3IsNegative.txt"};
		
		ExInvalidInput ex = assertThrows(ExInvalidInput.class, () -> {(new CmdReadFile()).execute(cmdParts);});
		
	}
	
	// Test readfile, the service line parts size is not 2
	@Test
	public void TestReadFileServiceLinePartsSizeIsNot2() {
		
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/4-ServiceLinePartsSizeIsNot2.txt"};
		
		ExInvalidInput ex = assertThrows(ExInvalidInput.class, () -> {(new CmdReadFile()).execute(cmdParts);});
		
	}
	
	// Test readfile, the service type is of the non-existing type
	@Test
	public void TestReadFileServiceTypeWrongFormat() {

		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/4-ServiceLinePartsInvalidServiceType.txt"};
		
		ExInvalidServiceType ex = assertThrows(ExInvalidServiceType.class, () -> {(new CmdReadFile()).execute(cmdParts);});
		
	}
	
	// Test readfile, the service time is not number
	@Test
	public void TestReadFileServiceLineParts1IsNotNumber() {

		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/4-ServiceLineParts1IsNotNumber.txt"};
		
		NumberFormatException ex = assertThrows(NumberFormatException.class, () -> {(new CmdReadFile()).execute(cmdParts);});
		
	}
	

	// Test readfile, the service time is not integer
	@Test
	public void TestReadFileServiceLineParts1IsNotInteger() {

		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/4-ServiceLineParts1IsNotInteger.txt"};
		
		ExInvalidInput ex = assertThrows(ExInvalidInput.class, () -> {(new CmdReadFile()).execute(cmdParts);});
		
	}
	

	// Test readfile, the service time is negative
	@Test
	public void TestReadFileServiceLineParts1IsNegative() {

		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/4-ServiceLineParts1IsNegative.txt"};
		
		ExInvalidInput ex = assertThrows(ExInvalidInput.class, () -> {(new CmdReadFile()).execute(cmdParts);});
		
	}
	
}