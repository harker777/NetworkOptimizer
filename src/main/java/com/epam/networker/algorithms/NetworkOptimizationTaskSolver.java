/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.networker.algorithms;

import com.epam.networker.entities.NetworkConnection;
import com.epam.networker.entities.NetworkOptimizationTask;
import java.util.List;

/**
 * This interface represents a strategy to solve network optimization task.
 *
 * @author Iaroslav_Mazai
 */
public interface NetworkOptimizationTaskSolver {

	/**
	 * Solves the given network optimization task.
	 *
	 * @param taskToSolve
	 * @return
	 */
	public List<NetworkConnection> solve(NetworkOptimizationTask taskToSolve);
}
