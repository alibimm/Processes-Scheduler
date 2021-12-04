package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import Project.Service;
import Project.ServiceType;

class TestService {

	@Test
	void testGetServiceTime() {

		Service testService = Service.create("C", "5");
		int actual = testService.getServiceTime();
		int expected = 5;
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetTypeIsCPU() {
		Service testService = Service.create("C", "5");
		ServiceType actual = testService.getType();
		ServiceType expected = ServiceType.CPU;
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetTypeIsKeyboard() {
		Service testService = Service.create("K", "5");
		ServiceType actual = testService.getType();
		ServiceType expected = ServiceType.Keyboard;
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetTypeIsNull() {
		Service testService = Service.create("Q", "5");
		Service actual = testService;
		Service expected = null;

		assertEquals(expected, actual);
	}
}
