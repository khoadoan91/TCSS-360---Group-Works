/**
 * 
 */
package refactored;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author nabilfadili
 *
 */
public interface UserUI {
	public void promptMainMenu(BufferedReader reader, CalendarUI theCalendar, User currentUser) throws IOException;
}
