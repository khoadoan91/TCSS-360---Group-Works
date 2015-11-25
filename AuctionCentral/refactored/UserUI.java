/**
 * 
 */
package refactored;

import java.util.Scanner;

import old.User;

/**
 * @author nabilfadili
 *
 */
public interface UserUI {
	public void promptMainMenu(Scanner scanner, DisplayCalendar theCalendar, User currentUser);

}
