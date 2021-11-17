package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import Project.Service;
import Project.ServiceType;

class TestService {

	// Test getServiceTime Method
	@Test
	// 
	void testGetServiceTime() {
		
		// Initializing dummy service
		Service testService = Service.create("C", "5");
		
		// Initializing actual result
		int actual = testService.getServiceTime();
		
		// Initializing expected result
		int expected = 5;
		
		assertEquals(expected, actual);
		
	}
	
	
	// Test getType Method
	@Test
	// Test CPU Type
	void testGetTypeIsCPU() {
		
		// Initializing dummy service
		Service testService = Service.create("C", "5");
		
		// Initializing actual result
		ServiceType actual = testService.getType();
		
		// Initializing expected result
		ServiceType expected = ServiceType.CPU;
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	// Test Keyboard Type
	void testGetTypeIsKeyboard() {
		
		// Initializing dummy service
		Service testService = Service.create("K", "5");
		
		// Initializing actual result
		ServiceType actual = testService.getType();
		
		// Initializing expected result
		ServiceType expected = ServiceType.Keyboard;
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	// Test Invalid Type
	void testGetTypeIsNull() {
		
		// Initializing dummy service
		Service testService = Service.create("Q", "5");
		
		// Initializing actual result
		Service actual = testService;
		
		// Initializing expected result
		Service expected = null;
		
		assertEquals(expected, actual);
		
	}
}
