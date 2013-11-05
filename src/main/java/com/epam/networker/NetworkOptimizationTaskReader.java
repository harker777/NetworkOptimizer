/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.networker;

import com.epam.networker.entities.NetworkConnection;
import com.epam.networker.entities.NetworkOptimizationTask;
import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * @author Iaroslav_Mazai
 */
public class NetworkOptimizationTaskReader {

	public static NetworkOptimizationTask readOptimizationTask(InputStream stream) {
		NetworkOptimizationTask result = new NetworkOptimizationTask();
		Scanner scanner = new Scanner(stream);

		int connectionsNumber = scanner.nextInt();
		result.setStartNodeName(scanner.next().charAt(0));
		result.setEndNodeName(scanner.next().charAt(0));
		for (int i = 0; i < connectionsNumber; i++) {
			result.getConnections().add(readConnection(scanner));
		}

		return result;
	}

	private static NetworkConnection readConnection(Scanner scanner) {
		NetworkConnection result = new NetworkConnection();

		result.setStartNodeName(scanner.next().charAt(0));
		result.setEndNodeName(scanner.next().charAt(0));
		result.setDelay(scanner.nextInt());

		return result;
	}
}
