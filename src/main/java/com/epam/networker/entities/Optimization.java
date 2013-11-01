/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.networker.entities;

/**
 *
 * @author Iaroslav_Mazai
 */
public class Optimization implements Comparable<Optimization> {

	private NetworkConnection firstConnection;
	private NetworkConnection secondConnection;
	private OptimizationType type;

	public Optimization(NetworkConnection firstConnection, NetworkConnection secondConnection, OptimizationType type) {
		this.firstConnection = firstConnection;
		this.secondConnection = secondConnection;
		this.type = type;
	}

	public NetworkConnection getFirstConnection() {
		return firstConnection;
	}

	public void setFirstConnection(NetworkConnection firstConnection) {
		this.firstConnection = firstConnection;
	}

	public NetworkConnection getSecondConnection() {
		return secondConnection;
	}

	public void setSecondConnection(NetworkConnection secondConnection) {
		this.secondConnection = secondConnection;
	}

	public OptimizationType getType() {
		return type;
	}

	public void setType(OptimizationType type) {
		this.type = type;
	}

	public boolean isParallelWithOneHyperConductive() {
		boolean isParallel = (this.type == OptimizationType.PARALLEL);
		boolean onlyFirstIsHyperConductive = (firstConnection.isHyperConductive() && !secondConnection.isHyperConductive());
		boolean onlySecondIsHyperConductive = (firstConnection.isHyperConductive() && !secondConnection.isHyperConductive());
		return isParallel && (onlyFirstIsHyperConductive || onlySecondIsHyperConductive);
	}

	public boolean isParallelWithBothHyperConductive() {
		boolean isParallel = (this.type == OptimizationType.PARALLEL);
		boolean bothHyperConductive = (firstConnection.isHyperConductive() && secondConnection.isHyperConductive());
		return isParallel && bothHyperConductive;
	}

	public boolean isParallelWithoutHyperConductive() {
		boolean isParallel = (this.type == OptimizationType.PARALLEL);
		boolean bothNotHyperConductive = (!firstConnection.isHyperConductive() && !secondConnection.isHyperConductive());
		return isParallel && bothNotHyperConductive;
	}

	public boolean isInSeries() {
		return this.type == OptimizationType.IN_SERIES;
	}

	public int getRank() {
		if (this.isParallelWithOneHyperConductive()) {
			return 4;
		} else if (this.isParallelWithoutHyperConductive()) {
			return 3;
		} else if (this.isParallelWithBothHyperConductive()) {
			return 2;
		} else {
			return 1;
		}
	}

	public int compareTo(Optimization that) {
		return (this.getRank() - that.getRank());
	}
}
