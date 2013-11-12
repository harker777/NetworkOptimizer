/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.networker.algorithms;

import com.epam.networker.entities.NetworkConnection;
import com.epam.networker.entities.NetworkOptimizationTask;
import com.epam.networker.entities.Optimization;
import com.epam.networker.entities.OptimizationType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author harker777
 */
public class SimpleNetworkOptimizationTaskSolver implements NetworkOptimizationTaskSolver {

	private List<NetworkConnection> tempConnections;

	@Override
	public List<NetworkConnection> solve(NetworkOptimizationTask taskToSolve) {
		tempConnections = new ArrayList<NetworkConnection>(taskToSolve.getConnections());

		while (true) {
			List<Optimization> inSeriesOptimizations = new ArrayList<Optimization>();
			Optimization parallelOptimization = null;

			for (NetworkConnection firstConnection : tempConnections) {
				for (NetworkConnection secondConnection : tempConnections) {
					if (SolversUtils.connectionsAreParallel(firstConnection, secondConnection)) {
						parallelOptimization = new Optimization(firstConnection, secondConnection, OptimizationType.PARALLEL);
						break;
					} else {
						if (SolversUtils.connectionsAreInSeries(firstConnection, secondConnection)) {
							Optimization optimization =
										 new Optimization(firstConnection, secondConnection, OptimizationType.IN_SERIES);
							inSeriesOptimizations.add(optimization);
						}
					}
				}
				// If some parallel optimization was found
				if (parallelOptimization != null) {
					SolversUtils.applyOptimization(tempConnections, parallelOptimization);
					break;
				}
			}

			Boolean someInSeriesOptimizationWasApplied = false;
			if (parallelOptimization == null) {
				someInSeriesOptimizationWasApplied = tryToApplySomeInSeriesOptimization(inSeriesOptimizations, taskToSolve);
			}

			if (parallelOptimization == null && !someInSeriesOptimizationWasApplied) {
				break;
			}
		}

		return tempConnections;
	}

	private Boolean tryToApplySomeInSeriesOptimization(List<Optimization> optimizations,
													   NetworkOptimizationTask taskToSolve) {
		for (Optimization optimization : optimizations) {
			if (isInSeriesOptimizationApplieable(
					tempConnections, optimization, taskToSolve.getStartNodeName(), taskToSolve.getEndNodeName())) {
				SolversUtils.applyOptimization(tempConnections, optimization);
				return true;
			}
		}
		return false;
	}

	private Boolean isInSeriesOptimizationApplieable(
			List<NetworkConnection> connections, Optimization optimization, Character startNodeName,
			Character endNodeName) {
		List<NetworkConnection> tempList = new ArrayList<NetworkConnection>(connections);
		SolversUtils.applyOptimization(tempList, optimization);
		return new BreadthFirstSearch().pathExists(tempList, startNodeName, endNodeName);
	}
}
