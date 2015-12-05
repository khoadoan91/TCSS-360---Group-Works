package refactoredTests;

import static org.junit.Assert.*;

import java.awt.Image;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
//	Bid myBid;
	Auction myAuction;

	@Before
	public void setup() {
		myBidder = new Bidder("123 Main St.", "123456789");
		myItem = new Item("Golf Clubs", 6, new BigDecimal("1000.00"), "Six golf clubs");
//		myBid = new Bid(myItem, 2000.0, myBidder.toString());
	}

	@Test(expected=NullPointerException.class)
	public void addBidOnNullTest() {
//		myBidder.addBid(null);
	}

	@Test
	public void addBidOnDuplicateTest() {
//		myBidder.addBid(myBid);
//		Bid bid = new Bid(myItem, 3000.0, myBidder.toString());
//		myBidder.addBid(bid);
//		assertFalse(myBidder.viewBids().contains(bid));
	}
	 @Test
	public void addBidOnGoodBidTest() {
//		myBidder.addBid(myBid);
//		assertTrue(myBidder.viewBids().contains(myBid));
	}

	@Test(expected=NullPointerException.class)
	public void removeBidOnNulTest() {
//		myBidder.removeBid(null);
	}

	@Test
	public void removeBideOnNotContainedTest() {
//		Bid bid = new Bid(new Item("TV", 1, new BigDecimal(20.0), "A nice TV."), 50.0, myBidder.toString());
//		myBidder.removeBid(bid);
//		assertFalse(myBidder.viewBids().contains(myBid));
	}

	@Test
	public void removeBidOnGoodBidTest() {
//		myBidder.addBid(myBid);
//		myBidder.removeBid(myBid);
//		assertFalse(myBidder.viewBids().contains(myBid));
	}
}
