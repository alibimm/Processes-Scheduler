package TestProject;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import Commands.CmdReadFile;
import Project.MainSystem;

public class TestCmdReadFile {
	
	public ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	PrintStream stdOut = System.out;
	
	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
	}
	
	@Test
	public void test1(){
		
		String[] cmdParts = new String[]{"readfile","TestSamples/sample_0.txt"};
		
		CmdReadFile crf=new CmdReadFile();
		crf.execute(cmdParts);
		assertEquals("File has been read and closed", outContent.toString());
		
	}
	
	
	
	 

}
