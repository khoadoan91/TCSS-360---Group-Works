package refactoredTests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import refactored.Auction;
import refactored.Item;
import refactored.NPEmployee;

/**
 * Tests data integrity for NPEmployee objects.
 * @author nabilfadili
 *
 */
public class NPEmployeeTest {

	public NPEmployee employeeWithAuction;
	public NPEmployee employeeWithoutAuction;
	public Auction testAuction;

	@Before
	public void setUp() throws Exception {
		List<Item> theItems = new ArrayList<Item>();
		theItems.add(new Item("book", 1, new BigDecimal(100), "A book"));
		testAuction = new Auction("ABC-DEC-20-2015", theItems, "10:00", "11:00");
		employeeWithAuction = new NPEmployee("ABC", testAuction);
		employeeWithoutAuction = new NPEmployee("DEF", null);
	}

	/**
	 * Tests integrity of User hierarchy.
	 */
	@Test
	public void testNPEmployee() {
		assertTrue(employeeWithAuction instanceof NPEmployee);
	}

	/**
	 * An NPEmployee should have at most one auction at a time and null otherwise.
	 * @throws ParseException
	 */
	@Test
	public void testAddAuctionToEmployeeWithoutAuction() throws ParseException {
		assertEquals(null, employeeWithoutAuction.getMyCurrentAuction());
		List<Item> testItems = new ArrayList<Item>();
		testItems.add(new Item("tv", 1, new BigDecimal(10000), "Big tv"));
		Auction testAuction = new Auction("DEF-JAN-25-2016", testItems, "12:00", "13:00");
		employeeWithoutAuction.addAuction(testAuction);
		assertEquals(testAuction, employeeWithoutAuction.getMyCurrentAuction());
	}

	/**
	 * An NPEmployee should have at most one auction at a time and null otherwise.
	 */
	@Test
	public void testRemoveAuction() {
		assertEquals(testAuction, employeeWithAuction.getMyCurrentAuction());
		employeeWithAuction.removeAuction();
		assertEquals(null, employeeWithAuction.getMyCurrentAuction());
	}


}
