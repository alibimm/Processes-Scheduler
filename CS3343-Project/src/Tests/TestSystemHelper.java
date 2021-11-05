package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import Project.Process;
import Project.Service;

import org.junit.jupiter.api.Test;

import Project.SystemHelper;

class TestSystemHelper {

	@Test
	void testMoveProcessFrom() {
		// Creating mock allServices 
		Service s1 = Service.create("C", "10");
		ArrayList<Service> allServices = new ArrayList<Service>(Arrays.asList(s1));
		
		// Creating mock processes 
		Process p1 =Process.create(1, 2, allServices);
		Process p2 =Process.create(2, 10, allServices);
		Process p3 =Process.create(3, 15, allServices);
		Process p4 =Process.create(4, 20, allServices);

		// Creating mock ArrayLists<Process> for testing
		ArrayList<Process> first = new ArrayList<Process>(Arrays.asList(p1,p2,p3));
		ArrayList<Process> second = new ArrayList<Process>(Arrays.asList(p4));
		
		// Executing moveProcessFrom()
		SystemHelper.moveProcessFrom(first, second);
		
		// Creating expected ArrayList<Process> with moved p1
		ArrayList<Process> firstExpected = new ArrayList<Process>(Arrays.asList(p2,p3));
		ArrayList<Process> secondExpected = new ArrayList<Process>(Arrays.asList(p4,p1));

		assertEquals(firstExpected, first);
		assertEquals(secondExpected, second);

	}

}