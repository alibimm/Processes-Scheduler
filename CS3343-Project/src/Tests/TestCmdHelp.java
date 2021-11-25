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

	@Test
	void testHelp() throws Exception  {
		
		setOutput();
		
		String[] cmdParts = new String[]{"help"};
		
		// Running execute new Cmd Help
		(new CmdHelp()).execute(cmdParts);
		
		String expected = "There are 9 commands used in various situations:\n"
				+ "\n"
				+ "Working area\n"
				+ "  readfile [filepathname]		Let the scheduler read the file\n"
				+ "  schedule				Start the scheduling\n"
				+ "  suggest				To get suggestions on best algorithm according to currently set indicator (default: Turnaround time) for the given files\n"
				+ "  custom [indicator]			To get suggestions on best algorithm according to custom indicators for the given files.\n"
				+ "					Available indicators:\n"
				+ "					  [AQT] - Average Queueing Time\n"
				+ "					  [ATRT] - Average TurnAround Time\n"
				+ "					  [ARTS] - Average Ratio TS (Turnaround/Serviice)\n"
				+ "					  [CpuUtil] - CPU Utilization\n"
				+ "  display				Print the current information\n"
				+ "  help					Get help for execution and navigation\n"
				+ "  exit					Stop execution of the application\n"
				+ "\n"
				+ "\n"
				+ "To navigate through given files and algorithms for specific results\n"
				+ "\n"
				+ "  open [index of file]			Open the detailed results of the specified file\n"
				+ "  open [algorithm]			Open the detailed results of the specified algorithm\n"
				+ "  close					Go back to previous information\n"
				+ "\n"
				+ "";
		
		assertEquals(expected, getOutput());
	}
	
	@Test
	void testHelp2() throws Exception  {
		
		setOutput();
		
		String[] cmdParts = new String[]{"help"};
		
		// Running execute new Cmd Help
		(new CmdHelp()).execute(cmdParts);
		
		cmdParts = new String[]{"help"};
		
		// Running execute new Cmd Help
		(new CmdHelp()).execute(cmdParts);
		
		String expected = "There are 9 commands used in various situations:\n"
				+ "\n"
				+ "Working area\n"
				+ "  readfile [filepathname]		Let the scheduler read the file\n"
				+ "  schedule				Start the scheduling\n"
				+ "  suggest				To get suggestions on best algorithm according to currently set indicator (default: Turnaround time) for the given files\n"
				+ "  custom [indicator]			To get suggestions on best algorithm according to custom indicators for the given files.\n"
				+ "					Available indicators:\n"
				+ "					  [AQT] - Average Queueing Time\n"
				+ "					  [ATRT] - Average TurnAround Time\n"
				+ "					  [ARTS] - Average Ratio TS (Turnaround/Serviice)\n"
				+ "					  [CpuUtil] - CPU Utilization\n"
				+ "  display				Print the current information\n"
				+ "  help					Get help for execution and navigation\n"
				+ "  exit					Stop execution of the application\n"
				+ "\n"
				+ "\n"
				+ "To navigate through given files and algorithms for specific results\n"
				+ "\n"
				+ "  open [index of file]			Open the detailed results of the specified file\n"
				+ "  open [algorithm]			Open the detailed results of the specified algorithm\n"
				+ "  close					Go back to previous information\n"
				+ "\n"
				+ "There are 9 commands used in various situations:\n"
				+ "\n"
				+ "Working area\n"
				+ "  readfile [filepathname]		Let the scheduler read the file\n"
				+ "  schedule				Start the scheduling\n"
				+ "  suggest				To get suggestions on best algorithm according to currently set indicator (default: Turnaround time) for the given files\n"
				+ "  custom [indicator]			To get suggestions on best algorithm according to custom indicators for the given files.\n"
				+ "					Available indicators:\n"
				+ "					  [AQT] - Average Queueing Time\n"
				+ "					  [ATRT] - Average TurnAround Time\n"
				+ "					  [ARTS] - Average Ratio TS (Turnaround/Serviice)\n"
				+ "					  [CpuUtil] - CPU Utilization\n"
				+ "  display				Print the current information\n"
				+ "  help					Get help for execution and navigation\n"
				+ "  exit					Stop execution of the application\n"
				+ "\n"
				+ "\n"
				+ "To navigate through given files and algorithms for specific results\n"
				+ "\n"
				+ "  open [index of file]			Open the detailed results of the specified file\n"
				+ "  open [algorithm]			Open the detailed results of the specified algorithm\n"
				+ "  close					Go back to previous information\n"
				+ "\n"
				+ "";
		
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

