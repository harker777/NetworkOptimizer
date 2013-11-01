package com.epam.networker.algorithms;

import com.epam.networker.entities.NetworkConnection;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author harker777
 */
public class ConnectionsOptimizerTest {

	public ConnectionsOptimizerTest() {
	}

	/**
	 * Checks if optimizeInSeries works right if common node is first in both connections, for
	 * example - (a,b,2), (a,c,2) -> (b,c,4)
	 */
	@Test
	public void testOptimizeInSeriesConnectionsWithCommonNodeFirstFirst() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 2);
		NetworkConnection secondConnection = new NetworkConnection('a', 'c', 2);
		NetworkConnection firstPossibleResult = new NetworkConnection('b', 'c', 4);
		NetworkConnection secondPossibleResult = new NetworkConnection('c', 'b', 4);
		NetworkConnection actualResult =
				ConnectionsOptimizer.optimizeInSeriesConnections(firstConnection, secondConnection);

		assertTrue(actualResult.equals(firstPossibleResult) || actualResult.equals(secondPossibleResult));
	}

	/**
	 * Checks if optimizeInSeries works right if common node is first in the first connection and
	 * second in the second connection, for example - (a,b,2), (c,a,2) -> (b,c,4)
	 */
	@Test
	public void testOptimizeInSeriesConnectionsWithCommonNodeFirstSecond() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 2);
		NetworkConnection secondConnection = new NetworkConnection('c', 'a', 2);
		NetworkConnection firstPossibleResult = new NetworkConnection('b', 'c', 4);
		NetworkConnection secondPossibleResult = new NetworkConnection('c', 'b', 4);
		NetworkConnection actualResult =
				ConnectionsOptimizer.optimizeInSeriesConnections(firstConnection, secondConnection);

		assertTrue(actualResult.equals(firstPossibleResult) || actualResult.equals(secondPossibleResult));
	}

	/**
	 * Checks if optimizeInSeries works right if common node is second the first connection and
	 * first in the second connection, for example - (b,a,2), (a,c,2) -> (b,c,4)
	 */
	@Test
	public void testOptimizeInSeriesConnectionsWithCommonNodeSecondFirst() {
		NetworkConnection firstConnection = new NetworkConnection('b', 'a', 2);
		NetworkConnection secondConnection = new NetworkConnection('a', 'c', 2);
		NetworkConnection firstPossibleResult = new NetworkConnection('b', 'c', 4);
		NetworkConnection secondPossibleResult = new NetworkConnection('c', 'b', 4);
		NetworkConnection actualResult =
				ConnectionsOptimizer.optimizeInSeriesConnections(firstConnection, secondConnection);

		assertTrue(actualResult.equals(firstPossibleResult) || actualResult.equals(secondPossibleResult));
	}

	/**
	 * Checks if optimizeInSeries works right if common node is second in both connections, for
	 * example - (b,a,2), (c,a,2) -> (b,c,4)
	 */
	@Test
	public void testOptimizeInSeriesConnectionsWithCommonNodeSecondSecond() {
		NetworkConnection firstConnection = new NetworkConnection('b', 'a', 2);
		NetworkConnection secondConnection = new NetworkConnection('c', 'a', 2);
		NetworkConnection firstPossibleResult = new NetworkConnection('b', 'c', 4);
		NetworkConnection secondPossibleResult = new NetworkConnection('c', 'b', 4);
		NetworkConnection actualResult =
				ConnectionsOptimizer.optimizeInSeriesConnections(firstConnection, secondConnection);

		assertTrue(actualResult.equals(firstPossibleResult) || actualResult.equals(secondPossibleResult));
	}

	/**
	 * Checks if optimizeParallel works right when both connections are hyperConductive, for example
	 * - (a,b,0), (b,a,0) -> (a,b,0)
	 */
	@Test
	public void testOptimizeParallelConnectionsWithBothHyperConductive() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 0);
		NetworkConnection secondConnection = new NetworkConnection('b', 'a', 0);
		NetworkConnection firstPossibleResult = new NetworkConnection('a', 'b', 0);
		NetworkConnection secondPossibleResult = new NetworkConnection('b', 'a', 0);
		NetworkConnection actualResult =
				ConnectionsOptimizer.optimizeParallelConnections(firstConnection, secondConnection);

		assertTrue(actualResult.equals(firstPossibleResult) || actualResult.equals(secondPossibleResult));
	}

	/**
	 * Checks if optimizeParallel works right when only first connection is hyperConductive, for
	 * example - (a,b,0), (b,a,2) -> (a,b,0)
	 */
	@Test
	public void testOptimizeParallelConnectionsWithFirstHyperConductive() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 0);
		NetworkConnection secondConnection = new NetworkConnection('b', 'a', 2);
		NetworkConnection firstPossibleResult = new NetworkConnection('a', 'b', 0);
		NetworkConnection secondPossibleResult = new NetworkConnection('b', 'a', 0);
		NetworkConnection actualResult =
				ConnectionsOptimizer.optimizeParallelConnections(firstConnection, secondConnection);

		assertTrue(actualResult.equals(firstPossibleResult) || actualResult.equals(secondPossibleResult));
	}

	/**
	 * Checks if optimizeParallel works right when only second connection is hyperConductive, for
	 * example - (a,b,2), (b,a,0) -> (a,b,0)
	 */
	@Test
	public void testOptimizeParallelConnectionsWithSecondHyperConductive() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 2);
		NetworkConnection secondConnection = new NetworkConnection('b', 'a', 0);
		NetworkConnection firstPossibleResult = new NetworkConnection('a', 'b', 0);
		NetworkConnection secondPossibleResult = new NetworkConnection('b', 'a', 0);
		NetworkConnection actualResult =
				ConnectionsOptimizer.optimizeParallelConnections(firstConnection, secondConnection);

		assertTrue(actualResult.equals(firstPossibleResult) || actualResult.equals(secondPossibleResult));
	}

	/**
	 * Checks if optimizeParallel works right when both connections are not hyperConductive, for
	 * example - (a,b,8), (b,a,8) -> (a,b,4)
	 */
	@Test
	public void testOptimizeParallelConnectionsWithNoneHyperConductive() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 8);
		NetworkConnection secondConnection = new NetworkConnection('b', 'a', 8);
		NetworkConnection firstPossibleResult = new NetworkConnection('a', 'b', 4);
		NetworkConnection secondPossibleResult = new NetworkConnection('b', 'a', 4);
		NetworkConnection actualResult =
				ConnectionsOptimizer.optimizeParallelConnections(firstConnection, secondConnection);

		assertTrue(actualResult.equals(firstPossibleResult) || actualResult.equals(secondPossibleResult));
	}
}
