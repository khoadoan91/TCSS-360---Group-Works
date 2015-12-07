package refactoredTests;

import static org.junit.Assert.*;

import java.awt.Image;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;
import refactored.Auction;
//import refactored.Bid;
import refactored.Bidder;
import refactored.DisplayCalendar;
import refactored.Item;

import org.junit.Test;

public class BidderTest {

	Bidder myBidder;
	Item myItem;
	List<Item> myItems;
	Calendar myCalendar;
	Auction myAuction;
	BigDecimal myBid;

	@Before
	public void setup() {
		myBidder = new Bidder("Nina", "123 Main St.", "123456789");
		myItem = new Item("Golf Clubs", 6, new BigDecimal("1000.00"), "Six golf clubs");
		myItems = new ArrayList<Item>();
		myItems.add(myItem);
		myCalendar = Calendar.getInstance();
		myAuction = new Auction("The Good NPO", myItems, myCalendar, "02:00");
		myBid = new BigDecimal(2000.0);
	}

	@Test(expected=NullPointerException.class)
	public void testAddBidOnNullAuction() {
		myBidder.addBid(null, myItem, myBid);
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddBidOnNullItem() {
		myBidder.addBid(myAuction, null, myBid);
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddBidOnNullBid() {
		myBidder.addBid(myAuction, myItem, null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testAddBidOnAuctionDoesNotContainItem() {
		Item item = new Item("TV", 1, new BigDecimal("100.0"), "A very nice TV.");
		myBidder.addBid(myAuction, item, myBid);
	}
	
	@Test
	public void testAddBidOnBidderDoesNotContainAuction() {
		Auction auction = new Auction("Another NPO", new ArrayList<Item>(), Calendar.getInstance(), "05:00");
		assertTrue(myBidder.addBid(auction, myItem, myBid));
	}
	
	@Test
	public void testAddBidOnNegativeBid() {
		assertFalse(myBidder.addBid(myAuction, myItem, new BigDecimal(-10.0)));
	}
	
	@Test
	public void testAddBidOnZeroBid() {
		assertFalse(myBidder.addBid(myAuction, myItem, new BigDecimal(0.0)));
	}
	
	@Test
	public void testAddBidMinPriceBid() {
		assertFalse(myBidder.addBid(myAuction, myItem, new BigDecimal(1000.0)));
	}
	
	@Test
	public void testAddBidOnGoodBid() {
		assertTrue(myBidder.addBid(myAuction, myItem, myBid));
	}

	@Test(expected=NullPointerException.class)
	public void testRemoveBidOnNullAuction() {
		myBidder.removeBid(null, myItem);
	}
	
	@Test(expected=NullPointerException.class)
	public void testRemoveBidOnNullItem() {
		myBidder.removeBid(myAuction, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRemoveBidOnBidderDoesNotContainAuction() {
		Auction auction = new Auction("Another NPO", new ArrayList<Item>(), Calendar.getInstance(), "05:00");
		myBidder.removeBid(auction, myItem);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testRemoveBidOnAuctionDoesNotContainItem() {
		Item item = new Item("TV", 1, new BigDecimal("100.0"), "A very nice TV.");
		myBidder.removeBid(myAuction, item);
	}

	@Test
	public void testRemoveBidOnGoodBid() {
		myBidder.removeBid(myAuction, myItem);
		assertFalse(myBidder.containsBid(myAuction, myItem));
	}
}
