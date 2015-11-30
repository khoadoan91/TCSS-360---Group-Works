package test;

import static org.junit.Assert.*;

import org.junit.Test;

import java.awt.Image;
import java.math.BigDecimal;
import java.util.ArrayList;

import dev.Bidder;

import org.junit.Before;
import org.junit.Test;

import dev.Bid;
import dev.DisplayCalendar;
import dev.Item;

public class BidTest {

	Item myItem;
	Bid myBid;
	Bidder myBidder;

	@Before
	public void setup() {
		myItem = new Item("Golf Clubs", 6, new BigDecimal("1000.00"), "Six golf clubs");
		myBidder = new Bidder("123 Main St.", "123456789"); 
		myBid = new Bid(myItem, 225.0, myBidder.toString());
	}

	@Test(expected=IllegalArgumentException.class)
	public void setBidAmountOnNegativeTest() {
		myBid.setBidAmount(-100.0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setBidAmountOnZeroTest() {
		myBid.setBidAmount(0.0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void setBidAmountOnLessThanStartingPriceTest() {
		myItem.setStartingPrice(new BigDecimal(20.0));
		myBid.setBidAmount(10.0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setBidAmountOnStartingPriceTest() {
		myItem.setStartingPrice(new BigDecimal(20.0));
		myBid.setBidAmount(20.0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setBidAmountOnGreaterThanStartingPriceTest() {
		myItem.setStartingPrice(new BigDecimal(20.0));
		myBid.setBidAmount(30.0);
	}
}

