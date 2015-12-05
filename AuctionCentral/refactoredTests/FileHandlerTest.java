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
	FileHandler fh;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		fh = new FileHandler();
	}
	
	@Test
	public void testReadingSerializedUserFileWithOneUser() {
		
	}

	@Test
	public void testWritingSerializedFileWithOneUser() {
		
	}

}
