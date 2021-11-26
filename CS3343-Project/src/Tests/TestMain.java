package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import Project.Main;

class TestMain {
	
	@Test
	void testMain() throws Exception {
		
		
		ByteArrayInputStream in;
		int result;
		in = new ByteArrayInputStream("readfile ./src/TestSamples/1-perfect.txt".getBytes());
		System.setIn(in);
		in = new ByteArrayInputStream("schedule".getBytes());
		System.setIn(in);
		in = new ByteArrayInputStream("custom AQT".getBytes());
		System.setIn(in);
		in = new ByteArrayInputStream("exit".getBytes());
		System.setIn(in);
		result = Main.main(null);
		assertEquals(0, result);
	}
//
//	@Test
//	void testReadFile() throws Exception {
//		
//		setOutput();
//		
//		String expected = "";
//		
//		Main.main(null);
//		String actual = getOutput();
//		assertEquals(expected, actual);
//	}
//	
//	@Test
//	void testSchedule() throws Exception {
//		
//		setOutput();
//		
//		String expected = "";
//		ByteArrayInputStream in = new ByteArrayInputStream("schedule".getBytes());
//		System.setIn(in);
//		Main.main(null);
//		String actual = getOutput();
//		assertEquals(expected, actual);
//	}
//	
//	@Test
//	void testCustom() throws Exception {
//		
//		setOutput();
//		
//		String expected = "";
//		ByteArrayInputStream in = new ByteArrayInputStream("custom AQT".getBytes());
//		System.setIn(in);
//		Main.main(null);
//		String actual = getOutput();
//		assertEquals(expected, actual);
//	}
//	
//	@Test
//	void testCustom2() throws Exception {
//		
//		setOutput();
//		
//		String expected = "";
//		ByteArrayInputStream in = new ByteArrayInputStream("custom".getBytes());
//		System.setIn(in);
//		Main.main(null);
//		String actual = getOutput();
//		assertEquals(expected, actual);
//	}
//	
//	@Test
//	void testSuggest() throws Exception {
//		
//		setOutput();
//		
//		String expected = "";
//		ByteArrayInputStream in = new ByteArrayInputStream("suggest".getBytes());
//		System.setIn(in);
//		Main.main(null);
//		String actual = getOutput();
//		assertEquals(expected, actual);
//	}
//	
//	@Test
//	void testOpen() throws Exception {
//		
//		setOutput();
//		
//		String expected = "";
//		ByteArrayInputStream in = new ByteArrayInputStream("open FCFS".getBytes());
//		System.setIn(in);
//		Main.main(null);
//		String actual = getOutput();
//		assertEquals(expected, actual);
//	}
//	
//	@Test
//	void testClose() throws Exception {
//		
//		setOutput();
//		
//		String expected = "";
//		ByteArrayInputStream in = new ByteArrayInputStream("close".getBytes());
//		System.setIn(in);
//		Main.main(null);
//		String actual = getOutput();
//		assertEquals(expected, actual);
//	}
//	
//	@Test
//	void testDisplay() throws Exception {
//		
//		setOutput();
//		
//		String expected = "";
//		ByteArrayInputStream in = new ByteArrayInputStream("display".getBytes());
//		System.setIn(in);
//		Main.main(null);
//		String actual = getOutput();
//		assertEquals(expected, actual);
//	}
//	
//	@Test
//	void testHelp() throws Exception {
//		
//		setOutput();
//		
//		String expected = "";
//		ByteArrayInputStream in = new ByteArrayInputStream("help".getBytes());
//		System.setIn(in);
//		Main.main(null);
//		String actual = getOutput();
//		assertEquals(expected, actual);
//	}
//	
//	
//	
//	@Test
//	void testNonExisting() throws Exception {
//		
//		setOutput();
//		
//		String expected = "";
//		ByteArrayInputStream in = new ByteArrayInputStream("NonExisting".getBytes());
//		System.setIn(in);
//		Main.main(null);
//		String actual = getOutput();
//		assertEquals(expected, actual);
//	}
//	
//	// Handling output
//	PrintStream oldPrintStream;
//	ByteArrayOutputStream bos;
//
//	  private void setOutput() throws Exception {
//	    oldPrintStream = System.out;
//	    bos = new ByteArrayOutputStream();
//	    System.setOut(new PrintStream(bos));
//	  }
//
//	  private String getOutput() { // throws Exception
//	    System.setOut(oldPrintStream);
//	    return bos.toString();
//	  }
	

}
