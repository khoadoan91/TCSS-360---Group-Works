package refactored;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;


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
	 * @throws IOException 
	 */
	public void promptLogin(BufferedReader reader) throws IOException {
		boolean isLogin = false;
		String user;
		do {
			System.out.print("Enter username: ");
			user = reader.readLine();
			if (allUsers.containsKey(user)) {
				System.out.println("Welcome " + user + "!!!!");
				isLogin = true;
			} else {
				System.out.println("Oops, please try again");
			}
		} while (!isLogin);
		currentUser = allUsers.get(user);
	}

	public void run(BufferedReader reader, CalendarUI theCalendar) throws IOException {
		UserUI ui;
		if (currentUser instanceof ACEmployee) {
			ui = new ACEmployeeUI();
			ui.promptMainMenu(reader, theCalendar, currentUser);
		}
		if (currentUser instanceof NPEmployee) {
			ui = new NPEmployeeUI();
			ui.promptMainMenu(reader, theCalendar, currentUser);		//Probably need to pass the user object
		}
		if (currentUser instanceof Bidder) {
			ui = new BidderUI();
			ui.promptMainMenu(reader, theCalendar, currentUser);
		}		
	}

}
