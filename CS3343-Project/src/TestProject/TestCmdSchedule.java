package TestProject;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import Commands.CmdSchedule;


public class TestCmdSchedule {
	
	public ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	PrintStream stdOut = System.out;
	
	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
	}
	
	@Test
	public void test1(){
		
		String[] cmdParts = new String[]{"schedule"};
		
		CmdSchedule cs=new CmdSchedule();
		cs.execute(cmdParts);
		assertEquals("processes has been schedule", outContent.toString());
		
	}
	
	
	
	 

}
