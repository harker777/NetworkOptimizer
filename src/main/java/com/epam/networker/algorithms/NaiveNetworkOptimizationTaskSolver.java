package com.epam.networker.algorithms;

import com.epam.networker.entities.NetworkConnection;
import com.epam.networker.entities.NetworkOptimizationTask;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Iaroslav_Mazai
 */
public class NaiveNetworkOptimizationTaskSolver implements NetworkOptimizationTaskSolver {

	private List<NetworkConnection> connections;
	private char startNodeName;
	private char endNodeName;

	public List<NetworkConnection> solve(NetworkOptimizationTask taskToSolve) {
		connections = new LinkedList<NetworkConnection>(taskToSolve.getConnections());

		// TODO: implement the algorithm

		return connections;
	}
}
