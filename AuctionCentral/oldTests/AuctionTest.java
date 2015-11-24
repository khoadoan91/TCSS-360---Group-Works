/**
 * 
 */
package oldTests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import refactored.Auction;
import refactored.Item;

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
		Item item1 = new Item("iPad", 1, new BigDecimal("300.00"), "4th generation");
		Item item2 = new Item("Macbook", 1, new BigDecimal("1000.00"), "Used");
		list.add(item1);
		list.add(item2);
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2015, 11, 25, 8, 30);
		myAuction = new Auction("GoodWill", list, cal, "2:10");
	}

	/**
	 * Test method for {@link refactored.Auction#addItem(refactored.Item)}.
	 */
	@Test(expected = NullPointerException.class)
	public void testAddItemWithNull() {
		myAuction.addItem(null);
	}

	@Test
	public void testAddItemNotContainInList() {
		assertTrue(myAuction.addItem(new Item("Nexus", 1, new BigDecimal("540.00"), "Used")));
	}

	@Test
	public void testAddItemContainedInList() {
		assertFalse(myAuction.addItem(new Item("iPad", 2, new BigDecimal("250.00"), "Used")));
	}

	/**
	 * Test method for {@link refactored.Auction#removeItem(refactored.Item)}.
	 */
	@Test(expected = NullPointerException.class)
	public void testRemoveItemWithNull() {
		myAuction.removeItem(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveItemWithEmptyString() {
		myAuction.removeItem("");
	}

	@Test
	public void testRemoveItem() {
		assertTrue(myAuction.removeItem("iPad"));
		assertFalse(myAuction.removeItem("iPad"));
	}

	@Test
	public void testGetAuctionName() {
		assertEquals(myAuction.getAuctionName(), "GoodWill-Nov-25-2015");
	}

	@Test
	public void testGetDateAuctionStarts() {
		Calendar startDate = myAuction.getDateAuctionStarts();
		assertEquals(startDate.get(Calendar.YEAR), 2015);
		assertEquals(startDate.get(Calendar.MONTH), 10);  // November
		assertEquals(startDate.get(Calendar.DAY_OF_MONTH), 25);
		assertEquals(startDate.get(Calendar.HOUR_OF_DAY), 8);
		assertEquals(startDate.get(Calendar.MINUTE), 30);
	}

	@Test
	public void testGetDateAuctionEnds() {
		Calendar endDate = myAuction.getDateAuctionEnds();
		assertEquals(endDate.get(Calendar.YEAR), 2015);
		assertEquals(endDate.get(Calendar.MONTH), 10);  // November
		assertEquals(endDate.get(Calendar.DAY_OF_MONTH), 25);
		assertEquals(endDate.get(Calendar.HOUR_OF_DAY), 10);
		assertEquals(endDate.get(Calendar.MINUTE), 40);
	}

	@Test
	public void testSetStartingTime() {
		myAuction.setStartingTime("14:30");
		Calendar startDate = myAuction.getDateAuctionStarts();
		assertEquals(startDate.get(Calendar.YEAR), 2015);
		assertEquals(startDate.get(Calendar.MONTH), 10);  // November
		assertEquals(startDate.get(Calendar.DAY_OF_MONTH), 25);
		assertEquals(startDate.get(Calendar.HOUR_OF_DAY), 14);
		assertEquals(startDate.get(Calendar.MINUTE), 30);
	}
	
	@Test
	public void testSetTimeDuration() {
		myAuction.setTimeDuration("4:30");
		Calendar endTime = myAuction.getDateAuctionEnds();
		System.out.println(endTime.getTime());
		assertEquals(endTime.get(Calendar.YEAR), 2015);
		assertEquals(endTime.get(Calendar.MONTH), 10);  // November
		assertEquals(endTime.get(Calendar.DAY_OF_MONTH), 25);
		assertEquals(endTime.get(Calendar.HOUR_OF_DAY), 13); 
		assertEquals(endTime.get(Calendar.MINUTE), 00);
	}

	@Test
	public void testCompareTo() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetAllItems() {
//		List<Item> itemList = myAuction.getAllItems();
		fail("Not yet implemented");
	}
}
