package com.epam.networker.algorithms;

import com.epam.networker.entities.NetworkConnection;
import com.epam.networker.entities.NetworkOptimizationTask;
import com.epam.networker.entities.Optimization;
import com.epam.networker.entities.OptimizationType;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * This class implements naive implementation of task solver by applying best (by rank)
 * optimizations
 *
 * @author Iaroslav_Mazai
 */
public class NaiveNetworkOptimizationTaskSolver implements NetworkOptimizationTaskSolver {

	private List<NetworkConnection> tempConnections;
	private NetworkOptimizationTask task;

	/**
	 * Solves the given NetworkOptimizationTask using naive algorithm. On every step it tries to
	 * optimize some internal connections. If there are no such optimization it tries to apply any
	 * optimization. Optimizations with higher rank are applied earlier.
	 *
	 * @param taskToSolve
	 * @return
	 */
	@Override
	public List<NetworkConnection> solve(NetworkOptimizationTask taskToSolve) {
		this.tempConnections = new LinkedList<NetworkConnection>(taskToSolve.getConnections());
		this.task = taskToSolve;

		while (true) {
			// First, try to optimize internal connections
			boolean someOptimizationSucceded = false;
			boolean internalOptimizationSucceded = applySomeInternalConnectionsOptimization();

			// If failed, try to optimize all the connections
			if (!internalOptimizationSucceded) {
				someOptimizationSucceded = applySomeConnectionsOptimization();
			}
			// If no optimizations found at all - that's the end
			if (!internalOptimizationSucceded && !someOptimizationSucceded) {
				break;
			}
		}

		return this.tempConnections;
	}

	/**
	 * Finds all internal connections optimizations and tries to optimize the best one (based on
	 * rank). If no optimizations are available returns false, else applies the best one and returns
	 * true.
	 *
	 * @return
	 */
	private boolean applySomeInternalConnectionsOptimization() {
		List<NetworkConnection> intermediateConnections = SolversUtils.getIntermediateConnections(
				tempConnections, task.getStartNodeName(), task.getEndNodeName());
		List<Optimization> possibleIntermediateOptimizations = new LinkedList<Optimization>();

		// Find all possible internal optimizations and add them to possibleIntermediateOptimizations list
		for (NetworkConnection firstNetworkConnection : intermediateConnections) {
			for (NetworkConnection secondNetworkConnection : intermediateConnections) {
				boolean connectionsAreParallel = SolversUtils.
						connectionsAreParallel(firstNetworkConnection, secondNetworkConnection);
				boolean connectionsAreInSeries = SolversUtils.
						connectionsAreInSeries(firstNetworkConnection, secondNetworkConnection);

				if (connectionsAreParallel) {
					Optimization optimization = new Optimization(
							firstNetworkConnection, secondNetworkConnection, OptimizationType.PARALLEL);
					possibleIntermediateOptimizations.add(optimization);
				} else if (connectionsAreInSeries) {
					Optimization optimization = new Optimization(
							firstNetworkConnection, secondNetworkConnection, OptimizationType.IN_SERIES);
					possibleIntermediateOptimizations.add(optimization);
				}
			}
		}

		// If there are some optimizations on internal connections - apply the best one and return true
		if (possibleIntermediateOptimizations.size() > 0) {
			applyBestOptimization(possibleIntermediateOptimizations);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Finds all connections optimizations and tries to optimize the best one. If no optimizations
	 * are available returns false, else applies the best one and returns true.
	 *
	 * @return
	 */
	private boolean applySomeConnectionsOptimization() {
		List<Optimization> allPossibleOptimizations = new LinkedList<Optimization>();

		// Find all possible optimizations and add them to allPossibleOptimizations list
		for (NetworkConnection firstNetworkConnection : tempConnections) {
			for (NetworkConnection secondNetworkConnection : tempConnections) {
				boolean connectionsAreParallel = SolversUtils.
						connectionsAreParallel(firstNetworkConnection, secondNetworkConnection);
				boolean connectionsAreInSeries = SolversUtils.
						connectionsAreInSeries(firstNetworkConnection, secondNetworkConnection);

				boolean firstConnectionIsBoundary = SolversUtils.isConnectionBoundary(
						firstNetworkConnection, task.getStartNodeName(), task.getEndNodeName());
				boolean secondConnectionIsBoundary = SolversUtils.isConnectionBoundary(
						secondNetworkConnection, task.getStartNodeName(), task.getEndNodeName());
				boolean bothConnectionsAreComplete = SolversUtils.isConnectionComplete(
						firstNetworkConnection, task.getStartNodeName(), task.getEndNodeName());

				boolean connectionsCanBeOptimizedParallel = connectionsAreParallel
						&& ((!firstConnectionIsBoundary && !secondConnectionIsBoundary) || bothConnectionsAreComplete);

				boolean commonNodeIsBoundary =
						SolversUtils.getCommonNodes(firstNetworkConnection, secondNetworkConnection).contains(task.getStartNodeName())
						|| SolversUtils.getCommonNodes(firstNetworkConnection, secondNetworkConnection).contains(task.getEndNodeName());
				boolean connectionsCanBeOptimizedInSeries = connectionsAreInSeries && !commonNodeIsBoundary;

				if (connectionsCanBeOptimizedParallel) {
					Optimization optimization = new Optimization(firstNetworkConnection, secondNetworkConnection, OptimizationType.PARALLEL);
					allPossibleOptimizations.add(optimization);
				} else if (connectionsCanBeOptimizedInSeries) {
					Optimization optimization = new Optimization(firstNetworkConnection, secondNetworkConnection, OptimizationType.IN_SERIES);
					allPossibleOptimizations.add(optimization);
				}
			}
		}

		// If there are some optimizations - apply the best one and return true
		if (allPossibleOptimizations.size() > 0) {
			applyBestOptimization(allPossibleOptimizations);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Applies the best optimization on internal temporary connections list. Based on
	 * finBestOptimization method
	 *
	 * @param possibleOptimizations
	 */
	private void applyBestOptimization(List<Optimization> possibleOptimizations) {
		Optimization bestOptimization = findBestOptimization(possibleOptimizations);
		SolversUtils.applyOptimization(tempConnections, bestOptimization);
	}

	/**
	 * Return the best optimization (based on their rank)
	 *
	 * @param optimizations
	 * @return
	 */
	private Optimization findBestOptimization(List<Optimization> optimizations) {
		return Collections.max(optimizations);
	}
}
