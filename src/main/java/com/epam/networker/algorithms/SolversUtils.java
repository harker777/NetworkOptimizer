package com.epam.networker.algorithms;

import com.epam.networker.entities.NetworkConnection;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * This class includes some utility methods for solvers (determining connection type etc.)
 *
 * @author Iaroslav_Mazai
 */
public class SolversUtils {

	/**
	 * Returns list of intermediate connections (ones that don't have start or end nodes in them)
	 *
	 * @param connections
	 * @param startNodeName
	 * @param endNodeName
	 * @return
	 */
	public static List<NetworkConnection> getIntermediateConnections(
			Collection<NetworkConnection> connections,
			char startNodeName, char endNodeName) {
		List<NetworkConnection> intermediateConnections = new LinkedList<NetworkConnection>();
		for (NetworkConnection connection : connections) {
			if (isConnectionIntermediate(connection, startNodeName, endNodeName)) {
				intermediateConnections.add(connection);
			}
		}
		return intermediateConnections;
	}

	/**
	 * Returns list of boundary connections (ones that have start or end nodes in them)
	 *
	 * @param connections
	 * @param startNodeName
	 * @param endNodeName
	 * @return
	 */
	public static List<NetworkConnection> getBoundaryConnections(
			Collection<NetworkConnection> connections, char startNodeName, char endNodeName) {
		List<NetworkConnection> boundaryConnections = new LinkedList<NetworkConnection>();
		for (NetworkConnection connection : connections) {
			if (isConnectionBoundary(connection, startNodeName, endNodeName)) {
				boundaryConnections.add(connection);
			}
		}
		return boundaryConnections;
	}

	/**
	 * Checks if connection is intermediate (doesn't have start or end node in it)
	 *
	 * @param connection
	 * @param startNodeName
	 * @param endNodeName
	 * @return
	 */
	public static boolean isConnectionIntermediate(
			NetworkConnection connection, char startNodeName, char endNodeName) {
		if (connection.getStartNodeName() == startNodeName
				|| connection.getStartNodeName() == endNodeName
				|| connection.getEndNodeName() == startNodeName
				|| connection.getEndNodeName() == endNodeName) {
			return false;
		}

		return true;
	}

	/**
	 * Checks if connection is boundary (does have start or end node in it)
	 *
	 * @param connection
	 * @param startNodeName
	 * @param endNodeName
	 * @return
	 */
	public static boolean isConnectionBoundary(
			NetworkConnection connection, char startNodeName, char endNodeName) {
		return !isConnectionIntermediate(connection, startNodeName, endNodeName);
	}

	/**
	 * Check if connection is complete (connects startNode and endNode)
	 *
	 * @param connection
	 * @param startNodeName
	 * @param endNodeName
	 * @return
	 */
	public static boolean isConnectionComplete(
			NetworkConnection connection, char startNodeName, char endNodeName) {
		if (connection.getStartNodeName() == startNodeName
				&& connection.getEndNodeName() == endNodeName) {
			return true;
		}
		if (connection.getStartNodeName() == endNodeName
				&& connection.getEndNodeName() == startNodeName) {
			return true;
		}

		return false;
	}
}
