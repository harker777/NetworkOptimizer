/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.networker.entities;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Iaroslav_Mazai
 */
public class NetworkConnectionTest {

	@Test
	public void testConstructorWithGoodDelay() {
		int delay = 5;
		NetworkConnection instance = new NetworkConnection('a', 'b', delay);
		int expResult = delay;
		int actualResult = instance.getDelay();
		assertEquals(expResult, actualResult);
	}

	@Test
	public void testConstructorWithBadDelay() {
		int delay = -5;
		NetworkConnection instance = new NetworkConnection('a', 'b', delay);
		int expResult = 0;
		int actualResult = instance.getDelay();
		assertEquals(expResult, actualResult);
	}

	@Test
	public void testIsHyperConductiveReturnsTrueOnZeroDelay() {
		int delay = 0;
		NetworkConnection instance = new NetworkConnection('a', 'b', delay);
		boolean expResult = true;
		boolean actualResult = instance.isHyperConductive();
		assertEquals(expResult, actualResult);
	}

	@Test
	public void testIsHyperConductiveReturnsFalseOnPositiveDelay() {
		int delay = 4;
		NetworkConnection instance = new NetworkConnection('a', 'b', delay);
		boolean expResult = false;
		boolean actualResult = instance.isHyperConductive();
		assertEquals(expResult, actualResult);
	}
}
