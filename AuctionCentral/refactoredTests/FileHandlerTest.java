/**
 * 
 */
package refactoredTests;

import static org.junit.Assert.*;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import refactored.Auction;
import refactored.FileHandler;
import refactored.Item;
import refactored.User;

/**
 * @author nabilfadili
 *
 */
public class FileHandlerTest {
	private Map<String, User> testUsers;
	private List<Auction> testAuctionList;
	private List<Item> testItemList;
	FileHandler fh;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		fh = new FileHandler();
	}

	/**
	 * Test method for {@link refactored.FileHandler#readUserFile(java.io.File)}.
	 */
	@Test
	public void testReadUserFile() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link refactored.FileHandler#readAuctionFile(java.io.File, java.io.File)}.
	 */
	@Test
	public void testReadAuctionFile() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link refactored.FileHandler#readItemFile(java.io.File)}.
	 */
	@Test
	public void testReadItemFile() {
		fail("Not yet implemented");
	}
	
	/**
	 * Asserts an NPEmployee works for an authorized non-profit organization.
	 * @throws ParseException 
	 */
	@Test
	public void testMatchAuctionAndNPE() throws ParseException {
		//auctionList.add(new Auction("MACY", testItemList, "10", "1"));
		//auctionList.add(new Auction("TARGET", new ArrayList<Item>(), "10", "1"));
		//auctionList.add(new Auction("WALMART", new ArrayList<Item>(), "10", "1"));

//		FileHandler fh = new FileHandler();
//		//Auction testAuction = fh.matchAuctionAndNPE(testAuctionList, "MACY");
//		assertEquals(testAuction.getOrganizationNam(), "MACY");
//		
	}

}
