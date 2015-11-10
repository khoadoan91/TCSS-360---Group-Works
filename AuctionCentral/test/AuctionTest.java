/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.Auction;
import model.Item;

import org.junit.Before;
import org.junit.Test;

/**
 * A Test for an Auction class.
 * 
 * @author KyleD
 */
public class AuctionTest {
	
	private Auction myAuction;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		List<Item> list = new ArrayList<>();
		Item item1 = new Item("iPad", 1, "New");
		Item item2 = new Item("Macbook", 1, "Used");
		list.add(item1); 
		list.add(item2);
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2015, 11, 25, 8, 30);
		myAuction = new Auction(list, cal);
	}

	/**
	 * Test method for {@link model.Auction#addItem(model.Item)}.
	 */
	@Test(expected = NullPointerException.class)
	public void testAddItemWithNull() {
		myAuction.addItem(null);
	}
	
	@Test
	public void testAddItemNotContainInList() {
		assertTrue(myAuction.addItem(new Item("Nexus", 1, "Used")));
	}
	
	@Test
	public void testAddItemContainedInList() {
		assertFalse(myAuction.addItem(new Item("iPad", 2, "Used")));
	}

	/**
	 * Test method for {@link model.Auction#removeItem(model.Item)}.
	 */
	@Test(expected = NullPointerException.class)
	public void testRemoveItemWithNull() {
		myAuction.removeItem(null);
	}
	
	@Test
	public void testRemoveItem() {
		Item item = new Item("iPad", 1, "NEW");
		myAuction.removeItem(item);
		assertFalse(myAuction.getAllItems().contains(item));
	}

	/**
	 * Test method for {@link model.Auction#getItem(model.Item)}.
	 */
	@Test(expected = NullPointerException.class)
	public void testGetItemWithNull() {
		myAuction.getItem(null);
	}
	
	@Test
	public void testGetItem() {
		Item item = new Item("iPAD", 1, "NEW");
		assertTrue(myAuction.getItem(item).equals(item));
	}

}
