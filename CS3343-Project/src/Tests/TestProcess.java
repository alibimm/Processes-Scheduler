package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Exceptions.ExInvalidServiceType;
import Project.Process;
import Project.Service;
import Project.ServiceType;

public class TestProcess {
	
	// 
	// getServiceTime Method Testcases
	// 
	@Test
	// This testcase adds only one service to the process, and checks that process gets its service time
	void testGetServiceTimeWith1Service() {
		
		// Initializing new services ArrayList
		ArrayList<Service> services = new ArrayList<>();
		
		// Initializing Dummy Service
		Service s = Service.create("C", "5");
		
		// Adding dummy service to an ArrayList
		services.add(s);
		
		// Initializing test Process object
		Process testProcess = Process.create(0, 1, services);
		
		// Initializing the result of the method call
		// Trying to get the first service time of the process
		int actual = testProcess.getServiceTime(0);
		
		// Initializing expected result
		int expected = 5;
		
		// Check the correctness
		assertEquals(expected, actual);
		
	}
	
	@Test
	// This testcase adds more than 1 service to the process, and checks that process gets its service time
	void testGetServiceTimeWithMoreThan1Service() {
		
		// Initializing new services ArrayList
		ArrayList<Service> services = new ArrayList<>();
		
		// Initializing Dummy Services
		Service s1 = Service.create("C", "5");
		Service s2 = Service.create("K", "3");
		Service s3 = Service.create("C", "7");
		
		// Adding dummy services to an ArrayList
		services.add(s1);
		services.add(s2);
		services.add(s3);
		
		// Initializing test Process object
		Process testProcess = Process.create(0, 1, services);
		
		// Initializing the result of the method call
		// Trying to get the second service time of the process
		int actual = testProcess.getServiceTime(1);
		// Initializing expected result
		int expected = 3;
		// Check the correctness
		assertEquals(expected, actual);
		
		
		// Initializing the result of the method call
		// Trying to get the third service time of the process
		actual =testProcess.getServiceTime(2);
		// Initializing expected result
		expected = 7;
		// Check the correctness
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetServiceTimeWithNoServices()  {
		
		// Initializing new services ArrayList
		ArrayList<Service> services = new ArrayList<>();

		// Initializing test Process object
		Process testProcess = Process.create(0, 1, services);
		
		// Initializing the result of the method call
		// Trying to get the first service time of the process
		IndexOutOfBoundsException ex = assertThrows(IndexOutOfBoundsException.class, () -> {testProcess.getServiceTime(0);});
		
	}
	
	//
	//
	//
	
	//
	// getServiceType Method Testcase
	//
	
	@Test
	void testGetServiceTypeWith1ServiceCPU() {
		
		// Initializing new services ArrayList
				ArrayList<Service> services = new ArrayList<>();
				
				// Initializing Dummy Service
				Service s = Service.create("C", "5");
				
				// Adding dummy service to an ArrayList
				services.add(s);
				
				// Initializing test Process object
				Process testProcess = Process.create(0, 1, services);
				
				// Initializing the result of the method call
				// Trying to get the first service type of the process
				ServiceType actual = testProcess.getServiceType(0);
				
				// Initializing expected result
				ServiceType expected = ServiceType.CPU;
				
				// Check the correctness
				assertEquals(expected, actual);
	}
	
	@Test
	void testGetServiceTypeWith1ServiceKeyboard() {
		
		// Initializing new services ArrayList
				ArrayList<Service> services = new ArrayList<>();
				
				// Initializing Dummy Service
				Service s = Service.create("K", "5");
				
				// Adding dummy service to an ArrayList
				services.add(s);
				
				// Initializing test Process object
				Process testProcess = Process.create(0, 1, services);
				
				// Initializing the result of the method call
				// Trying to get the first service type of the process
				ServiceType actual = testProcess.getServiceType(0);
				
				// Initializing expected result
				ServiceType expected = ServiceType.Keyboard;
				
				// Check the correctness
				assertEquals(expected, actual);
	}
	
	@Test
	void testGetServiceTypeWithMoreThan1Service() {
		
		// Initializing new services ArrayList
				ArrayList<Service> services = new ArrayList<>();
				
				// Initializing Dummy Services
				Service s1 = Service.create("C", "5");
				Service s2 = Service.create("K", "3");
				Service s3 = Service.create("C", "7");
				
				// Adding dummy services to an ArrayList
				services.add(s1);
				services.add(s2);
				services.add(s3);
				
				// Initializing test Process object
				Process testProcess = Process.create(0, 1, services);
				
				// Initializing the result of the method call
				// Trying to get the second service time of the process
				ServiceType actual = testProcess.getServiceType(1);
				// Initializing expected result
				ServiceType expected = ServiceType.Keyboard;
				// Check the correctness
				assertEquals(expected, actual);
				
				
				// Initializing the result of the method call
				// Trying to get the third service time of the process
				actual = testProcess.getServiceType(2);
				// Initializing expected result
				expected = ServiceType.CPU;
				// Check the correctness
				assertEquals(expected, actual);
	}
	
	//
	//
	//
	
	//
	// getServicesCount Method Testcase
	//
	@Test
	void testGetServicesCount() throws ExInvalidServiceType {
		// Initializing new services ArrayList
		ArrayList<Service> services = new ArrayList<>();
		
		// Initializing Dummy Services
		Service s1 = Service.create("C", "5");
		Service s2 = Service.create("K", "3");
		Service s3 = Service.create("C", "7");
		
		// Adding dummy services to an ArrayList
		services.add(s1);
		services.add(s2);
		services.add(s3);
		
		// Initializing test Process object
		Process testProcess = Process.create(0, 1, services);
		
		// Initializing actual result
		int actual = testProcess.getServicesCount();
		
		// Initializing expected result
		int expected = 3;
		
		// Check the correctness
		assertEquals(expected, actual);
	}
	
	//
	// getArrivalTime Method Testcases
	// 
	@Test
	// This testcase creates new Process and checks getArrivalTime Method
	void testGetArrivalTime() {
		
		// Initializing test Process object
		Process testProcess = Process.create(0, 1, new ArrayList<>());
		
		// Initializing the actual result
		int actual = testProcess.getArrivalTime();
		
		// Initializing the expected result
		int expected = 1; 
		
		assertEquals(expected, actual);
	}
	//
	//
	//
	
	//
	// getId Method Testcases
	// 
	
	@Test
	// This testcase creates new Process and checks getId Method
	void testGetId() {
		
		// Initializing test Process object
		Process testProcess = Process.create(5, 1, new ArrayList<>());
		
		// Initializing the actual result
		int actual = testProcess.getId();
		
		// Initializing the expected result
		int expected = 5; 
		
		assertEquals(expected, actual);
	}
	
	
}