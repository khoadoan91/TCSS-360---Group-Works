/**
 * 
 */
package model;

import java.io.BufferedReader;
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
			if (currentUser != null) {flag = false;	}
		} 
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
			System.out.println("Error reading file.");
		}
		//TODO add exceptions here as they come up so that the user is notified
		//     of the error.
	}

}
