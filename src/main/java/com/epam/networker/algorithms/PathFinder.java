/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.networker.algorithms;

import com.epam.networker.entities.NetworkConnection;
import java.util.List;

/**
 *
 * @author harker777
 */
public interface PathFinder {

	public Boolean pathExists(List<NetworkConnection> connections, Character startNode, Character endNode);
}
