/**
 * 
 */
package test;

import static org.junit.Assert.*;
import model.Auction;
import model.Item;

import org.junit.Before;
import org.junit.Test;

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
		myAuction = new Auction();
	}

	/**
	 * Test method for {@link model.Auction#addItem(model.Item)}.
	 */
	@Test
	public void testAddItem() {
		
	}

	/**
	 * Test method for {@link model.Auction#removeItem(model.Item)}.
	 */
	@Test
	public void testRemoveItem() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Auction#getItem(model.Item)}.
	 */
	@Test
	public void testGetItem() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Auction#getAuctionDay()}.
	 */
	@Test
	public void testGetAuctionDay() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Auction#getAllItems()}.
	 */
	@Test
	public void testGetAllItems() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Auction#setAuctionDay(java.sql.Date)}.
	 */
	@Test
	public void testSetAuctionDay() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Auction#setAvailable(boolean)}.
	 */
	@Test
	public void testSetAvailable() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Auction#isAvailable()}.
	 */
	@Test
	public void testIsAvailable() {
		fail("Not yet implemented");
	}

}
