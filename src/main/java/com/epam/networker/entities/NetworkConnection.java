package com.epam.networker.entities;

/**
 * This class represents a connection between two nodes. It stores two nodes names (as characters)
 * and delay to transfer data between them.
 *
 * @author Iaroslav_Mazai
 */
public class NetworkConnection {

	private char startNodeName;
	private char endNodeName;
	private int delay;

	/**
	 * Creates a Network Connection instance with specified nodes names and delay
	 *
	 * @param startNodeName
	 * @param endNodeName
	 * @param delay
	 */
	public NetworkConnection(char startNodeName, char endNodeName, int delay) {
		this.startNodeName = startNodeName;
		this.endNodeName = endNodeName;
		this.delay = delay;
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

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}
}
