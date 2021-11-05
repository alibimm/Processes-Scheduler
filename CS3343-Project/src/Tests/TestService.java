package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Project.MainSystem;
import Project.Service;
import Project.ServiceType;

class TestService {

	@Test
	void testGetServiceTime() {
		
		Service testService = Service.create("C", "5");
		
		Double testServiceTime = testService.getServiceTime();
		
		assertEquals(testServiceTime, Double.parseDouble("5"));
		
	}
	
	@Test
	void testGetTypeIsCPU() {
		
		Service testService = Service.create("C", "5");
		
		ServiceType testType = testService.getType();
		
		assertEquals(testType, ServiceType.CPU);
		
	}
	
	@Test
	void testGetTypeIsKeyboard() {
		
		Service testService = Service.create("K", "5");
		
		ServiceType testType = testService.getType();
		
		assertEquals(testType, ServiceType.Keyboard);
		
	}

}
