/**
 * 
 */
package test;

import static org.junit.Assert.*;
import old.NPEmployee;

import org.junit.Before;
import org.junit.Test;

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
	 * Test method for {@link old.NPEmployee#getUserType()}.
	 */
	@Test
	public void testGetUserType() {
		NPEmployee testUser = new NPEmployee("FirstName LastName");
		assertEquals("NPEmployee", testUser.getUserType());
	}

	/**
	 * Test method for {@link old.NPEmployee#NPEmployee(java.lang.String)}.
	 */
	@Test
	public void testNPEmployee() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link old.NPEmployee#addAuction()}.
	 */
	@Test
	public void testAddAuction() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link old.NPEmployee#editAuction()}.
	 */
	@Test
	public void testEditAuction() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link old.NPEmployee#removeAuction()}.
	 */
	@Test
	public void testRemoveAuction() {
		fail("Not yet implemented");
	}

}
