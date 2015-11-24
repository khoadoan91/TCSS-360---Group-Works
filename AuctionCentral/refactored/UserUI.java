package refactored;

import java.util.Map;
import java.util.Scanner;

import current.DisplayCalendar;
import current.User;

public class UserUI {
	
	private Map<String, User> myUsers;
	
	/**
	 * Takes all loaded users for login verification
	 * @param myUsers list of all users
	 */
	public UserUI(Map<String, User> myUsers) {
		this.myUsers = myUsers;
	}
	
	/**
	 * Prompts user to enter username for verification
	 * @return the designated User type
	 */
	public User promptLogin() {
		Scanner scanner = new Scanner(System.in);
		boolean isLogin = false;
		String user;
		do {
			System.out.print("Enter username: ");
			user = scanner.next();
			if (myUsers.containsKey(user)) {
				System.out.println("Welcome " + user + "!!!!");
				isLogin = true;
			} else {
				System.out.println("Oops, please try again");
			}
		} while (!isLogin);
		return myUsers.get(user);
	}

}
