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
		setOutput();
		ByteArrayInputStream in;
		in = new ByteArrayInputStream(("readfile ./src/TestSamples/1-perfect.txt\n"
				+ "schedule\n"
				+ "custom AQT\n"
				+ "suggest\n"
				+ "open 0\n"
				+ "close\n"
				+ "display\n"
				+ "help\n"
				+ "random\n"
				+ "open\n"
				+ "exit\n").getBytes());
		System.setIn(in);
		Main.main(null);
		String actual = getOutput();
		String expected = ">>1 unscheduled inputs scheduled successfully.\n"
				+ ">Changed the best algorithm indicator to AQT.\n"
				+ ">The best performing algorithm(s): Shortest Remaining Time Next\n"
				+ "Case           Best algorithms\n"
				+ "Case #0        SRT\n"
				+ ">Type           Duration       CPU Util       Avg Turnaround Max Turnaround Avg Queuing    Max Queuing    \n"
				+ "FCFS           50             0.80           39.50          48.00          19.00          24.00          \n"
				+ "RR             50             0.80           39.50          48.00          19.00          24.00          \n"
				+ "FB             50             0.80           37.75          48.00          17.25          23.00          \n"
				+ "SPN            48             0.83           35.25          46.00          14.75          22.00          \n"
				+ "SRT            48             0.81           35.00          46.00          14.50          21.00          \n"
				+ "HRRN           50             0.80           39.50          48.00          19.00          24.00          \n"
				+ "\n"
				+ ">>Scheduled cases:\n"
				+ "Case 0\n"
				+ "Unscheduled files: 0\n"
				+ ">\n"
				+ "There are 10 commands used in various situations:\n"
				+ "\n"
				+ "  readfile [filepathname]  Let the scheduler read the file                   \n"
				+ "  schedule                 Start the scheduling                              \n"
				+ "  suggest                  To get suggestions on best algorithm according to currently set indicator for the given files.\n"
				+ "\t\t\t\t(default: Turnaround time)\n  custom [indicator]       To get suggestions on best algorithm according to custom indicators for the given files.\n"
				+ "\t\t  \t     Available indicators:\n"
				+ "\t\t\t\t[AQT] - Average Queueing Time\n"
				+ "\t\t\t\t[ATRT] - Average TurnAround Time\n"
				+ "\t\t\t\t[ARTS] - Average Ratio TS (Turnaround/Service)\n"
				+ "\t\t\t\t[CpuUtil] - CPU Utilization\n"
				+ "  display                  Print the current information                     \n"
				+ "  help                     Get help for execution and navigation             \n"
				+ "  exit                     Stop execution of the application\n                \n"
				+ "\n"
				+ "To navigate through given files and algorithms for specific results\n"
				+ "\n"
				+ "  open [index of case]     Open the detailed results of the specified file   \n"
				+ "  open [algorithm]         Open the detailed results of the specified algorithm\n"
				+ "  close                    Go back to previous information                   \n"
				+ "\n"
				+ ">Command random doesn't exist. To see all commands use 'help'.\n"
				+ ">Open Command should have more than 2 arguments\n>";
		assertEquals(expected,actual);
	}
	
	// Handling output
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
