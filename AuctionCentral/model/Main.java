//<<<<<<< HEAD
///**
// * 
// */
//package model;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Scanner;
//
///**
// * @author nabilfadili
// *
// */
//public class Main {
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		User currentUser = null;
//		boolean flag = true;
//		while (flag) {
//			currentUser = login();
//			if (currentUser != null) {
//				flag = false;
//			}
//		}
//
//		// TODO Instantiate DisplayCalendar
//		// load auction into calendar
//
//		if (currentUser.getUserType().equals("ACEmployee")) {
//			ACEmployeeMainMenu();
//		}
//		if (currentUser.getUserType().equals("NPEmployee")) {
//			NPEmployeeMainMenu();
//		}
//		if (currentUser.getUserType().equals("Bidder")) {
//			bidderMainMenu();
//		}
//	}
//
//	/**
//	 * ACEmployee main menu of program. Called after login.
//	 */
//	public static void ACEmployeeMainMenu() {
//		System.out.println("\nWhat would you like to do?");
//		System.out.println("1. View all auctions\n" + "2. View specific auction\n" + "3. Logout");
//	}
//
//	/**
//	 * NPEmployee main menu of program. Called after login.
//	 */
//	public static void NPEmployeeMainMenu() {
//		System.out.println("\nWhat would you like to do?");
//		System.out
//				.println("1. Schedule an auction\n" + "2. Edit an auction\n" + "3. Remove an auction\n" + "4. Logout");
//	}
//
//	/**
//	 * Bidder main menu of program. Called after login.
//	 */
//	public static void bidderMainMenu() {
//		System.out.println("\nWhat would you like to do?");
//		System.out.println("1. View your current bids\n" + "2. Make a bid\n" + "3. Remove a bid\n" + "4. Edit a bid\n"
//				+ "5. Logout");
//	}
//
//	/**
//	 * Decides which user object to create and return to the main program
//	 * 
//	 * @return User
//	 */
//	public static User login() {
//		Scanner scanner = new Scanner(System.in);
//		System.out.print("Enter username:");
//		String loginInput = scanner.nextLine();
//
//		File inputFile = new File("user_list.txt");
//		try {
//			scanner = new Scanner(inputFile);
//		} catch (FileNotFoundException e) {
//			errorMessage(e);
//		}
//
//		String username = null, userType = null;
//		while (scanner.hasNext()) {
//			username = scanner.next();
//			if (loginInput.equalsIgnoreCase(username)) {
//				System.out.println("Welcome " + username + "!");
//				userType = scanner.next();
//				System.out.println("Logged in as: " + userType);
//				break;
//			} else {
//				scanner.nextLine();
//			}
//		}
//		if (userType == null) {
//			System.out.println("Thats not a valid username");
//			return null;
//		}
//		if (userType.equals("ACEmployee")) {
//			return new ACEmployee(username);
//		}
//		if (userType.equals("NPEmployee")) {
//			return new NPEmployee(username);
//		}
//		if (userType.equals("Bidder")) {
//			return new Bidder(username);
//		}
//		return null;
//	}
//
//	/**
//	 * Used to pass various exceptions and generate the appropriate error
//	 * message for the user.
//	 * 
//	 * @param e
//	 */
//	public static void errorMessage(Exception e) {
//		if (e == null) {
//			System.err.println("Error reading from file. User = null");
//		}
//		if (e.getClass().isInstance(new FileNotFoundException())) {
//			System.err.println("Error reading file. Does not exist.");
//		}
//		// TODO add exceptions here as they come up so that the user is notified
//		// of the error.
//	}
//
//=======
/**
 * 
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

/**
 * @author nabilfadili
 *
 */
public class Main {

