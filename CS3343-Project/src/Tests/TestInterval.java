package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import Project.Interval;

public class TestInterval {
	@Test
	// Testing create() method in ProcessInCPU class
	void TestCreate() {
		//Initialize testing instance
		Interval testInterval = Interval.create(0,5);
		
		assertEquals(testInterval.getStart(), 0);
		assertEquals(testInterval.getEnd(), 5);
	}
	
	@Test
	// Testing getStart() method in ProcessInCPU class
	void TestGetStart() {
		//Initialize testing instance
		Interval testInterval = Interval.create(0,5);
		
		assertEquals(testInterval.getStart(), 0);
	}
	
	@Test
	// Testing getEnd() method in ProcessInCPU class
	void TestGetEnd() {
		//Initialize testing instance
		Interval testInterval = Interval.create(0,5);
		
		assertEquals(testInterval.getEnd(), 5);
	}
}
