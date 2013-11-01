package com.epam.networker.entities;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Iaroslav_Mazai
 */
public class NetworkConnectionTest {

	private double eps = 0.0001;

	/**
	 * Checks that constructor with positive delay passed works.
	 */
	@Test
	public void testConstructorWithGoodDelay() {
		double delay = 5;
		NetworkConnection instance = new NetworkConnection('a', 'b', delay);
		double expResult = delay;
		double actualResult = instance.getDelay();
		assertEquals(expResult, actualResult, eps);
	}

	/**
	 * Checks that constructor with negative delay passed sets delay to zero
	 */
	@Test
	public void testConstructorWithBadDelay() {
		double delay = -5;
		NetworkConnection instance = new NetworkConnection('a', 'b', delay);
		double expResult = 0;
		double actualResult = instance.getDelay();
		assertEquals(expResult, actualResult, eps);
	}

	/**
	 * Checks that isHyperConductive method returns true if the delay is zero
	 */
	@Test
	public void testIsHyperConductiveReturnsTrueOnZeroDelay() {
		double delay = 0;
		NetworkConnection instance = new NetworkConnection('a', 'b', delay);
		boolean expResult = true;
		boolean actualResult = instance.isHyperConductive();
		assertEquals(expResult, actualResult);
	}

	/**
	 * Checks that isHyperConductive method return false if the delay is greater than zero
	 */
	@Test
	public void testIsHyperConductiveReturnsFalseOnPositiveDelay() {
		double delay = 4;
		NetworkConnection instance = new NetworkConnection('a', 'b', delay);
		boolean expResult = false;
		boolean actualResult = instance.isHyperConductive();
		assertEquals(expResult, actualResult);
	}
}
