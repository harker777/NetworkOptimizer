package com.epam.networker;

import com.epam.networker.algorithms.NaiveNetworkOptimizationTaskSolver;
import com.epam.networker.entities.NetworkOptimizationTask;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) throws IOException {
		File newFile = new File("example");
		NetworkOptimizationTask task = NetworkOptimizationTaskReader.readOptimizationTask(new FileInputStream(newFile));
		System.out.println(task.getConnections());
		System.out.println(new NaiveNetworkOptimizationTaskSolver().solve(task));
	}
}
