
package test;

import static org.junit.Assert.*;
import old.User;

import org.junit.Before;
import org.junit.Test;

/**
 * @author nabilfadili
 *
 */
public class UserTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// TODO get this to work correctly!
	}

	/**
	 * Test method for {@link old.User#getUsername()}.
	 */
	@Test
	public void testGetUsername() {
		User testUser = new User("FirstName LastName");
		assertEquals("FirstName LastName", testUser.getUsername());
	}

	/**
	 * Test method for {@link old.User#getUserType()}.
	 */
	@Test
	public void testGetUserType() {
		User testUser = new User("FirstName LastName");
		assertEquals("User unspecified", testUser.getUserType());
	}

}
