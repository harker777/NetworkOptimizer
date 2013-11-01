package com.epam.networker.entities;

/**
 * This class represents a connection between two nodes. It stores their names (as characters) and
 * delay to transfer data between them.
 *
 * @author Iaroslav_Mazai
 */
public class NetworkConnection {

	private char startNodeName;
	private char endNodeName;
	private double delay;

	/**
	 * Creates a Network Connection instance with specified nodes names and delay
	 *
	 * @param startNodeName
	 * @param endNodeName
	 * @param delay
	 */
	public NetworkConnection(char startNodeName, char endNodeName, double delay) {
		this.startNodeName = startNodeName;
		this.endNodeName = endNodeName;
		if (delay < 0) {
			this.delay = 0;
		} else {
			this.delay = delay;
		}
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

	public double getDelay() {
		return delay;
	}

	public void setDelay(float delay) {
		this.delay = delay;
	}

	public boolean isHyperConductive() {
		return getDelay() == 0;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 71 * hash + this.startNodeName;
		hash = 71 * hash + this.endNodeName;
		hash = 71 * hash + Double.valueOf(delay).hashCode();
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final NetworkConnection other = (NetworkConnection) obj;
		double eps = 0.0001;
		if (this.startNodeName != other.startNodeName) {
			return false;
		}
		if (this.endNodeName != other.endNodeName) {
			return false;
		}
		if (Math.abs(this.delay - other.delay) > eps) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "NetworkConnection{" + "startNodeName=" + startNodeName + ", endNodeName=" + endNodeName + ", delay=" + delay + '}';
	}
}
