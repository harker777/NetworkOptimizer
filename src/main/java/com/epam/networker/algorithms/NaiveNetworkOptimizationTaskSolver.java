package com.epam.networker.algorithms;

import com.epam.networker.entities.NetworkConnection;
import com.epam.networker.entities.NetworkOptimizationTask;
import com.epam.networker.entities.Optimization;
import com.epam.networker.entities.OptimizationType;
import java.util.Collections;
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

		while (true) {
			// First, try to optimize internal connections
			boolean someOptimizationSucceded = false;
			boolean internalOptimizationSucceded = applySomeInternalConnectionsOptimization();

			if (!internalOptimizationSucceded) {
				someOptimizationSucceded = applySomeConnectionsOptimization();
			}
			if (!internalOptimizationSucceded && !someOptimizationSucceded) {
				break;
			}
		}

		return this.tempConnections;
	}

	private boolean applySomeInternalConnectionsOptimization() {
		List<NetworkConnection> intermediateConnections = SolversUtils.getIntermediateConnections(
				tempConnections, task.getStartNodeName(), task.getEndNodeName());
		List<Optimization> possibleIntermediateOptimizations = new LinkedList<Optimization>();

		// Find all possible internal optimizations
		for (NetworkConnection firstNetworkConnection : intermediateConnections) {
			for (NetworkConnection secondNetworkConnection : intermediateConnections) {
				boolean connectionsAreParallel = SolversUtils.
						connectionsAreParallel(firstNetworkConnection, secondNetworkConnection);
				boolean connectionsAreInSeries = SolversUtils.
						connectionsAreInSeries(firstNetworkConnection, secondNetworkConnection);

				if (connectionsAreParallel) {
					Optimization optimization =
							new Optimization(firstNetworkConnection, secondNetworkConnection, OptimizationType.PARALLEL);
					possibleIntermediateOptimizations.add(optimization);
				} else if (connectionsAreInSeries) {
					Optimization optimization =
							new Optimization(firstNetworkConnection, secondNetworkConnection, OptimizationType.IN_SERIES);
					possibleIntermediateOptimizations.add(optimization);
				}
			}
		}

		// If there are some optimizations on on internal connections apply the best of them
		if (possibleIntermediateOptimizations.size() > 0) {
			applyBestOptimization(possibleIntermediateOptimizations);
			return true;
		} else {
			return false;
		}
	}

	private boolean applySomeConnectionsOptimization() {
		List<Optimization> allPossibleOptimizations = new LinkedList<Optimization>();

		for (NetworkConnection firstNetworkConnection : tempConnections) {
			for (NetworkConnection secondNetworkConnection : tempConnections) {
				boolean connectionsAreParallel = SolversUtils.
						connectionsAreParallel(firstNetworkConnection, secondNetworkConnection);
				boolean connectionsAreInSeries = SolversUtils.
						connectionsAreInSeries(firstNetworkConnection, secondNetworkConnection);

				boolean firstConnectionIsBoundary =
						SolversUtils.isConnectionBoundary(firstNetworkConnection, task.getStartNodeName(), task.getEndNodeName());
				boolean secondConnectionIsBoundary =
						SolversUtils.isConnectionBoundary(secondNetworkConnection, task.getStartNodeName(), task.getEndNodeName());
				boolean bothConnectionsAreComplete =
						SolversUtils.isConnectionComplete(firstNetworkConnection, task.getStartNodeName(), task.getEndNodeName());
				boolean connectionsCanBeOptimizedParallel =
						connectionsAreParallel && ((!firstConnectionIsBoundary && !secondConnectionIsBoundary)
						|| bothConnectionsAreComplete);

				boolean commonConnectionIsBoundary =
						SolversUtils.getCommonNodes(firstNetworkConnection, secondNetworkConnection).contains(task.getStartNodeName())
						|| SolversUtils.getCommonNodes(firstNetworkConnection, secondNetworkConnection).contains(task.getEndNodeName());
				boolean connectionsCanBeOptimizedInSeries =
						connectionsAreInSeries && !commonConnectionIsBoundary;

				if (connectionsCanBeOptimizedParallel) {
					Optimization optimization =
							new Optimization(firstNetworkConnection, secondNetworkConnection, OptimizationType.PARALLEL);
					allPossibleOptimizations.add(optimization);
				} else if (connectionsCanBeOptimizedInSeries) {
					Optimization optimization =
							new Optimization(firstNetworkConnection, secondNetworkConnection, OptimizationType.IN_SERIES);
					allPossibleOptimizations.add(optimization);
				}
			}
		}
		if (allPossibleOptimizations.size() > 0) {
			applyBestOptimization(allPossibleOptimizations);
			return true;
		} else {
			return false;
		}
	}

	private void applyBestOptimization(List<Optimization> possibleOptimizations) {
		Optimization bestOptimization = findBestOptimization(possibleOptimizations);
		applyOptimization(tempConnections, bestOptimization);
	}

	private Optimization findBestOptimization(List<Optimization> optimizations) {
		return Collections.max(optimizations);
	}

	private void applyOptimization(List<NetworkConnection> connections, Optimization optimization) {
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
