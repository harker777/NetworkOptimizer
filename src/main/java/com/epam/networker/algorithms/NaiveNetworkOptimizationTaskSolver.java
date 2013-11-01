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

	private List<NetworkConnection> tempConnections;
	private NetworkOptimizationTask task;

	public List<NetworkConnection> solve(NetworkOptimizationTask taskToSolve) {
		this.tempConnections = new LinkedList<NetworkConnection>(taskToSolve.getConnections());
		this.task = taskToSolve;

		// TODO: implement the algorithm

		return tempConnections;
	}
}
