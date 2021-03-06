package com.epam.networker.algorithms;

import com.epam.networker.entities.NetworkConnection;
import com.epam.networker.entities.Optimization;
import com.epam.networker.entities.OptimizationType;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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

	/**
	 * Checks if two connections are parallel. For example: (a,b,2) and (b,a,8) are parallel, but (a,b,2) and (c,d,4)
	 * are not. Connection is not parallel to itself.
	 *
	 * @param firstConnection
	 * @param secondConnection
	 * @return
	 */
	public static boolean connectionsAreParallel(
			NetworkConnection firstConnection, NetworkConnection secondConnection) {
		// Cannot optimize connection with itself
		if (firstConnection == secondConnection) {
			return false;
		}
		if (firstConnection.getStartNodeName() == secondConnection.getStartNodeName()
			&& firstConnection.getEndNodeName() == secondConnection.getEndNodeName()) {
			return true;
		}
		if (firstConnection.getStartNodeName() == secondConnection.getEndNodeName()
			&& firstConnection.getEndNodeName() == secondConnection.getStartNodeName()) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if two connections are in series. For example: (a,b,2) and (b,c,8) are parallel, but (a,b,2) and (c,d,4)
	 * are not. Connection is not in series with itself.
	 *
	 * @param firstConnection
	 * @param secondConnection
	 * @return
	 */
	public static boolean connectionsAreInSeries(
			NetworkConnection firstConnection, NetworkConnection secondConnection) {
		// Cannot optimize connection with itself
		if (firstConnection == secondConnection) {
			return false;
		}
		// It is a bad idea to optimize connections in series manner if they are parallel
		if (connectionsAreParallel(firstConnection, secondConnection)) {
			return false;
		}
		if (firstConnection.getStartNodeName() == secondConnection.getStartNodeName()
			|| firstConnection.getStartNodeName() == secondConnection.getEndNodeName()
			|| firstConnection.getEndNodeName() == secondConnection.getStartNodeName()
			|| firstConnection.getEndNodeName() == secondConnection.getEndNodeName()) {
			return true;
		}
		return false;
	}

	/**
	 * Returns a set of common nodes of two connections
	 */
	public static Set<Character> getCommonNodes(
			NetworkConnection firstConnection, NetworkConnection secondConnection) {
		Set<Character> commonNodes = new HashSet<Character>();
		if (firstConnection.getStartNodeName() == secondConnection.getStartNodeName()) {
			commonNodes.add(firstConnection.getStartNodeName());
		}
		if (firstConnection.getStartNodeName() == secondConnection.getEndNodeName()) {
			commonNodes.add(firstConnection.getStartNodeName());
		}
		if (firstConnection.getEndNodeName() == secondConnection.getStartNodeName()) {
			commonNodes.add(firstConnection.getEndNodeName());
		}
		if (firstConnection.getEndNodeName() == secondConnection.getEndNodeName()) {
			commonNodes.add(firstConnection.getEndNodeName());
		}
		return commonNodes;
	}

	/**
	 * Applies the given optimization to given list. It modifies the list by removing old connections and adding the new
	 * one (result of optimization)
	 *
	 * @param connections
	 * @param optimization
	 */
	public static void applyOptimization(List<NetworkConnection> connections, Optimization optimization) {
		NetworkConnection newConnection;
		if (optimization.getType() == OptimizationType.IN_SERIES) {
			newConnection = ConnectionsOptimizer.optimizeInSeriesConnections(
					optimization.getFirstConnection(), optimization.getSecondConnection());
		} else {
			newConnection = ConnectionsOptimizer.optimizeParallelConnections(
					optimization.getFirstConnection(), optimization.getSecondConnection());
		}

		connections.remove(optimization.getFirstConnection());
		connections.remove(optimization.getSecondConnection());
		connections.add(newConnection);
	}
}
