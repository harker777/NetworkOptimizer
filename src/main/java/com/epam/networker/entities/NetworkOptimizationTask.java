package com.epam.networker.entities;

import java.util.List;

/**
 *
 * @author Iaroslav_Mazai
 */
public class NetworkOptimizationTask {

	private char startNodeName;
	private char endNodeName;
	private List<NetworkConnection> connections;

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
