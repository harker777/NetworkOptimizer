/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.networker.algorithms;

import com.epam.networker.entities.NetworkConnection;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Iaroslav_Mazai
 */
public class SolversUtilsTest {

	List<NetworkConnection> connections;
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
			new NetworkConnection('a', 'e', 2),
			new NetworkConnection('e', 'f', 4),
			new NetworkConnection('f', 'g', 4),
			new NetworkConnection('d', 'g', 8),
			new NetworkConnection('a', 'g', 8)
		};
		connections = new LinkedList(Arrays.asList(connectionsArray));
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
}
