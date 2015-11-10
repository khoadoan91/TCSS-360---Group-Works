package test;

import static org.junit.Assert.*;

import java.awt.Image;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.Bid;
import model.Item;

/**
 * JUnit tests for the Bid class.
 * 
 * @author Nina Chepovska
 * @version
 */
public class BidTest {
	
	Item myItem;
	Bid myBid;
	
	@Before
	public void setup() {
		myItem = new Item("Golf Clubs", 6, "Six golf clubs");
		myBid = new Bid(myItem, 225.0);
	}

	@Test
	public void testBid() {
		Bid bid = new Bid(new Item("Golf Clubs", 6, "Six golf clubs"), 225.0);
		assertEquals(myBid, bid);
	}

	@Test
	public void testGetItem() {
		assertEquals(myItem, myBid.getItem());
	}

	@Test
	public void testGetBidAmount() {
		assertEquals(225.0, myBid.getBidAmount(), 0.0001);
	}

	@Test
	public void testSetBidAmount() {
		myBid.setBidAmount(350.0);
		assertEquals(350.0, myBid.getBidAmount(), 0.0001);
	}
}
