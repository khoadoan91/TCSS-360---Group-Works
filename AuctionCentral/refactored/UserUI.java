
package refactored;

import java.util.Scanner;

/**
 * Interface for sublass UIs.
 * @author nabilfadili
 *
 */
public interface UserUI {
	public void promptMainMenu(Scanner scanner, DisplayCalendar theCalendar, User currentUser);
}
