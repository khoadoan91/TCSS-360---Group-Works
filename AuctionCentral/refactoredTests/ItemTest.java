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
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetDescriptionWithEmptyString() {
		myItem2.setDescription("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetQuantityWithZero() {
		myItem2.setQuantity(0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetQuantityWithNegativeNumber() {
		myItem2.setQuantity(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetTitleWithEmptyString() {
		myItem2.setTitle("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetStartingPriceWithNegativeNumber() {
		myItem2.setStartingPrice(new BigDecimal("-100.00"));
	}
	
	@Test(expected = NullPointerException.class)
	public void testSetTitleWithNull() {
		myItem2.setTitle(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSetDescriptionWithNull() {
		myItem2.setDescription(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSetStartingPriceWithNull() {
		myItem2.setStartingPrice(null);
	}

	/**
	 * Test method for {@link refactored.Item#setTitle(java.lang.String)}.
	 */
	@Test
	public void testSetTitle() {
		String title = "iPad 4th Generation";
		myItem1.setTitle(title);
		assertEquals(myItem1.getTitle(), title);
	}

	/**
	 * Test method for {@link refactored.Item#setQuantity(int)}.
	 */
	@Test
	public void testSetQuantity() {
		int quantity = 10;
		myItem1.setQuantity(quantity);
		assertEquals(myItem1.getQuantity(), quantity);
	}

	/**
	 * Test method for {@link refactored.Item#setStartingPrice(java.math.BigDecimal)}.
	 */
	@Test
	public void testSetStartingPrice() {
		BigDecimal price = new BigDecimal("33999.00");
		myItem1.setStartingPrice(price);
		assertEquals(myItem1.getStartingPrice(), price);
	}

	/**
	 * Test method for {@link refactored.Item#setDescription(java.lang.String)}.
	 */
	@Test
	public void testSetDescription() {
		String desc = "Like New";
		myItem1.setDescription(desc);
		assertEquals(myItem1.getDescription(), desc);
	}

	/**
	 * Test method for {@link refactored.Item#toString()}.
	 */
	@Test
	public void testToString() {
		assertEquals("iPad\nQuantity: 5\nStarting Price: $339.99\nDescription: "
				+ "Used, in good condition", myItem1.toString());
	}

	/**
	 * Test method for {@link refactored.Item#toStringTextFile()}.
	 */
	@Test
	public void testToStringTextFile() {
		assertEquals("iPad, 5, 339.99, Used, in good condition", myItem1.toStringTextFile());
	}

	/**
	 * Test method for {@link refactored.Item#compareTo(refactored.Item)}.
	 */
	@Test
	public void testCompareToOnSameTitle() {
		assertTrue(myItem1.compareTo(new Item("ipad", 1, new BigDecimal("200.00"), "bla bla")) == 0);
	}
	
	public void testCompareToOnNotSameTitle() {
		assertFalse(myItem1.compareTo(myItem2) != 0);
	}
}
