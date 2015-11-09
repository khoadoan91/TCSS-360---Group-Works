package test;

import static org.junit.Assert.*;
import model.DisplayCalendar;
import model.Auction;

import org.junit.Before;
import org.junit.Test;

public class CalendarTester {
	
	private DisplayCalendar myEmptyCal;
	private DisplayCalendar myOneAucCal;
	private DisplayCalendar myFullCal;

	@Before
	public void setUp() {
		myEmptyCal = new DisplayCalendar();
		myFullCal = new DisplayCalendar();
		for (int i = 0; i < 25; i++) {
			myFullCal.addAuction(new Auction());
		}
	}

	@Test
	public void testHasMaxAuctions1() {
		assertFalse(myEmptyCal.hasMaxAuctions());
	}
	
	@Test
	public void testHasMaxAuctions2() {
		assertTrue(myFullCal.hasMaxAuctions());
	}
}
