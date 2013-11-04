/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.networker.algorithms;

import com.epam.networker.entities.NetworkConnection;
import com.epam.networker.entities.NetworkOptimizationTask;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Iaroslav_Mazai
 */
public class NaiveNetworkOptimizationTaskSolverTest {

	NetworkConnection[] connectionsForSmallTaskArray = {
		new NetworkConnection('a', 'e', 2),
		new NetworkConnection('e', 'b', 2),
		new NetworkConnection('a', 'c', 0),
		new NetworkConnection('c', 'd', 8),
		new NetworkConnection('c', 'd', 8),
		new NetworkConnection('d', 'b', 0)
	};
	List<NetworkConnection> connectionsForSmallTask =
			new LinkedList<NetworkConnection>(Arrays.asList(connectionsForSmallTaskArray));
	NetworkOptimizationTask smallTask = new NetworkOptimizationTask('a', 'b', connectionsForSmallTask);

	/**
	 * Checks if naiveOptimizationTaskSolver return right result on small task
	 *
	 */
	@Test
	public void testSolveWithSmallTask() {
		NetworkOptimizationTaskSolver solver = new NaiveNetworkOptimizationTaskSolver();
		List<NetworkConnection> expResult = Arrays.asList(new NetworkConnection[]{new NetworkConnection('a', 'b', 2)});
		List<NetworkConnection> actualResult = solver.solve(smallTask);
		assertEquals(expResult, actualResult);
	}
}
