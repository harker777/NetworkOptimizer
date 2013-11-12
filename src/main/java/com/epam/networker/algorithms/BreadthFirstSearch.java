/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.networker.algorithms;

import com.epam.networker.entities.NetworkConnection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * This class implements Breadth first search algorithm
 *
 * @author harker777
 */
public class BreadthFirstSearch implements PathFinder {

	private Map<Character, Set<Character>> nodes;
	private Set<Character> visitedNodes;
	private Queue<Character> nodesToCheck;

	/**
	 * This method returns true if there is some path from startNode to endNode and false otherwise
	 *
	 */
	@Override
	public Boolean pathExists(List<NetworkConnection> connections, Character startNode, Character endNode) {
		initVariables(startNode);
		initNodes(connections);

		while (!nodesToCheck.isEmpty()) {
			Character currentNode = nodesToCheck.poll();
			if (currentNode.equals(endNode)) {
				return true;
			}

			if (nodes.get(currentNode) == null) {
				return false;
			}
			for (Character descendant : nodes.get(currentNode)) {
				if (!visitedNodes.contains(descendant)) {
					nodesToCheck.add(descendant);
					visitedNodes.add(descendant);
				}
			}
		}

		return false;
	}

	private void initVariables(Character startNode) {
		nodes = new HashMap<Character, Set<Character>>();
		visitedNodes = new HashSet<Character>();
		nodesToCheck = new LinkedList<Character>();

		nodesToCheck.add(startNode);
		visitedNodes.add(startNode);
	}

	private void initNodes(List<NetworkConnection> networkConnections) {
		for (NetworkConnection networkConnection : networkConnections) {
			addConnectionToNodes(networkConnection);
		}
	}

	private void addConnectionToNodes(NetworkConnection networkConnection) {
		Character startNodeName = networkConnection.getStartNodeName();
		Character endNodeName = networkConnection.getEndNodeName();

		initNode(startNodeName);
		initNode(endNodeName);

		nodes.get(startNodeName).add(endNodeName);
		nodes.get(endNodeName).add(startNodeName);
	}

	private void initNode(Character nodeName) {
		if (!nodes.containsKey(nodeName)) {
			nodes.put(nodeName, new HashSet<Character>());
		}
	}
}
