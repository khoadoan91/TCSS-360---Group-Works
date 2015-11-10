/**
 * 
 */
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author nabilfadili
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		User currentUser = null;
		boolean flag = true;
		while (flag) {
			currentUser = login();
			if (currentUser != null) {flag = false;}
		} 
		if (currentUser.getUserType().equals("ACEmployee")) {
			ACEmployeeMainMenu();
		}
		if (currentUser.getUserType().equals("NPEmployee")) {
			NPEmployeeMainMenu();
		}
		if (currentUser.getUserType().equals("Bidder")) {
			bidderMainMenu();
		}
	}
	
	/**
	 * ACEmployee main menu of program. Called after login.
	 */
	public static void ACEmployeeMainMenu() {
		System.out.println("\nWhat would you like to do?");
		System.out.println("1. View all auctions\n"
				+ "2. View specific auction\n"
				+ "3. Logout");	
	}
	/**
	 * NPEmployee main menu of program. Called after login.
	 */
	public static void NPEmployeeMainMenu() {
		System.out.println("\nWhat would you like to do?");
		System.out.println("1. Schedule an auction\n"
				+ "2. Edit an auction\n"
				+ "3. Remove an auction\n"
				+ "4. Logout");
	}
	/**
	 * Bidder main menu of program. Called after login.
	 */
	public static void bidderMainMenu() {
		System.out.println("\nWhat would you like to do?");
		System.out.println("1. View your current bids\n"
				+ "2. Make a bid\n"
				+ "3. Remove a bid\n"
				+ "4. Edit a bid\n"
				+ "5. Logout");	
	}
	
	/**
	 * Decides which user object to create and return to the main program
	 * @return User
	 */
	public static User login() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter username:");
		String loginInput = scanner.nextLine();
		
		File inputFile = new File("user_list.txt");
		try {
			scanner = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			errorMessage(e);
		}
		
		String username = null, userType = null;
		while (scanner.hasNext()) {
			username = scanner.next();
			if (loginInput.equalsIgnoreCase(username)) {				
				System.out.println("Welcome " + username + "!");
				userType = scanner.next();
				System.out.println("Logged in as: " + userType);
				break;
			}
			else {
				scanner.nextLine();
			}
		}
		if (userType == null) {
			System.out.println("Thats not a valid username");
			return null;
		}
		if (userType.equals("ACEmployee")) {
			return new ACEmployee(username);
		}
		if (userType.equals("NPEmployee")) {
			return new NPEmployee(username);
		}
//		if (userType.equals("Bidder")) {
//			return new Bidder(username);
//		}
		return null;
	}
	
	/**
	 * Used to pass various exceptions and generate the appropriate error message
	 * for the user.
	 * @param e
	 */
	public static void errorMessage(Exception e) {
		if (e == null) {
			System.out.println("Error reading from file. User = null"); 
		}
		if (e.getClass().isInstance(new FileNotFoundException())) {
			System.out.println("Error reading file. Does not exist.");
		}
		//TODO add exceptions here as they come up so that the user is notified
		//     of the error.
	}

}