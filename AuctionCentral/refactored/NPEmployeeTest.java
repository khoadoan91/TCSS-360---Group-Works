package refactored;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

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

	@Test
	public void testNPEmployee() {
		assertTrue(employeeWithAuction instanceof NPEmployee);
	}

	@Test
	public void testAddAuctionToEmployeeWithAuction() {
		//fail
	}
	@Test
	public void testAddAuctionToEmployeeWithoutAuction() {
		//fail
	}
	@Test
	public void testRemoveAuction() {
		assertEquals(testAuction, employeeWithAuction.getMyAuction());
		employeeWithAuction.removeAuction();
		assertEquals(null, employeeWithAuction.getMyAuction());
	}
	@Test
	public void testViewAuctionWithNoAuctionScheduled() {
		assertEquals("You do not have an auction scheduled.", employeeWithoutAuction.viewAuction());
	}
	@Test
	public void testViewAuctionWithAuctionScheduled() {
		assertEquals(employeeWithAuction.getMyAuction().toString(), employeeWithAuction.viewAuction());
	}

}
