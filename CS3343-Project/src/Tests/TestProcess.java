package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Project.Process;
import Project.Service;
import Project.ServiceType;

public class TestProcess {

	@Test
	void testProceedToNextServiceTrue() {
		ArrayList<Service> services = new ArrayList<>();
		
		Service s = Service.create("C", "5");
		
		services.add(s);
		
		Process testProcess = Process.create(0, 0, services);
		
		boolean testProceed = testProcess.proceedToNextService();
		
		assertEquals(testProceed, true);
	}
	
	
	@Test
	void testProceedToNextServiceFalse() {
		ArrayList<Service> services = new ArrayList<>();
		
		Service s = Service.create("C", "5");
		Service d = Service.create("K", "4");
		
		services.add(s);
		services.add(d);
		
		Process testProcess = Process.create(0, 0, services);
		
		boolean testProceed = testProcess.proceedToNextService();
		
		assertEquals(testProceed, false);
	}
	
	
	@Test
	void testIsCurServiceOverTrue() {
		ArrayList<Service> services = new ArrayList<>();
		
		Service s = Service.create("C", "0");
		
		services.add(s);
		
		Process testProcess = Process.create(0, 0, services);
		
		boolean testProceed = testProcess.isCurServiceOver();
		
		assertEquals(testProceed, true);
	}
	
	
	@Test
	void testIsCurServiceOverFalse() {
		ArrayList<Service> services = new ArrayList<>();
		
		Service s = Service.create("C", "5");
		
		services.add(s);
		
		Process testProcess = Process.create(0, 0, services);
		
		boolean testProceed = testProcess.isCurServiceOver();
		
		assertEquals(testProceed, false);
	}
	
	
	@Test
	void testGetArrivalTime() {
		ArrayList<Service> services = new ArrayList<>();
		
		Service s = Service.create("C", "5");
		
		services.add(s);
		
		Process testProcess = Process.create(0, 1, services);
		
		double testProceed = testProcess.getArrivalTime();
		
		assertEquals(testProceed, 1);
	}
	
	
	@Test
	void testGetCurService() {
		ArrayList<Service> services = new ArrayList<>();
		
		Service s = Service.create("C", "5");
		
		services.add(s);
		
		Process testProcess = Process.create(0, 1, services);
		
		Service testProceed = testProcess.getCurService();
		
		assertEquals(testProceed, services.get(0));
	}
	
	
	@Test
	void testGetId() {
		ArrayList<Service> services = new ArrayList<>();
		
		Service s = Service.create("C", "5");
		
		services.add(s);
		
		Process testProcess = Process.create(5, 1, services);
		
		int testProceed = testProcess.getId();
		
		assertEquals(testProceed, 5);
	}
	
	
	@Test
	void testGetServices() {
		ArrayList<Service> services = new ArrayList<>();
		
		Service s = Service.create("C", "5");
		
		services.add(s);
		
		Process testProcess = Process.create(0, 1, services);
		
		ArrayList<Service> testProceed = testProcess.getServices();
		
		assertEquals(testProceed, services);
	}
	
	
	@Test
	void testSetServices() {
		ArrayList<Service> services = new ArrayList<>();
		ArrayList<Service> services_another = new ArrayList<>();
		
		Service s = Service.create("C", "5");
		Service d = Service.create("K", "4");
		
		services.add(s);
		services_another.add(d);
		
		Process testProcess = Process.create(0, 1, services);
		
		testProcess.setServices(services_another);
		
		assertEquals(testProcess.getServices(), services_another);
	}
	
	
	@Test
	void testGetCurServiceTime() {
		ArrayList<Service> services = new ArrayList<>();
		
		Service s = Service.create("C", "5");

		services.add(s);

		Process testProcess = Process.create(0, 1, services);
		
		double testProceed = testProcess.getCurServiceTime();
		
		assertEquals(testProceed, 5.0);
	}
	
	
	@Test
	void testGetCurServiceType() {
		ArrayList<Service> services = new ArrayList<>();
		
		Service s = Service.create("C", "5");

		services.add(s);

		Process testProcess = Process.create(0, 1, services);
		
		ServiceType testProceed = testProcess.getCurServiceType();
		
		assertEquals(testProceed, ServiceType.CPU);	
	}
	
	
	@Test
	void testGetCpuQt() {
		ArrayList<Service> services = new ArrayList<>();
		
		Service s = Service.create("C", "5");

		services.add(s);

		Process testProcess = Process.create(0, 1, services);
		
		double testProceed = testProcess.getCPUQT();
		
		assertEquals(testProceed, 0.0);	
	}
	
	@Test
	void testGetKeybQt() {
		ArrayList<Service> services = new ArrayList<>();
		
		Service s = Service.create("K", "5");

		services.add(s);

		Process testProcess = Process.create(0, 1, services);
		
		double testProceed = testProcess.getKeybQT();
		
		assertEquals(testProceed, 0.0);	
	}
	
	
	@Test
	void testUpdateQtCpu() {
		ArrayList<Service> services = new ArrayList<>();
		
		Service s = Service.create("C", "5");

		services.add(s);

		Process testProcess = Process.create(0, 1, services);
		
		testProcess.updateQueueingTime();
		
		assertEquals(testProcess.getCPUQT(), 1.0);	
	}
	
	
	@Test
	void testUpdateQtKeyboard() {
		ArrayList<Service> services = new ArrayList<>();
		
		Service s = Service.create("K", "5");

		services.add(s);

		Process testProcess = Process.create(0, 1, services);
		
		testProcess.updateQueueingTime();
		
		assertEquals(testProcess.getKeybQT(), 1.0);	
	}
}