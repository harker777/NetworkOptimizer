package com.epam.networker.entities;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a network optimization task. It stores collection of Network connections
 * and start-end nodes names.
 *
 * @author Iaroslav_Mazai
 */
public class NetworkOptimizationTask {

	private char startNodeName;
	private char endNodeName;
	private List<NetworkConnection> connections;

	public NetworkOptimizationTask() {
		connections = new LinkedList<NetworkConnection>();
	}

	/**
	 * Initializes object with specified start-end nodes names and connections list
	 *
	 * @param startNodeName
	 * @param endNodeName
	 * @param connections
	 */
	public NetworkOptimizationTask(char startNodeName, char endNodeName, List<NetworkConnection> connections) {
		this.startNodeName = startNodeName;
		this.endNodeName = endNodeName;
		this.connections = connections;
	}

	public char getStartNodeName() {
		return startNodeName;
	}

	public void setStartNodeName(char startNodeName) {
		this.startNodeName = startNodeName;
	}

	public char getEndNodeName() {
		return endNodeName;
	}

	public void setEndNodeName(char endNodeName) {
		this.endNodeName = endNodeName;
	}

	public List<NetworkConnection> getConnections() {
		return connections;
	}

	public void setConnections(
			List<NetworkConnection> connections) {
		this.connections = connections;
	}
}