	/**
	 * @author nabilfadili
	 * @param args
	 */
	public static void main(String[] args) {
		boolean flag = true;
		do {
			User currentUser = null;
			//boolean flag2 = true;
			while (flag) {
				currentUser = login();
				if (currentUser != null) {flag = false;}
			}
			flag = true;
			if (currentUser.getUserType().equals("ACEmployee")) {
				flag = ACEmployeeMainMenu((ACEmployee)currentUser);
			}
			if (currentUser.getUserType().equals("NPEmployee")) {
				flag = NPEmployeeMainMenu((NPEmployee)currentUser);
			}
			if (currentUser.getUserType().equals("Bidder")) {
				flag = bidderMainMenu((Bidder)currentUser);
			}
		} while (flag);
	}
	
	/**
	 * @author nabilfadili
	 * ACEmployee main menu of program. Called after login.
	 */
	public static boolean ACEmployeeMainMenu(ACEmployee currentUser) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("\nWhat would you like to do?");
			System.out.println("1. View all auctions\n"
					+ "2. View specific auction\n"
					+ "3. View the calendar\n"
					+ "4. Logout");	
			switch (scanner.nextInt()) {
			case 1:
				currentUser.viewAuctionList();
				break;
			case 2:
				currentUser.selectAuction();
				break;
			case 3:
				currentUser.viewCalendar();
				break;
			case 4:
				return logout(currentUser);
			}
		}
	}
	/**
	 * @author nabilfadili
	 * NPEmployee main menu of program. Called after login.
	 */
	public static boolean NPEmployeeMainMenu(NPEmployee currentUser) {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("\nWhat would you like to do?");
			System.out.println("1. Schedule an auction\n"
					+ "2. Edit an auction\n"
					+ "3. Remove an auction\n"
					+ "4. Logout");
			switch (scanner.nextInt()) {
			case 1:
				currentUser.addAuction();
				break;
			case 2:
				currentUser.editAuction();
				break;
			case 3:
				currentUser.removeAuction();
				break;
			case 4:
				return logout(currentUser);
			}
		}
	}
	/**
	 * @author nabilfadili
	 * Bidder main menu of program. Called after login.
	 */
	public static boolean bidderMainMenu(Bidder currentUser) {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("\nWhat would you like to do?");
			System.out.println("1. View your current bids\n"
					+ "2. Make a bid\n"
					+ "3. Remove a bid\n"
					+ "4. Edit a bid\n"
					+ "5. Logout");	
			switch (scanner.nextInt()) {
			case 1:
				
				break;
			case 2:
				
				break;
			case 3:
				
				break;
			case 4:
				break;
			case 5:
				return logout(currentUser);
			}
		}
	}
	
	/**
	 * Writes changes to the auction list and item list txt files that were
	 * made during the session. This includes added and removed bids.
	 * @param endUser made the the changes and thus narrows down where the txt fields will be changed.
	 */
	public static boolean logout(User endUser) {
		Scanner scanner = new Scanner(System.in);
		if (endUser.getUserType().equals("ACEmployee")) {	
			
		}
		if (endUser.getUserType().equals("NPEmployee")) {
			
		}
		if (endUser.getUserType().equals("Bidder")) {
			
		}
		do {
			System.out.println("Login as another user?\n" + "1. Yes\n" + "2. No");
			switch (scanner.nextInt()) {
			case 1:
				return true;
			case 2:
				return false;
			default:
				System.out.println("Invalid input.");
				break;
			} 
		} while(true);
	}
	
	/**
	 * @author nabilfadili
	 * Decides which user object to create and return to the main program
	 * @return User
	 */
	public static User login() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter username:");
		String loginInput = scanner.nextLine();
		
		File inputFile = new File("user_list.txt");
		ArrayList<Item> items = readItemListFile();
		ArrayList<Auction> auctions = readAuctionListFile(items);
		DisplayCalendar myCalendar = new DisplayCalendar(auctions);
		try {
			scanner = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			errorMessage(e);
		}
		
		String username = null, userType = null, orgName = null;
		while (scanner.hasNext()) {
			username = scanner.next();
			if (loginInput.equalsIgnoreCase(username)) {				
				System.out.println("Welcome " + username + "!");
				userType = scanner.next();
				if (userType.equals("NPEmployee")) {
					orgName = scanner.next();
				}
				System.out.println("Logged in as: " + userType + " (" + orgName + ")");
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
			return new ACEmployee(username, myCalendar);
		}
		if (userType.equals("NPEmployee")) {
			// FIXME NPEmployee needs to have the third parameter to store theOrgName
			return new NPEmployee(username, orgName, myCalendar);
		}
		if (userType.equals("Bidder")) {
			return new Bidder(username, myCalendar);
		}
		return null;
	}
	
	/**
	 * @author nabilfadili
	 * Reads/parses the auction txt file and creates an Auction object for each line.
	 * Auction txt file must be in sync with the item txt file for this to work.
	 * @param allItems is a list of all items read in for all auctions.
	 * @return a list of Auction objects.
	 */
	public static ArrayList<Auction> readAuctionListFile(ArrayList<Item> allItems) {
		ArrayList<Auction> auctionList = new ArrayList<>();
		String orgName, timeDuration;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy");
		List<Item> auctionItems;
		int itemsPerAuction;                //Keeps track of where to be adding items from in the allItems list. WATCH THIS
		int itemListCounter = 0;
		try {
			File file = new File("auction_list.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			String[] tokens;			
										   
			while ((line = bufferedReader.readLine()) != null) {
				tokens = line.split(", ");
				//Set orgName
				orgName = tokens[0];
				
				//Creating item list
				itemsPerAuction = Integer.parseInt(tokens[1]);			      //quantity of items
				auctionItems = new ArrayList<>(itemsPerAuction);  
				for (int i = 0; i < itemsPerAuction; ++i) {
					auctionItems.add(allItems.get(itemListCounter + i));
				}
				itemListCounter += itemsPerAuction;                           //increment for the next auction's items
				
				//Creating calendar object
				cal.setTime(sdf.parse(tokens[2]));
				
				//Set duration
				timeDuration = tokens[3];
				
				//Create Auction item
				auctionList.add(new Auction(orgName, auctionItems, cal, timeDuration));
			}
			bufferedReader.close();
		}
		catch(IOException | ParseException e) {
			e.printStackTrace();
			System.out.println("Error reading auction file.");
		}
		return auctionList;
	}
	/**
	 * @author nabilfadili
	 * Reads/parses the item txt file and creates an Item object for each line.
	 * Item txt file must be in sync with the auction txt file for this to work.
	 * @return a list of all Item objects for all auctions.
	 */	
	public static ArrayList<Item> readItemListFile() {
		ArrayList<Item> items = new ArrayList<>();				
		try {
			File file = new File("item_list.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			String[] tokens;
			while ((line = bufferedReader.readLine()) != null) {
				tokens = line.split(", ");
				items.add(new Item(tokens[0], Integer.parseInt(tokens[1]), tokens[2]));
			}
			bufferedReader.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error reading item file.");
		}
		return items;
	}
	
	/**
	 * @author nabilfadili
	 * Used to pass various exceptions and generate the appropriate error message
	 * for the user.
	 * @param e
	 */
	public static void errorMessage(Exception e) {
		if (e == null) {
			System.err.println("Error reading from file. User = null"); 
		}
		if (e.getClass().isInstance(new FileNotFoundException())) {
			System.err.println("Error reading file. Does not exist.");
		}
		//TODO add exceptions here as they come up so that the user is notified
		//     of the error.
	}
	
	public static void testFileaccuracy(ArrayList<Auction> auctions) {
		//Tests output of reading the files
		for (int i = 0; i < auctions.size(); i++) {
			System.out.println(auctions.get(i).getAuctionName() + "\n" +
					auctions.get(i).getOrganizationNam() + "\n" +
					auctions.get(i).getMonth() + "-" 
					+ auctions.get(i).getDayOfMonth() + "-" 
					+ auctions.get(i).getYear() + "\n" +
					auctions.get(i).getHour() + ":" + auctions.get(i).getMin());
			List<Item> theItems = auctions.get(i).getAllItems();
			for (int j = 0; j < theItems.size(); j++) {
				System.out.print(theItems.get(j).getTitle() + ", ");
			}
			System.out.println("\n");
	    }
	}
}
