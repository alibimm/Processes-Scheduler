package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Project.AlgorithmType;

public class TestAlgorithmType {
	@Test
	// Testing toShort() method in AlgorithmType class
	void TestToShort() {
		assertEquals(AlgorithmType.FCFS.toShort(), "FCFS");
		assertEquals(AlgorithmType.RR.toShort(), "RR");
		assertEquals(AlgorithmType.FB.toShort(), "FB");
		assertEquals(AlgorithmType.SPN.toShort(), "SPN");
		assertEquals(AlgorithmType.SRT.toShort(), "SRT");
		assertEquals(AlgorithmType.HRRN.toShort(), "HRRN");
		assertEquals(AlgorithmType.None.toShort(), "None");
	}
	
	@Test
	// Testing toString() method in AlgorithmType class
	void TestToString() {
		assertEquals(AlgorithmType.FCFS.toString(), "First Come First Serve");
		assertEquals(AlgorithmType.RR.toString(), "Round Robin");
		assertEquals(AlgorithmType.FB.toString(), "Feedback");
		assertEquals(AlgorithmType.SPN.toString(), "Shortest Process Next");
		assertEquals(AlgorithmType.SRT.toString(), "Shortest Remaining Time Next");
		assertEquals(AlgorithmType.HRRN.toString(), "Highest Response Ratio Next");
		assertEquals(AlgorithmType.None.toString(), "None");
	}	
}
