package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

import Commands.CmdHelp;

class TestCmdHelp {

	@Test
	void testHelp() throws Exception  {
		
		setOutput();
		
		String[] cmdParts = new String[]{"help"};
		
		(new CmdHelp()).execute(cmdParts);
		
		String expected = "\nThere are 10 commands used in various situations:\n"
				+ "\n"
				+ "  readfile [filepathname]  Let the scheduler read the file                   \n"
				+ "  schedule                 Start the scheduling                              \n"
				+ "  suggest                  To get suggestions on best algorithm according to currently set indicator for the given files.\n"
				+ "				(default: Turnaround time)\n"
				+ "  custom [indicator]       To get suggestions on best algorithm according to custom indicators for the given files.\n"
				+ "		  	     Available indicators:\n"
				+ "				[AQT] - Average Queueing Time\n"
				+ "				[ATRT] - Average TurnAround Time\n"
				+ "				[ARTS] - Average Ratio TS (Turnaround/Service)\n"
				+ "				[CpuUtil] - CPU Utilization\n"
				+ "  display                  Print the current information                     \n"
				+ "  help                     Get help for execution and navigation             \n"
				+ "  exit                     Stop execution of the application\n"
				+ "                \n"
				+ "\n"
				+ "To navigate through given files and algorithms for specific results\n"
				+ "\n"
				+ "  open [index of case]     Open the detailed results of the specified file   \n"
				+ "  open [algorithm]         Open the detailed results of the specified algorithm\n"
				+ "  close                    Go back to previous information                   \n\n";
		
		assertEquals(expected, getOutput());
	}
	
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

