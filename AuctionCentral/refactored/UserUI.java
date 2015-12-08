
package refactored;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Interface for sublass UIs.
 * @author nabilfadili
 *
 */
public interface UserUI {
	public void promptMainMenu(BufferedReader reader, CalendarUI theCalendar, User currentUser) throws IOException, InterruptedException;
}
