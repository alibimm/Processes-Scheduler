package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Exceptions.ExInvalidServiceType;
import Project.Process;
import Project.Service;
import Project.ServiceType;

public class TestProcess {
	
	@Test
	// This testcase adds only one service to the process, and checks that process gets its service time
	void testGetServiceTimeWith1Service() {
		
		ArrayList<Service> services = new ArrayList<>();
		Service s = Service.create("C", "5");
		services.add(s);
		
		Process testProcess = Process.create(0, 1, services);
		
		// Initializing the result of the method call
		// Trying to get the first service time of the process
		int actual = testProcess.getServiceTime(0);
		int expected = 5;
		
		assertEquals(expected, actual);
	}
	
	@Test
	// This testcase adds more than 1 service to the process, and checks that process gets its service time
	void testGetServiceTimeWithMoreThan1Service() {
		
		ArrayList<Service> services = new ArrayList<>();
		Service s1 = Service.create("C", "5");
		Service s2 = Service.create("K", "3");
		Service s3 = Service.create("C", "7");
		services.add(s1);
		services.add(s2);
		services.add(s3);
		
		Process testProcess = Process.create(0, 1, services);
		
		// Initializing the result of the method call
		// Trying to get the second service time of the process
		int actual = testProcess.getServiceTime(1);
		int expected = 3;
		assertEquals(expected, actual);
		
		
		// Initializing the result of the method call
		// Trying to get the third service time of the process
		actual = testProcess.getServiceTime(2);
		expected = 7;
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetServiceTimeWithNoServices()  {
		ArrayList<Service> services = new ArrayList<>();
		Process testProcess = Process.create(0, 1, services);
		
		// Initializing the result of the method call
		// Trying to get the first service time of the process
		assertThrows(IndexOutOfBoundsException.class, () -> {testProcess.getServiceTime(0);});
	}
	
	@Test
	void testGetServiceTypeWith1ServiceCPU() {
		ArrayList<Service> services = new ArrayList<>();
		Service s = Service.create("C", "5");
		services.add(s);
		
		Process testProcess = Process.create(0, 1, services);
		
		// Initializing the result of the method call
		// Trying to get the first service type of the process
		ServiceType actual = testProcess.getServiceType(0);
		ServiceType expected = ServiceType.CPU;
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetServiceTypeWith1ServiceKeyboard() {
				ArrayList<Service> services = new ArrayList<>();
				Service s = Service.create("K", "5");
				services.add(s);
				
				Process testProcess = Process.create(0, 1, services);
				
				// Initializing the result of the method call
				// Trying to get the first service type of the process
				ServiceType actual = testProcess.getServiceType(0);
				ServiceType expected = ServiceType.Keyboard;
				
				assertEquals(expected, actual);
	}
	
	@Test
	void testGetServiceTypeWithMoreThan1Service() {
				ArrayList<Service> services = new ArrayList<>();
				Service s1 = Service.create("C", "5");
				Service s2 = Service.create("K", "3");
				Service s3 = Service.create("C", "7");
				services.add(s1);
				services.add(s2);
				services.add(s3);
				
				Process testProcess = Process.create(0, 1, services);
				
				// Initializing the result of the method call
				// Trying to get the second service time of the process
				ServiceType actual = testProcess.getServiceType(1);
				ServiceType expected = ServiceType.Keyboard;
				
				assertEquals(expected, actual);
				
				
				// Initializing the result of the method call
				// Trying to get the third service time of the process
				actual = testProcess.getServiceType(2);
				expected = ServiceType.CPU;

				assertEquals(expected, actual);
	}
	
	@Test
	void testGetServicesCount() throws ExInvalidServiceType {
		// Initializing new services ArrayList
		ArrayList<Service> services = new ArrayList<>();
		Service s1 = Service.create("C", "5");
		Service s2 = Service.create("K", "3");
		Service s3 = Service.create("C", "7");
		services.add(s1);
		services.add(s2);
		services.add(s3);
		
		Process testProcess = Process.create(0, 1, services);
		
		int actual = testProcess.getServicesCount();
		int expected = 3;
		
		assertEquals(expected, actual);
	}
	
	@Test
	// This testcase creates new Process and checks getArrivalTime Method
	void testGetArrivalTime() {
		Process testProcess = Process.create(0, 1, new ArrayList<>());
		
		int actual = testProcess.getArrivalTime();
		int expected = 1; 
		
		assertEquals(expected, actual);
	}

	@Test
	// This testcase creates new Process and checks getId Method
	void testGetId() {
		Process testProcess = Process.create(5, 1, new ArrayList<>());

		int actual = testProcess.getId();
		int expected = 5; 
		
		assertEquals(expected, actual);
	}
}