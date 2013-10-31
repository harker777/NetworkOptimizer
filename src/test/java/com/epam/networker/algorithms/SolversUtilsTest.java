/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.networker.algorithms;

import com.epam.networker.entities.NetworkConnection;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Iaroslav_Mazai
 */
public class SolversUtilsTest {

	List<NetworkConnection> connections;
	List<NetworkConnection> intermediateConnections;
	List<NetworkConnection> boundaryConnections;
	char startNodeName;
	char endNodeName;
	NetworkConnection intermediateConnection;
	NetworkConnection boundaryConnection;
	NetworkConnection completeConnection;

	public SolversUtilsTest() {
		NetworkConnection[] connectionsArray = {
			new NetworkConnection('a', 'b', 2),
			new NetworkConnection('b', 'c', 2),
			new NetworkConnection('c', 'd', 4),
			new NetworkConnection('d', 'g', 4),
			new NetworkConnection('a', 'e', 2),
			new NetworkConnection('e', 'f', 4),
			new NetworkConnection('f', 'g', 4),
			new NetworkConnection('a', 'g', 8)
		};
		NetworkConnection[] intermediateConnectionsArray = {
			new NetworkConnection('b', 'c', 2),
			new NetworkConnection('c', 'd', 4),
			new NetworkConnection('e', 'f', 4)
		};
		NetworkConnection[] boundaryConnectionsArray = {
			new NetworkConnection('a', 'b', 2),
			new NetworkConnection('d', 'g', 4),
			new NetworkConnection('a', 'e', 2),
			new NetworkConnection('f', 'g', 4),
			new NetworkConnection('a', 'g', 8)
		};

		connections = new LinkedList(Arrays.asList(connectionsArray));
		intermediateConnections = new LinkedList<NetworkConnection>(Arrays.asList(intermediateConnectionsArray));
		boundaryConnections = new LinkedList<NetworkConnection>(Arrays.asList(boundaryConnectionsArray));

		startNodeName = 'a';
		endNodeName = 'g';
		intermediateConnection = new NetworkConnection('b', 'c', 2);
		boundaryConnection = new NetworkConnection('a', 'b', 2);
		completeConnection = new NetworkConnection('a', 'g', 2);
	}

	/**
	 * Checks if isConnectionIntermediate returns true on intermediate connection
	 */
	@Test
	public void testIsConnectionIntermediateReturnsTrueOnIntermediateConnection() {
		boolean expResult = true;
		boolean actualResult = SolversUtils.isConnectionIntermediate(intermediateConnection, startNodeName, endNodeName);
		assertEquals(expResult, actualResult);
	}

	/**
	 * Checks if isConnectionIntermediate returns false on boundary connection
	 */
	@Test
	public void testIsConnectionIntermediateReturnsFalseOnBoundaryConnection() {
		boolean expResult = false;
		boolean actualResult = SolversUtils.isConnectionIntermediate(boundaryConnection, startNodeName, endNodeName);
		assertEquals(expResult, actualResult);
	}

	/**
	 * Checks if isConnectionBoundary returns true on boundary connection
	 */
	@Test
	public void testIsConnectionBoundaryReturnsTrueOnBoundaryConnection() {
		boolean expResult = true;
		boolean actualResult = SolversUtils.isConnectionBoundary(boundaryConnection, startNodeName, endNodeName);
		assertEquals(expResult, actualResult);
	}

	/**
	 * Checks if isConnectionBoundary return false on intermediate connection
	 */
	@Test
	public void testIsConnectionBoundaryReturnsFalseOnNotBoundaryConnection() {
		boolean expResult = false;
		boolean actualResult = SolversUtils.isConnectionBoundary(intermediateConnection, startNodeName, endNodeName);
		assertEquals(expResult, actualResult);
	}

	/**
	 * Checks if isConnectionBoundary returns true on complete connection
	 */
	@Test
	public void testIsConnectionBoundaryReturnsTrueOnCompleteConnection() {
		boolean expResult = true;
		boolean actualResult = SolversUtils.isConnectionBoundary(completeConnection, startNodeName, endNodeName);
		assertEquals(expResult, actualResult);
	}

	/**
	 * Checks if isConnectionComplete returns true on complete connection
	 */
	@Test
	public void testIsConnectionCompleteReturnsTrueOnCompleteConnection() {
		boolean expResult = true;
		boolean actualResult = SolversUtils.isConnectionComplete(completeConnection, startNodeName, endNodeName);
		assertEquals(expResult, actualResult);
	}

	/**
	 * Checks if isConnectionComplete returns false on intermediate connection
	 */
	@Test
	public void testIsConnectionCompleteReturnsFalseOnIntermediateConnection() {
		boolean expResult = false;
		boolean actualResult = SolversUtils.isConnectionComplete(intermediateConnection, startNodeName, endNodeName);
		assertEquals(expResult, actualResult);
	}

	/**
	 * Checks if isConnectionComplete returns false on boundary but not complete connection
	 */
	@Test
	public void testIsConnectionCompleteReturnsFalseOnBoundaryButNotCompleteConnection() {
		boolean expResult = false;
		boolean actualResult = SolversUtils.isConnectionComplete(boundaryConnection, startNodeName, endNodeName);
		assertEquals(expResult, actualResult);
	}

	/**
	 * Checks if getIntermediateConnections returns intermediate connections
	 */
	@Test
	public void testGetIntermediateConnectionsReturnsIntermediateConnections() {
		NetworkConnection[] expResult = intermediateConnections.toArray(new NetworkConnection[]{});
		NetworkConnection[] actualResult = SolversUtils.getIntermediateConnections(connections, startNodeName, endNodeName).
				toArray(new NetworkConnection[]{});
		System.out.println(Arrays.asList(expResult));
		System.out.println(Arrays.asList(actualResult));
		assertArrayEquals(expResult, actualResult);
	}

