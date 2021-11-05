package TestProject;

import Project.Main;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
//import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import org.junit.Test;

public class TestingMain {
	
	@Test
	public void testMain1() throws Exception {
//		String[] args = null;
////		final InputStream original = System.in;
//	    String input = "readfile TestSamples/sample_0.txt" ;
//	    InputStream in = new ByteArrayInputStream(input.getBytes());
//	    System.setIn(in);
//	   
////	    System.setIn(original);
//	    Main.main(args);
	    
	    
	    
	    Main main = new Main();

	    String input = "readfile TestSamples/sample_0.txt\nschedule" ;
	    InputStream in1 = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in1);
	    System.setIn(System.in);
	    
	    
		Main.main(null);
		System.setIn(System.in);
//		
		
	}
}
