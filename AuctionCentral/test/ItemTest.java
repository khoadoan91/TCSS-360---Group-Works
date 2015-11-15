/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import model.Item;

import org.junit.Before;
import org.junit.Test;

/**
 * @author KyleD
 *
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
	 * Test method for {@link model.Item#getTitle()}.
	 */
	@Test
	public void testGetTitle() {
		assertEquals(myItem1.getTitle(), "iPad");
		assertEquals(myItem2.getTitle(), "Nexus 6");
	}

	/**
	 * Test method for {@link model.Item#getQuantity()}.
	 */
	@Test
	public void testGetQuantity() {
		assertEquals(myItem1.getQuantity(), 5);
		assertEquals(myItem2.getQuantity(), 1);
	}

	/**
	 * Test method for {@link model.Item#getDescription()}.
	 */
	@Test
	public void testGetDescription() {
		assertEquals(myItem1.getDescription(), "Used, in good condition");
		assertEquals(myItem2.getDescription(), "Used, broken screen");
	}

	/**
	 * Test method for {@link model.Item#setTitle(java.lang.String)}.
	 */
	@Test
	public void testSetTitle() {
		myItem1.setTitle("iPod");
		assertEquals(myItem1.getTitle(), "iPod");
	}

	/**
	 * Test method for {@link model.Item#setQuantity(int)}.
	 */
	@Test
	public void testSetQuantity() {
		myItem2.setQuantity(3);
		assertEquals(myItem2.getQuantity(), 3);
	}

	/**
	 * Test method for {@link model.Item#setDescription(java.lang.String)}.
	 */
	@Test
	public void testSetDescription() {
		myItem2.setDescription("New");
		assertEquals(myItem2.getDescription(), "New");
	}
}
