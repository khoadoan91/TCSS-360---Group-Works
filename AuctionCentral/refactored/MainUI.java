package refactored;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Main driver class for console interaction.
 * @author nabilfadili
 *
 */
public class MainUI {
	
	private Map<String, User> allUsers;
	private User currentUser;
	
	/**
	 * Takes all loaded users for login verification
	 * @param myUsers list of all users
	 */
	public MainUI(Map<String, User> myUsers) {
		allUsers = myUsers;
	}
	
	/**
	 * Prompts user to enter username for verification
	 */
	public void promptLogin(Scanner scanner) {
		boolean isLogin = false;
		String user;
		do {
			System.out.print("Enter username: ");
			user = scanner.next();
			if (allUsers.containsKey(user)) {
				System.out.println("Welcome " + user + "!!!!");
				isLogin = true;
			} else {
				System.out.println("Oops, please try again");
			}
		} while (!isLogin);
		currentUser = allUsers.get(user);
	}

	public Map<String, User> run(Scanner scanner, DisplayCalendar theCalendar) {
		UserUI ui;
		if (currentUser instanceof ACEmployee) {
			ui = new ACEmployeeUI();
			ui.promptMainMenu(scanner, theCalendar, currentUser);
		}
		if (currentUser instanceof NPEmployee) {
			ui = new NPEmployeeUI();
			ui.promptMainMenu(scanner, theCalendar, currentUser);		
		}
		if (currentUser instanceof Bidder) {
			ui = new BidderUI();
			ui.promptMainMenu(scanner, theCalendar, currentUser);
		}	
		return allUsers;
	}

}