	/**
	 * Checks if getIntermediateConnections returns intermediate connections
	 */
	@Test
	public void testGetBoundaryConnectionsReturnsBoundaryConnections() {
		NetworkConnection[] expResult = boundaryConnections.toArray(new NetworkConnection[]{});
		NetworkConnection[] actualResult = SolversUtils.getBoundaryConnections(connections, startNodeName, endNodeName).
				toArray(new NetworkConnection[]{});
		assertArrayEquals(expResult, actualResult);
	}

	/**
	 * Checks if connectionsAreParallel returns true on parallel connections with same direction
	 * (for example, (a,b,2) and (a,b,4))
	 */
	@Test
	public void testConnectionsAreParallelReturnsTrueOnParallelSameDirection() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 2);
		NetworkConnection secondConnection = new NetworkConnection('a', 'b', 4);
		boolean expResult = true;
		boolean actualResult = SolversUtils.connectionsAreParallel(firstConnection, secondConnection);
		assertEquals(expResult, actualResult);
	}

	/**
	 * Checks if connectionsAreParallel returns true on parallel connections with different
	 * direction (for example, (a,b,2) and (b,a,4))
	 */
	@Test
	public void testConnectionsAreParallelReturnsTrueOnParallelDifferentDirection() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 2);
		NetworkConnection secondConnection = new NetworkConnection('b', 'a', 4);
		boolean expResult = true;
		boolean actualResult = SolversUtils.connectionsAreParallel(firstConnection, secondConnection);
		assertEquals(expResult, actualResult);
	}

	/**
	 * Checks if connectionsAreParallel returns false on same connection
	 */
	@Test
	public void testConnectionsAreParallelReturnsFalseOnSameConnection() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 2);
		boolean expResult = false;
		boolean actualResult = SolversUtils.connectionsAreParallel(firstConnection, firstConnection);
		assertEquals(expResult, actualResult);
	}

	/**
	 * Checks if connectionsAreParallel returns false on absolutely different connections (for
	 * example - (a,b,2) and (c,d,4))
	 */
	@Test
	public void testConnectionsAreParallelReturnsFalseOnAbsolutelyDifferentConnections() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 2);
		NetworkConnection secondConnection = new NetworkConnection('c', 'd', 4);
		boolean expResult = false;
		boolean actualResult = SolversUtils.connectionsAreParallel(firstConnection, secondConnection);
		assertEquals(expResult, actualResult);
	}

	/**
	 * Checks if connectionsAreParallel returns false on connections with one common node (for
	 * example - (a,b,2) and (b,d,4))
	 */
	@Test
	public void testConnectionsAreParallelReturnsFalseOnConnectionsWithOneCommonNode() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 2);
		NetworkConnection secondConnection = new NetworkConnection('b', 'd', 4);
		boolean expResult = false;
		boolean actualResult = SolversUtils.connectionsAreParallel(firstConnection, secondConnection);
		assertEquals(expResult, actualResult);
	}

	/**
	 * Checks if connectionsAreInSeries returns true on connections in series with same direction
	 * (for example - (a,b,2) and (b,d,4))
	 */
	@Test
	public void testConnectionsAreInSeriesReturnsTrueOnConnectionsWithSameDirection() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 2);
		NetworkConnection secondConnection = new NetworkConnection('b', 'd', 4);
		boolean expResult = true;
		boolean actualResult = SolversUtils.connectionsAreInSeries(firstConnection, secondConnection);
		assertEquals(expResult, actualResult);
	}

	/**
	 * Checks if connectionsAreInSeries returns true on connections in series with different
	 * directions (for example - (a,b,2) and (d,b,4))
	 */
	@Test
	public void testConnectionsAreInSeriesReturnsTrueOnConnectionsWithDifferentDirection() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 2);
		NetworkConnection secondConnection = new NetworkConnection('d', 'b', 4);
		boolean expResult = true;
		boolean actualResult = SolversUtils.connectionsAreInSeries(firstConnection, secondConnection);
		assertEquals(expResult, actualResult);
	}

	/**
	 * Checks if connectionsAreInSeries returns false on connections which are absolutely not
	 * connected (for example - (a,b,2) and (c,d,2))
	 */
	@Test
	public void testConnectionsAreInSeriesReturnsFalseOnAbsolutelyDifferentConnections() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 2);
		NetworkConnection secondConnection = new NetworkConnection('c', 'd', 2);
		boolean expResult = false;
		boolean actualResult = SolversUtils.connectionsAreInSeries(firstConnection, secondConnection);
		assertEquals(expResult, actualResult);
	}

	/**
	 * Checks if connectionsAreInSeries returns false on same connection
	 */
	@Test
	public void testConnectionsAreInSeriesReturnsFalseOnSameConnection() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 2);
		boolean expResult = false;
		boolean actualResult = SolversUtils.connectionsAreInSeries(firstConnection, firstConnection);
		assertEquals(expResult, actualResult);
	}

	/**
	 * Checks if connectionsAreInSeries returns false on parallel connections (for example - (a,b,2)
	 * and (b,a,4))
	 */
	@Test
	public void testConnectionsAreInSeriesReturnsFalseOnParallelConnections() {
		NetworkConnection firstConnection = new NetworkConnection('a', 'b', 2);
		NetworkConnection secondConnection = new NetworkConnection('b', 'a', 4);
		boolean expResult = false;
		boolean actualResult = SolversUtils.connectionsAreInSeries(firstConnection, secondConnection);
		assertEquals(expResult, actualResult);
	}
}
