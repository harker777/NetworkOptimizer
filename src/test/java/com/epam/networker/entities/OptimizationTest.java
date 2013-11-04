package com.epam.networker.entities;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Iaroslav_Mazai
 */
public class OptimizationTest {

	public OptimizationTest() {
	}

	@Test
	public void testIsParallelWithOnlyOneHyperConductiveReturnsTrueOnParallelWithOneHyperCoductive() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 0);
		NetworkConnection secondConnection = new NetworkConnection('b', 'a', 2);
		Optimization optimization = new Optimization(firstConnection, secondConnection, OptimizationType.PARALLEL);

		boolean expResult = true;
		boolean actualResult = optimization.isParallelWithOnlyOneHyperConductive();
		assertEquals(expResult, actualResult);
	}

	@Test
	public void testIsParallelWithOnlyOneHyperConductiveReturnsFalseOnParallelWithBothHyperCoductive() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 0);
		NetworkConnection secondConnection = new NetworkConnection('b', 'a', 0);
		Optimization optimization = new Optimization(firstConnection, secondConnection, OptimizationType.PARALLEL);

		boolean expResult = false;
		boolean actualResult = optimization.isParallelWithOnlyOneHyperConductive();
		assertEquals(expResult, actualResult);
	}

	@Test
	public void testIsParallelWithOnlyOneHyperConductiveReturnsFalseOnParallelWithNoHyperCoductive() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 4);
		NetworkConnection secondConnection = new NetworkConnection('b', 'a', 2);
		Optimization optimization = new Optimization(firstConnection, secondConnection, OptimizationType.PARALLEL);

		boolean expResult = false;
		boolean actualResult = optimization.isParallelWithOnlyOneHyperConductive();
		assertEquals(expResult, actualResult);
	}

	@Test
	public void testIsParallelWithOnlyOneHyperConductiveReturnsFalseOnInSeries() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 4);
		NetworkConnection secondConnection = new NetworkConnection('b', 'c', 0);
		Optimization optimization = new Optimization(firstConnection, secondConnection, OptimizationType.IN_SERIES);

		boolean expResult = false;
		boolean actualResult = optimization.isParallelWithOnlyOneHyperConductive();
		assertEquals(expResult, actualResult);
	}

	@Test
	public void testIsParallelWithBothHyperConductiveReturnsTrueOnParallelWithBothHyperConductive() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 0);
		NetworkConnection secondConnection = new NetworkConnection('b', 'a', 0);
		Optimization optimization = new Optimization(firstConnection, secondConnection, OptimizationType.PARALLEL);

		boolean expResult = true;
		boolean actualResult = optimization.isParallelWithBothHyperConductive();
		assertEquals(expResult, actualResult);
	}

	@Test
	public void testIsParallelWithBothHyperConductiveReturnsFalseOnParallelWithOnlyOneHyperConductive() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 0);
		NetworkConnection secondConnection = new NetworkConnection('b', 'a', 2);
		Optimization optimization = new Optimization(firstConnection, secondConnection, OptimizationType.PARALLEL);

		boolean expResult = false;
		boolean actualResult = optimization.isParallelWithBothHyperConductive();
		assertEquals(expResult, actualResult);
	}

	@Test
	public void testIsParallelWithBothHyperConductiveReturnsFalseOnParallelWithNoneHyperConductive() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 4);
		NetworkConnection secondConnection = new NetworkConnection('b', 'a', 2);
		Optimization optimization = new Optimization(firstConnection, secondConnection, OptimizationType.PARALLEL);

		boolean expResult = false;
		boolean actualResult = optimization.isParallelWithBothHyperConductive();
		assertEquals(expResult, actualResult);
	}

	@Test
	public void testIsParallelWithBothHyperConductiveReturnsFalseOnInSeries() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 4);
		NetworkConnection secondConnection = new NetworkConnection('b', 'c', 2);
		Optimization optimization = new Optimization(firstConnection, secondConnection, OptimizationType.IN_SERIES);

		boolean expResult = false;
		boolean actualResult = optimization.isParallelWithBothHyperConductive();
		assertEquals(expResult, actualResult);
	}

	@Test
	public void testIsParallelWithoutHyperConductiveReturnsTrueOnParallelWithoutHyperConductive() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 4);
		NetworkConnection secondConnection = new NetworkConnection('b', 'a', 2);
		Optimization optimization = new Optimization(firstConnection, secondConnection, OptimizationType.PARALLEL);

		boolean expResult = true;
		boolean actualResult = optimization.isParallelWithoutHyperConductive();
		assertEquals(expResult, actualResult);
	}

	@Test
	public void testIsParallelWithoutHyperConductiveReturnsFalseOnParallelWithOneHyperConductive() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 0);
		NetworkConnection secondConnection = new NetworkConnection('b', 'a', 2);
		Optimization optimization = new Optimization(firstConnection, secondConnection, OptimizationType.PARALLEL);

		boolean expResult = false;
		boolean actualResult = optimization.isParallelWithoutHyperConductive();
		assertEquals(expResult, actualResult);
	}

	@Test
	public void testIsParallelWithoutHyperConductiveReturnsFalseOnParallelWithBothHyperConductive() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 0);
		NetworkConnection secondConnection = new NetworkConnection('b', 'a', 0);
		Optimization optimization = new Optimization(firstConnection, secondConnection, OptimizationType.PARALLEL);

		boolean expResult = false;
		boolean actualResult = optimization.isParallelWithoutHyperConductive();
		assertEquals(expResult, actualResult);
	}

	@Test
	public void testIsParallelWithoutHyperConductiveReturnsFalseOnInSeries() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 0);
		NetworkConnection secondConnection = new NetworkConnection('b', 'c', 8);
		Optimization optimization = new Optimization(firstConnection, secondConnection, OptimizationType.IN_SERIES);

		boolean expResult = false;
		boolean actualResult = optimization.isParallelWithoutHyperConductive();
		assertEquals(expResult, actualResult);
	}

	@Test
	public void testIsInSeriesReturnsTruesOnInSeries() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 0);
		NetworkConnection secondConnection = new NetworkConnection('b', 'c', 8);
		Optimization optimization = new Optimization(firstConnection, secondConnection, OptimizationType.IN_SERIES);

		boolean expResult = true;
		boolean actualResult = optimization.isInSeries();
		assertEquals(expResult, actualResult);
	}

	@Test
	public void testIsInSeriesReturnsFalseOnParallel() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 0);
		NetworkConnection secondConnection = new NetworkConnection('b', 'a', 8);
		Optimization optimization = new Optimization(firstConnection, secondConnection, OptimizationType.PARALLEL);

		boolean expResult = false;
		boolean actualResult = optimization.isInSeries();
		assertEquals(expResult, actualResult);
	}

	@Test
	public void testIsInSeriesReturnsFourOnParallelWithOnlyOneHyperConductive() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 0);
		NetworkConnection secondConnection = new NetworkConnection('b', 'a', 8);
		Optimization optimization = new Optimization(firstConnection, secondConnection, OptimizationType.PARALLEL);

		int expResult = 4;
		int actualResult = optimization.getRank();
		assertEquals(expResult, actualResult);
	}

	@Test
	public void testIsInSeriesReturnsThreeOnParallelWithoutHyperConductive() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 4);
		NetworkConnection secondConnection = new NetworkConnection('b', 'a', 8);
		Optimization optimization = new Optimization(firstConnection, secondConnection, OptimizationType.PARALLEL);

		int expResult = 3;
		int actualResult = optimization.getRank();
		assertEquals(expResult, actualResult);
	}

	@Test
	public void testIsInSeriesReturnsTwoOnParallelWithBothHyperConductive() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 0);
		NetworkConnection secondConnection = new NetworkConnection('b', 'a', 0);
		Optimization optimization = new Optimization(firstConnection, secondConnection, OptimizationType.PARALLEL);

		int expResult = 2;
		int actualResult = optimization.getRank();
		assertEquals(expResult, actualResult);
	}

	@Test
	public void testIsInSeriesReturnsFourOnInSeries() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 0);
		NetworkConnection secondConnection = new NetworkConnection('b', 'c', 4);
		Optimization optimization = new Optimization(firstConnection, secondConnection, OptimizationType.IN_SERIES);

		int expResult = 1;
		int actualResult = optimization.getRank();
		assertEquals(expResult, actualResult);
	}
}
