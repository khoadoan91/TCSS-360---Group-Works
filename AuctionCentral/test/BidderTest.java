package test;

import static org.junit.Assert.*;

import java.awt.Image;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Auction;
import model.Bid;
import model.Bidder;
import model.DisplayCalendar;
import model.Item;

/**
 * JUnit tests for the Bidder class.
 * 
 * @author Nina Chepovska
 * @version
 */
public class BidderTest {

	Bidder myBidder;
	Item myItem;
	Bid myBid;
	Auction myAuction;

	@Before
	public void setup() {
		myBidder = new Bidder("Nina", new DisplayCalendar());
		myItem = new Item("Golf Clubs", 6, new BigDecimal("1000.00"), "Six golf clubs");
		myBid = new Bid(myItem, 225.0, myBidder.getUsername());
		Calendar expected = Calendar.getInstance();
		expected.set(Calendar.DAY_OF_YEAR, expected.get(Calendar.DAY_OF_YEAR) + 2);
		myAuction = new Auction("NPO", new ArrayList<Item>(), expected, "03:00");
	}

	@Test
	public void testBidder() {
		Bidder bidder = new Bidder("Nina", new DisplayCalendar());
		assertEquals(myBidder, bidder);
	}

	@Test
	public void testViewBids() {
		List<Bid> bids = new ArrayList<Bid>();
		bids.add(myBid);
		assertEquals(bids, myBidder.viewBids());
	}

	@Test
	public void testAddBid() {
		myBidder.addBid(myAuction, myBid);
		assertTrue(myBidder.viewBids().contains(myBid));
	}

	@Test
	public void testRemoveBid() {
		myBidder.addBid(myAuction, myBid);
		myBidder.removeBid(myAuction, myBid);
		assertFalse(myBidder.viewBids().contains(myBid));
	}

}