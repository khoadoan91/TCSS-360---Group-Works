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
//	private Bid myBid1;
//	private Bid myBid2;

	@Before
	public void setUp() throws Exception {
		List<Item> list = new ArrayList<>();
		myItem1 = new Item("iPad", 1, new BigDecimal("300.00"), "4th generation");
		myItem2 = new Item("Macbook", 1, new BigDecimal("1000.00"), "Used");
		list.add(myItem1);
		list.add(myItem2);
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2015, 12, 25, 8, 30);
		myAuction = new Auction("GoodWill", list, cal, "2:10");
		myAuctionStartDate = myAuction.getDateAuctionStarts();
		myAuctionEndDate = myAuction.getDateAuctionEnds();
		
//		myBid1 = new Bid(myItem1, 400.0, "Nina");
//		myBid1 = new Bid(myItem2, 1100.0, "Nina");
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
		assertEquals(myAuction.getAuctionName(), "GoodWill-Dec-25-2015");
	}

	@Test
	public void testGetDateAuctionStartsAtYear() {
		assertEquals(myAuctionStartDate.get(Calendar.YEAR), 2015);
	}
	
	@Test
	public void testGetDateAuctionStartsAtMonth() {
		assertEquals(myAuctionStartDate.get(Calendar.MONTH), 11);  // December
	}
	
	@Test
	public void testGetDateAuctionStartsAtDayOfMonth() {
		assertEquals(myAuctionStartDate.get(Calendar.DAY_OF_MONTH), 25);
	}
	
	@Test
	public void testGetDateAuctionStartsAtHour() {
		assertEquals(myAuctionStartDate.get(Calendar.HOUR_OF_DAY), 8);
	}
	
	@Test
	public void testGetDateAuctionStartsAtMinute() {
		assertEquals(myAuctionStartDate.get(Calendar.MINUTE), 30);
	}

	@Test
	public void testGetDateAuctionEndsAtYear() {
		assertEquals(myAuctionEndDate.get(Calendar.YEAR), 2015);
	}
	
	@Test
	public void testGetDateAuctionEndsAtMonth() {
		assertEquals(myAuctionEndDate.get(Calendar.MONTH), 11);  // December
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
	public void testSetStartingTimeAtYear() {
		myAuction.setStartingTime("14:30");
		Calendar startDate = myAuction.getDateAuctionStarts();
		assertEquals(startDate.get(Calendar.YEAR), 2015);
	}
	
	@Test
	public void testSetStartingTimeAtMonth() {
		myAuction.setStartingTime("14:30");
		Calendar startDate = myAuction.getDateAuctionStarts();
		assertEquals(startDate.get(Calendar.MONTH), 11);  // December
	}
	
	@Test
	public void testSetStartingTimeAtDate() {
		myAuction.setStartingTime("14:30");
		Calendar startDate = myAuction.getDateAuctionStarts();
		assertEquals(startDate.get(Calendar.DAY_OF_MONTH), 25);
	}
	
	@Test
	public void testSetStartingTimeAtHour() {
		myAuction.setStartingTime("14:30");
		Calendar startDate = myAuction.getDateAuctionStarts();
		assertEquals(startDate.get(Calendar.HOUR_OF_DAY), 14);
	}
	
	@Test
	public void testSetStartingTimeAtMinute() {
		myAuction.setStartingTime("14:30");
		Calendar startDate = myAuction.getDateAuctionStarts();
		assertEquals(startDate.get(Calendar.MINUTE), 30);
	}
	
	@Test
	public void testSetTimeDurationAtYear() {
		myAuction.setTimeDuration("4:30");
		Calendar endTime = myAuction.getDateAuctionEnds();
		assertEquals(endTime.get(Calendar.YEAR), 2015);
	}
	
	@Test
	public void testSetTimeDurationAtMonth() {
		myAuction.setTimeDuration("4:30");
		Calendar endTime = myAuction.getDateAuctionEnds();
		assertEquals(endTime.get(Calendar.MONTH), 11);  // December
	}
	
	@Test
	public void testSetTimeDurationAtDate() {
		myAuction.setTimeDuration("4:30");
		Calendar endTime = myAuction.getDateAuctionEnds();
		assertEquals(endTime.get(Calendar.DAY_OF_MONTH), 25);
	}
	
	@Test
	public void testSetTimeDurationAtHour() {
		myAuction.setTimeDuration("4:30");
		Calendar endTime = myAuction.getDateAuctionEnds();
		assertEquals(endTime.get(Calendar.HOUR_OF_DAY), 13);
	}
	
	@Test
	public void testSetTimeDurationAtMinute() {
		myAuction.setTimeDuration("4:30");
		Calendar endTime = myAuction.getDateAuctionEnds();
		assertEquals(endTime.get(Calendar.MINUTE), 00);
	}

	@Test
	public void testRemoveItemContainInList() {
		assertTrue(myAuction.removeItem(myItem1));
	}
	
	@Test
	public void testRemoveItemNotContainInList() {
		assertFalse(myAuction.removeItem(new Item("Google", 2, new BigDecimal(10000), "a website")));
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
		assertEquals(myAuction.getOrganizationName(), "GoodWill");
	}

	@Test (expected = IllegalArgumentException.class)
	public void testSetDateOfMonthOnNegativeNumber() {
		myAuction.setDateOfMonth(-1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetDateOfMonthOnZero() {
		myAuction.setDateOfMonth(0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetDateOfMonthOnBigNumber() {
		myAuction.setDateOfMonth(35);
	}
	
	@Test
	public void testSetDateOfMonth() {
		myAuction.setDateOfMonth(20);
		assertEquals(myAuction.getDateAuctionStarts().get(Calendar.DAY_OF_MONTH), 20);
	}

	@Test
	public void testSetMonth() {
		myAuction.setMonth(10);
		// since the calendar starts at index 0 for Jan, Auction class handle this error by subtracting one
		assertEquals(myAuction.getMonth(), 9); // 9 is actually October
	}

	@Test
	public void testSetYear() {
		myAuction.setYear(2016);
		assertEquals(myAuction.getYear(), 2016);
	}
	
	@Test
	public void testGetDayOfYear() {
		assertEquals(myAuction.getDayOfYear(), myAuctionStartDate.get(Calendar.DAY_OF_YEAR));
	}

//	@Test
//	public void testDisplayItemsInAuction() {
//		assertEquals("GoodWill Fri Dec 25 08:30:00 PST 2015\na) iPad\nQuantity: 1\n"
//				+ "Starting Price: $300.00\nDescription: 4th generation\nb) Macbook\n"
//				+ "Quantity: 1\nStarting Price: $1,000.00\nDescription: Used\n", 
//				myAuction.displayItemsInAuction());
//	}

	@Test
	public void testToString() {
		assertEquals("GoodWill-Dec-25-2015, 2, 8:30, 2:10", myAuction.toString());
	}

	@Test
	public void testCompareTo() {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2015, 12, 25, 16, 30);
		Auction temp = new Auction("GoodWill", new ArrayList<Item>(), cal, "2:10");
		assertTrue(myAuction.compareTo(temp) < 0);
	}
}
