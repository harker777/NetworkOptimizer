package com.epam.networker.algorithms;

import com.epam.networker.entities.NetworkConnection;
import java.util.List;

/**
 * This class is responsible for making optimizations of connections. His methods, given two connections will return one
 * connections, which is equivalent to them.
 *
 * @author harker777
 */
public class ConnectionsOptimizer {

	/**
	 * Optimizes connections which are in series - for example (a,b,2), (b,c,4) -> (a,c,6)
	 *
	 * @param firstConnection
	 * @param secondConnection
	 * @return
	 */
	public static NetworkConnection optimizeInSeriesConnections(
			NetworkConnection firstConnection, NetworkConnection secondConnection) {
		int newConnectionDelay = firstConnection.getDelay() + secondConnection.getDelay();
		char newConnectionFirstNodeName = 'a';
		char newConnectionSecondNodeName = 'a';

		if (firstConnection.getStartNodeName() == secondConnection.getStartNodeName()) {
			newConnectionFirstNodeName = firstConnection.getEndNodeName();
			newConnectionSecondNodeName = secondConnection.getEndNodeName();
		}
		if (firstConnection.getStartNodeName() == secondConnection.getEndNodeName()) {
			newConnectionFirstNodeName = firstConnection.getEndNodeName();
			newConnectionSecondNodeName = secondConnection.getStartNodeName();
		}
		if (firstConnection.getEndNodeName() == secondConnection.getStartNodeName()) {
			newConnectionFirstNodeName = firstConnection.getStartNodeName();
			newConnectionSecondNodeName = secondConnection.getEndNodeName();
		}
		if (firstConnection.getEndNodeName() == secondConnection.getEndNodeName()) {
			newConnectionFirstNodeName = firstConnection.getStartNodeName();
			newConnectionSecondNodeName = secondConnection.getStartNodeName();
		}

		return new NetworkConnection(newConnectionFirstNodeName, newConnectionSecondNodeName, newConnectionDelay);
	}

	/**
	 * Optimizes connections, which are parallel - for example (a,b,4), (b,a,4) -> (a,b,2)
	 *
	 * @param firstConnection
	 * @param secondConnection
	 * @return
	 */
	public static NetworkConnection optimizeParallelConnections(
			NetworkConnection firstConnection, NetworkConnection secondConnection) {
		char newConnectionFirstNodeName = firstConnection.getStartNodeName();
		char newConnectionSecondNodeName = firstConnection.getEndNodeName();
		float newConnectionDelay;
		if (firstConnection.isHyperConductive() && secondConnection.isHyperConductive()) {
			newConnectionDelay = 0;
		} else if (firstConnection.isHyperConductive() && !secondConnection.isHyperConductive()) {
			newConnectionDelay = secondConnection.getDelay() / 2;
		} else if (!firstConnection.isHyperConductive() && secondConnection.isHyperConductive()) {
			newConnectionDelay = firstConnection.getDelay() / 2;
		} else {
			newConnectionDelay = (firstConnection.getDelay() * secondConnection.getDelay())
								 / (firstConnection.getDelay() + secondConnection.getDelay());
		}
		return new NetworkConnection(newConnectionFirstNodeName, newConnectionSecondNodeName, (int) newConnectionDelay);
	}
}
