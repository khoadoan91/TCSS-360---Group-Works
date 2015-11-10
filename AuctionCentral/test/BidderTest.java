package test;

import static org.junit.Assert.*;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Bid;
import model.Bidder;
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
	
	@Before
	public void setup() {
		//myBidder = new Bidder("Nina", "125 Main St.", "123456789");
		myItem = new Item("Golf Clubs", 6, "Six golf clubs");
		myBid = new Bid(myItem, 225.0);
	}

	@Test
	public void testBidder() {
		//Bidder bidder = new Bidder("Nina", "125 Main St.", "123456789");
		//assertEquals(myBidder, bidder);
	}

	@Test
	public void testViewBids() {
		List<Bid> bids = new ArrayList<Bid>();
		bids.add(myBid);
		assertEquals(bids, myBidder.viewBids());
	}

	@Test
	public void testAddBid() {	
		myBidder.addBid(myBid);
		assertTrue(myBidder.viewBids().contains(myBid));
	}

	@Test
	public void testRemoveBid() {	
		myBidder.addBid(myBid);
		myBidder.removeBid(myBid);
		assertTrue(!myBidder.viewBids().contains(myBid));
	}

}
