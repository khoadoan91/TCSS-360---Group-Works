package refactoredTests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import refactored.Item;

/**
 * @author KyleD
 */
public class ItemTest {

	private Item myItem1;
	private Item myItem2;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		myItem1 = new Item("iPad", 5, new BigDecimal("339.99"), "Used, in good condition");
		myItem2 = new Item("Nexus 6", 1, new BigDecimal("450.00"), "Used, broken screen");
	}

	/**
	 * Test method for {@link refactored.Item#setTitle(java.lang.String)}.
	 */
	@Test
	public void testSetTitle() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link refactored.Item#setQuantity(int)}.
	 */
	@Test
	public void testSetQuantity() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link refactored.Item#setStartingPrice(java.math.BigDecimal)}.
	 */
	@Test
	public void testSetStartingPrice() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link refactored.Item#setDescription(java.lang.String)}.
	 */
	@Test
	public void testSetDescription() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link refactored.Item#toString()}.
	 */
	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link refactored.Item#toStringTextFile()}.
	 */
	@Test
	public void testToStringTextFile() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link refactored.Item#compareTo(refactored.Item)}.
	 */
	@Test
	public void testCompareTo() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link refactored.Item#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

}
