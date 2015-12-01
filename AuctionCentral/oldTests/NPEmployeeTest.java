/**
 * 
 */
package oldTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import old.NPEmployeeOLD;

/**
 * @author nabilfadili
 *
 */
public class NPEmployeeTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link old.NPEmployeeOLD#getUserType()}.
	 */
	@Test
	public void testGetUserType() {
		NPEmployeeOLD testUser = new NPEmployeeOLD("FirstName LastName");
		assertEquals("NPEmployee", testUser.getUserType());
	}

	/**
	 * Test method for {@link old.NPEmployeeOLD#NPEmployee(java.lang.String)}.
	 */
	@Test
	public void testNPEmployee() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link old.NPEmployeeOLD#addAuction()}.
	 */
	@Test
	public void testAddAuction() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link old.NPEmployeeOLD#editAuction()}.
	 */
	@Test
	public void testEditAuction() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link old.NPEmployeeOLD#removeAuction()}.
	 */
	@Test
	public void testRemoveAuction() {
		fail("Not yet implemented");
	}

}
