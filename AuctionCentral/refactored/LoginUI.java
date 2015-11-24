package refactored;

import java.util.Map;
import java.util.Scanner;

import current.DisplayCalendar;
import current.User;

public class LoginUI {
	private Map<String, User> myUsers;
	
	public LoginUI(Map<String, User> myUsers, DisplayCalendar myCalendar) {
		this.myUsers = myUsers;
	}
	
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
		//myUsers.get(user).run(scanner, myCalendar);			//WATCH THIS!
		//scanner.close();
	}

}
