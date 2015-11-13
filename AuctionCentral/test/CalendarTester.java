package test;

import static org.junit.Assert.*;
import model.DisplayCalendar;

import org.junit.Before;
import org.junit.Test;

public class CalendarTester {

	private DisplayCalendar myEmptyCal;

	@Before
	public void setUp() {
		myEmptyCal = new DisplayCalendar();
	}

	@Test
	public void testHasMaxAuctions1() {
		assertFalse(myEmptyCal.hasMaxAuctions());
	}
}
