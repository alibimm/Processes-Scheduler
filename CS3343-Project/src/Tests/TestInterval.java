package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import Project.Interval;

public class TestInterval {
	
	//Initialize testing instance
	Interval testInterval = Interval.create(0,5);
			
			
	@Test
	// Testing create() method in Interval class
	void TestCreate() {
		assertEquals(testInterval.getStart(), 0);
		assertEquals(testInterval.getEnd(), 5);
	}
	
	@Test
	// Testing getStart() method in Interval class
	void TestGetStart() {
		assertEquals(testInterval.getStart(), 0);
	}
	
	@Test
	// Testing getEnd() method in Interval class
	void TestGetEnd() {
		assertEquals(testInterval.getEnd(), 5);
	}
}
