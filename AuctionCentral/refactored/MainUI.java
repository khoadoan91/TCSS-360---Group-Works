package refactored;


import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.Map;

/**
 * Main driver class for console interaction.
 * @author nabilfadili
 *
 */
public class MainUI {

	private HashMap<String, User> allUsers;
	private User currentUser;

	/**
	 * Takes all loaded users for login verification
	 * @param myUsers list of all users
	 */
	public MainUI(HashMap<String, User> myUsers) {
		allUsers = myUsers;
	}

	/**
	 * Prompts user to enter username for verification
	 * @throws IOException
	 */
	public void promptLogin(BufferedReader reader) throws IOException {
		boolean isLogin = false;
		String user;
		do {
			System.out.print("Enter username: ");
			user = reader.readLine();
			if (allUsers.containsKey(user)) {
				System.out.println("Welcome " + user + "!");
				if (allUsers.get(user) instanceof NPEmployee) {
					System.out.println("Organization: " + ((NPEmployee)allUsers.get(user)).getMyOrgName());
				}
				isLogin = true;
			} else {
				System.out.println("Oops, please try again");
			}
		} while (!isLogin);
		currentUser = allUsers.get(user);
	}


	/**
	 * Runs the main menus for the currently logged in user.
	 * @param reader for input
	 * @param theCalendar
	 * @return the modified users to be serialized.
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public HashMap<String, User> run(BufferedReader reader, CalendarUI theCalendar) throws IOException, InterruptedException {
		UserUI ui;
		if (currentUser instanceof ACEmployee) {
			ui = new ACEmployeeUI();
			ui.promptMainMenu(reader, theCalendar, currentUser);
		}
		if (currentUser instanceof NPEmployee) {
			ui = new NPEmployeeUI();
			ui.promptMainMenu(reader, theCalendar, currentUser);
		}
		if (currentUser instanceof Bidder) {
			ui = new BidderUI();
			ui.promptMainMenu(reader, theCalendar, currentUser);
		}
		return allUsers;	
	}

}
