package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.Auction;
import model.DisplayCalendar;
import model.Item;
import model.Main;

import org.junit.Before;
import org.junit.Test;

public class MainTest {
	
	private Main testDriver = new Main();
	private ArrayList<Item> testItems;
	private ArrayList<Auction> testAuctions;
	private DisplayCalendar testMyCalendar;

	@SuppressWarnings("static-access")
	@Before
	public void setUp() throws Exception {
		testItems = testDriver.readItemListFile();
		testAuctions = testDriver.readAuctionListFile(testItems);
		testMyCalendar = new DisplayCalendar(testAuctions);
	}

	@Test
	public void testACEmployeeMainMenu() {
		fail("Not yet implemented");
	}

	@Test
	public void testNPEmployeeMainMenu() {
		fail("Not yet implemented");
	}

	@Test
	public void testBidderMainMenu() {
		fail("Not yet implemented");
	}

	@Test
	public void testLogout() {
		fail("Not yet implemented");
	}

	@Test
	public void testLogin() {
		
		
	}

	@Test
	public void testReadAuctionListFile() {
		//assertEquals(testAuctions.get(0).getAuctionName(), "GOODWILL-INDUSTRIES-OCT-26-2015");
		assertEquals(testAuctions.get(0).getOrganizationNam(), "GOODWILL-INDUSTRIES");
		assertEquals(testAuctions.get(0).getAllItems().size(), 3);
		//assertEquals(testAuctions.get(0).getMonth(), 10);
		assertEquals(testAuctions.get(0).getDayOfMonth(), 26);
		assertEquals(testAuctions.get(0).getYear(), 2015);
		assertEquals(testAuctions.get(0).getHour(), 2);
		System.out.println(testAuctions.get(0).getMin());
		//assertEquals(testAuctions.get(0).getMin(), 30);
	}

	@Test
	public void testReadItemListFile() {
		assertEquals(testItems.get(0).getTitle(), "Animal Farm");
		assertEquals(testItems.get(0).getQuantity(), 1);
		assertEquals(testItems.get(0).getDescription(), "An allegorical and dystopian novella by George Orwell");
		assertEquals(testItems.get(1).getTitle(), "Harry Potter (series)");
		assertEquals(testItems.get(1).getQuantity(), 8);
		assertEquals(testItems.get(1).getDescription(), "The entire series of Harry Potter books by J.K. Rowling");
	}

}
