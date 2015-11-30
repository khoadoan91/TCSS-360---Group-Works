package test;

import static org.junit.Assert.*;

import java.awt.Image;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dev.Bidder;

import org.junit.Before;
import org.junit.Test;

import dev.Auction;
import dev.Bid;
import dev.DisplayCalendar;
import dev.Item;

import org.junit.Test;

public class BidderTest {

	Bidder myBidder;
	Item myItem;
	Bid myBid;
	Auction myAuction;

	@Before
	public void setup() {
		myBidder = new Bidder("123 Main St.", "123456789");
		myItem = new Item("Golf Clubs", 6, new BigDecimal("1000.00"), "Six golf clubs");
		myBid = new Bid(myItem, 225.0, myBidder.toString());
		Calendar expected = Calendar.getInstance();
		expected.set(Calendar.DAY_OF_YEAR, expected.get(Calendar.DAY_OF_YEAR) + 2);
		myAuction = new Auction("NPO", new ArrayList<Item>(), expected, "03:00");
	}

	@Test(expected=NullPointerException.class)
	public void addBidOnNullTest() {
		
	}
	
	@Test
	public void addBidOnDuplicateTest() {
		
	}
	 @Test
	public void addBidOnGoodBidTest() {
		
	}
	
	@Test(expected=NullPointerException.class)
	public void removeBidOnNulTest() {
		
	}
	
	@Test
	public void removeBideOnNotContainedTest() {
		
	}
	
	@Test
	public void removeBidOnGoodBidTest() {
		
	}

}
