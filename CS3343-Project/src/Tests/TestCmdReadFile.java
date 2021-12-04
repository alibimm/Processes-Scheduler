package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
		
		MainSystem system = MainSystem.getInstance();
		
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/1-perfect.txt"};
		
		(new CmdReadFile()).execute(cmdParts);
		
		ArrayList<Process> actual = system.getAllInputs().get(0);
		
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
		
		// Checking the results
		for (int i = 0; i < expected.size(); ++i) {
			assertEquals(expected.get(i).getId(), actual.get(i).getId());
			assertEquals(expected.get(i).getArrivalTime(), actual.get(i).getArrivalTime());
			assertEquals(expected.get(i).getServicesCount(), actual.get(i).getServicesCount());
		}
	}

	// Test readfile with invalid filepathname
	@Test
	public void TestReadFileWithInvalidFilePathName() throws Exception {
		setOutput();
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/random.txt"};

		(new CmdReadFile()).execute(cmdParts);
		String expected = "./src/TestSamples/random.txt (No such file or directory)\n";
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	
	// Test readfile, the first line is not number
	@Test
	public void TestReadFileFirstLineIsNotNumber() throws Exception {
		setOutput();

		String[] cmdParts = new String[]{"readfile","./src/TestSamples/2-number_of_processes_not_integer.txt"};
		(new CmdReadFile()).execute(cmdParts);
		String expected = "For input string: \"Q\"\n";
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	
	// Test readfile, the first line is not integer, but float
	@Test
	public void TestReadFileFirstLineIsNotInteger() throws Exception {
		setOutput();

		String[] cmdParts = new String[]{"readfile","./src/TestSamples/2-number_of_processes_float_number.txt"};

		(new CmdReadFile()).execute(cmdParts);	
		String expected = "For input string: \"4.5\"\n";
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	
	
	// Test readfile, the first line is negative integer		
	@Test
	public void TestReadFileFirstLineIsNegativeInteger() throws Exception {
		setOutput();

		String[] cmdParts = new String[]{"readfile","./src/TestSamples/2-number_of_processes_negative_integer.txt"};
		// Running execute new Cmd Read FIle
		(new CmdReadFile()).execute(cmdParts);
		String expected = "Number of processes should be non-negative integer\n";
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	
	// Test readfile, the process line parts size is not equal to 4
	// # - initiation of process,
	// 0 - process id
	// 0 - arrival time
	// 5 - number of services
	@Test
	public void TestReadFileProcessLinePartsSizeIsNot4() throws Exception {
		setOutput();

		String[] cmdParts = new String[]{"readfile","./src/TestSamples/3-ProcessLinePartsSizeIsNot4.txt"};
		(new CmdReadFile()).execute(cmdParts);
		String expected = "Process Line Should have 4 arguments (e.g. # 0 5 3)\n";
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	

	// Test readfile, the processLineParts[0] is not #
	@Test
	public void TestReadFileProcessLineParts0IsNotHash() throws Exception {
		setOutput();

		String[] cmdParts = new String[]{"readfile","./src/TestSamples/3-ProcessLinePartsIsNot#.txt"};
		(new CmdReadFile()).execute(cmdParts);
		String expected = "Process should be initialized with # character (e.g. # 0 5 3)\n";
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	

	// Test readfile, the processLineParts[1] is not number
	@Test
	public void TestReadFileProcessLineParts1IsNotNumber() throws Exception {
		setOutput();

		String[] cmdParts = new String[]{"readfile","./src/TestSamples/3-ProcessLineParts1IsNotNumber.txt"};
		(new CmdReadFile()).execute(cmdParts);
		String expected = "For input string: \"q\"\n";
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	

	// Test readfile, the processLineParts[1] is not integer
	@Test
	public void TestReadFileProcessLineParts1IsNotInteger() throws Exception {
		setOutput();

		String[] cmdParts = new String[]{"readfile","./src/TestSamples/3-ProcessLineParts1IsNotInteger.txt"};
		(new CmdReadFile()).execute(cmdParts);
		String expected = "For input string: \"1.1\"\n";
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	

	// Test readfile, the processLineParts[1] is negative
	@Test
	public void TestReadFileProcessLineParts1IsNegative() throws Exception {
		setOutput();

		String[] cmdParts = new String[]{"readfile","./src/TestSamples/3-ProcessLineParts1IsNegative.txt"};
		(new CmdReadFile()).execute(cmdParts);
		String expected = "Process ID should be non-negative integer\n";
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	

	// Test readfile, the processLineParts[2] is not number
	@Test
	public void TestReadFileProcessLineParts2IsNotNumber() throws Exception {
		setOutput();

		String[] cmdParts = new String[]{"readfile","./src/TestSamples/3-ProcessLineParts2IsNotNumber.txt"};
		(new CmdReadFile()).execute(cmdParts);
		String expected = "For input string: \"q\"\n";
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	

	// Test readfile, the processLineParts[2] is not integer
	@Test
	public void TestReadFileProcessLineParts2IsNotInteger() throws Exception {
		setOutput();

		String[] cmdParts = new String[]{"readfile","./src/TestSamples/3-ProcessLineParts2IsNotInteger.txt"};
		(new CmdReadFile()).execute(cmdParts);
		String expected = "For input string: \"1.1\"\n";
		String actual = getOutput();
		assertEquals(expected, actual);
		
	}
	

	// Test readfile, the processLineParts[2] is negative
	@Test
	public void TestReadFileProcessLineParts2IsNegative() throws Exception {
		
		setOutput();
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/3-ProcessLineParts2IsNegative.txt"};
		(new CmdReadFile()).execute(cmdParts);
		String expected = "Process Arrival Time should be non-negative integer\n";
		String actual = getOutput();
		assertEquals(expected, actual);
		
	}
	
	

	// Test readfile, the processLineParts[3] is not number
	@Test
	public void TestReadFileProcessLineParts3IsNotNumber() throws Exception {
		
		setOutput();
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/3-ProcessLineParts3IsNotNumber.txt"};
		(new CmdReadFile()).execute(cmdParts);
		String expected = "Process Line Should have 4 arguments (e.g. # 0 5 3)\n";
		String actual = getOutput();
		assertEquals(expected, actual);
		
	}
	

	// Test readfile, the processLineParts[3] is not integer
	@Test
	public void TestReadFileProcessLineParts3IsNotInteger() throws Exception {
		
		setOutput();
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/3-ProcessLineParts3IsNotInteger.txt"};
		(new CmdReadFile()).execute(cmdParts);
		
		String expected = "For input string: \"1.1\"\n";
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	

	// Test readfile, the processLineParts[3] is negative
	@Test
	public void TestReadFileProcessLineParts3IsNegative() throws Exception {
		setOutput();

		String[] cmdParts = new String[]{"readfile","./src/TestSamples/3-ProcessLineParts3IsNegative.txt"};
		(new CmdReadFile()).execute(cmdParts);
		String expected = "Number of services in the process should be positive integer\n";
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	
	// Test readfile, the service line parts size is not 2
	@Test
	public void TestReadFileServiceLinePartsSizeIsNot2() throws Exception {
		setOutput();

		String[] cmdParts = new String[]{"readfile","./src/TestSamples/4-ServiceLinePartsSizeIsNot2.txt"};
		(new CmdReadFile()).execute(cmdParts);
		String expected = "Service Line Should have 2 arguments (e.g. C 5)\n";
		String actual = getOutput();
		assertEquals(expected, actual);
	}
	
	// Test readfile, the service type is of the non-existing type
	@Test
	public void TestReadFileServiceTypeWrongFormat() throws Exception {

		setOutput();
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/4-ServiceLinePartsInvalidServiceType.txt"};
		(new CmdReadFile()).execute(cmdParts);
		String expected = "Service Type Should be C or K\n";
		String actual = getOutput();
		assertEquals(expected, actual);
		
	}
	
	// Test readfile, the service time is not number
	@Test
	public void TestReadFileServiceLineParts1IsNotNumber() throws Exception {

		setOutput();
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/4-ServiceLineParts1IsNotNumber.txt"};
		(new CmdReadFile()).execute(cmdParts);
		String expected = "For input string: \"q\"\n";
		String actual = getOutput();
		assertEquals(expected, actual);
		
	}
	

	// Test readfile, the service time is not integer
	@Test
	public void TestReadFileServiceLineParts1IsNotInteger() throws Exception {

		setOutput();
		// Initializing cmdParts that will be inputted
		String[] cmdParts = new String[]{"readfile","./src/TestSamples/4-ServiceLineParts1IsNotInteger.txt"};
		(new CmdReadFile()).execute(cmdParts);
		String expected = "For input string: \"1.1\"\n";
		String actual = getOutput();
		assertEquals(expected, actual);
		
	}
	

	// Test readfile, the service time is negative
	@Test
	public void TestReadFileServiceLineParts1IsNegative() throws Exception {
		setOutput();

		String[] cmdParts = new String[]{"readfile","./src/TestSamples/4-ServiceLineParts1IsNegative.txt"};
		(new CmdReadFile()).execute(cmdParts);
		String expected = "Service time should be positive integer\n";
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