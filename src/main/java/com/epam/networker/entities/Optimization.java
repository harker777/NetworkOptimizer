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

	/**
	 *
	 * @param firstConnection
	 * @param secondConnection
	 * @param type
	 */
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

	/**
	 * Checks if optimization is parallel and only one connection is hyperConductive
	 *
	 * @return
	 */
	public boolean isParallelWithOnlyOneHyperConductive() {
		boolean isParallel = (this.type == OptimizationType.PARALLEL);
		boolean onlyFirstIsHyperConductive = (firstConnection.isHyperConductive() && !secondConnection.isHyperConductive());
		boolean onlySecondIsHyperConductive = (firstConnection.isHyperConductive() && !secondConnection.isHyperConductive());
		return isParallel && (onlyFirstIsHyperConductive || onlySecondIsHyperConductive);
	}

	/**
	 * Checks if optimization is parallel and both connections are hyperConductive
	 *
	 * @return
	 */
	public boolean isParallelWithBothHyperConductive() {
		boolean isParallel = (this.type == OptimizationType.PARALLEL);
		boolean bothHyperConductive = (firstConnection.isHyperConductive() && secondConnection.isHyperConductive());
		return isParallel && bothHyperConductive;
	}

	/**
	 * Checks if optimization is parallel and both connections are not hyperConductive
	 *
	 * @return
	 */
	public boolean isParallelWithoutHyperConductive() {
		boolean isParallel = (this.type == OptimizationType.PARALLEL);
		boolean bothNotHyperConductive = (!firstConnection.isHyperConductive() && !secondConnection.isHyperConductive());
		return isParallel && bothNotHyperConductive;
	}

	/**
	 * Checks if optimization is inSeries
	 *
	 * @return
	 */
	public boolean isInSeries() {
		return this.type == OptimizationType.IN_SERIES;
	}

	/**
	 * Returns the rank of this optimization. 4 - it is parallel and only one connection is
	 * hyperConductive; 3 - it is parallel and no connections are hyperConductive; 2 - it is
	 * parallel and both connections are hyperConductive; 1 - it is inSeries optimization.
	 *
	 * @return
	 */
	public int getRank() {
		if (this.isParallelWithOnlyOneHyperConductive()) {
			return 4;
		} else if (this.isParallelWithoutHyperConductive()) {
			return 3;
		} else if (this.isParallelWithBothHyperConductive()) {
			return 2;
		} else {
			return 1;
		}
	}

	/**
	 * Compares to optimizations based on their rank.
	 *
	 * @param that
	 * @return
	 */
	@Override
	public int compareTo(Optimization that) {
		return (this.getRank() - that.getRank());
	}
}
