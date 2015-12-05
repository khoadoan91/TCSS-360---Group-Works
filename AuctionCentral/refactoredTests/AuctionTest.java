package refactoredTests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import refactored.Auction;
import refactored.Item;

public class AuctionTest {
	
	private Auction myAuction;
	private Item myItem1;
	private Item myItem2;
	private Calendar myAuctionStartDate;
	private Calendar myAuctionEndDate;

	@Before
	public void setUp() throws Exception {
		List<Item> list = new ArrayList<>();
		myItem1 = new Item("iPad", 1, new BigDecimal("300.00"), "4th generation");
		myItem2 = new Item("Macbook", 1, new BigDecimal("1000.00"), "Used");
		list.add(myItem1);
		list.add(myItem2);
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2015, 11, 25, 8, 30);
		myAuction = new Auction("GoodWill", list, cal, "2:10");
		myAuctionStartDate = myAuction.getDateAuctionStarts();
		myAuctionEndDate = myAuction.getDateAuctionEnds();
	}

	@Test
	public void testAuctionStringListOfItemCalendarString() {
		fail("Not yet implemented");
	}

	@Test
	public void testAuctionStringListOfItemStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testMakeTestAuction() {
		fail("Not yet implemented");
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

	@Test
	public void testGetAuctionName() {
		assertEquals(myAuction.getAuctionName(), "GoodWill-Nov-25-2015");
	}

	@Test
	public void testGetDateAuctionStartsAtYear() {
		assertEquals(myAuctionStartDate.get(Calendar.YEAR), 2015);
	}
	
	@Test
	public void testGetDateAuctionStartsAtMonth() {
		assertEquals(myAuctionStartDate.get(Calendar.MONTH), 10);  // November
	}
	
	@Test
	public void testGetDateAuctionStartsAtDayOfMonth() {
		assertEquals(myAuctionStartDate.get(Calendar.DAY_OF_MONTH), 25);
	}
	
	@Test
	public void testGetDateAuctionStartsAtHour() {
		assertEquals(myAuctionStartDate.get(Calendar.HOUR_OF_DAY), 10);
	}
	
	@Test
	public void testGetDateAuctionStartsAtMinute() {
		assertEquals(myAuctionStartDate.get(Calendar.MINUTE), 40);
	}

	@Test
	public void testGetDateAuctionEndsAtYear() {
		assertEquals(myAuctionEndDate.get(Calendar.YEAR), 2015);
	}
	
	@Test
	public void testGetDateAuctionEndsAtMonth() {
		assertEquals(myAuctionEndDate.get(Calendar.MONTH), 10);  // November
	}
	
	@Test
	public void testGetDateAuctionEndsAtDayOfMonth() {
		assertEquals(myAuctionEndDate.get(Calendar.DAY_OF_MONTH), 25);
	}
	
	@Test
	public void testGetDateAuctionEndsAtHour() {
		assertEquals(myAuctionEndDate.get(Calendar.HOUR_OF_DAY), 10);
	}
	
	@Test
	public void testGetDateAuctionEndsAtMinute() {
		assertEquals(myAuctionEndDate.get(Calendar.MINUTE), 40);
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
	public void testRemoveBid() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewBids() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllItems() {
		List<Item> expectedItems = new ArrayList<>();
		expectedItems.add(myItem1);
		expectedItems.add(myItem2);
		assertEquals(expectedItems, myAuction.getAllItems());
	}

	@Test
	public void testGetOrganizationName() {
		assertEquals(myAuction.getOrganizationName(), "Goodwill");
	}

	@Test
	public void testSetDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetMonth() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetYear() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testDisplayAuction() {
		fail("Not yet implemented");
	}

	@Test
	public void testToStringTextFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testCompareTo() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

}
