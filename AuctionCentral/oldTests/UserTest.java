
package oldTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import old.UserOLD;

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
	 * Test method for {@link old.UserOLD#getUsername()}.
	 */
	@Test
	public void testGetUsername() {
		UserOLD testUser = new UserOLD("FirstName LastName");
		assertEquals("FirstName LastName", testUser.getUsername());
	}

	/**
	 * Test method for {@link old.UserOLD#getUserType()}.
	 */
	@Test
	public void testGetUserType() {
		UserOLD testUser = new UserOLD("FirstName LastName");
		assertEquals("User unspecified", testUser.getUserType());
	}

}
