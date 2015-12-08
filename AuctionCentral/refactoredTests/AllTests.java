package refactoredTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AuctionTest.class, BidderTest.class, CalendarTester.class, ItemTest.class, NPEmployeeTest.class })
public class AllTests {

}
