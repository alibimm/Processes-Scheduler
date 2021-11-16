package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exceptions.ExInvalidServiceType;
import Project.MainSystem;
import Project.Service;
import Project.ServiceType;

class TestService {

	// Test getServiceTime Method
	@Test
	// 
	void testGetServiceTime() throws ExInvalidServiceType {
		
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
	void testGetTypeIsCPU() throws ExInvalidServiceType {
		
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
	void testGetTypeIsKeyboard() throws ExInvalidServiceType {
		
		// Initializing dummy service
		Service testService = Service.create("K", "5");
		
		// Initializing actual result
		ServiceType actual = testService.getType();
		
		// Initializing expected result
		ServiceType expected = ServiceType.Keyboard;
		
		assertEquals(expected, actual);
		
	}
}
