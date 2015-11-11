/**
 * 
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
		ArrayList<Item> items = readItemListFile();
		ArrayList<Auction> auctions = readAuctionListFile(items);
		
		for (int i = 0; i < auctions.size(); i++) {
			System.out.println(auctions.get(i).getAuctionName() + "\n" +
					auctions.get(i).getOrganizationNam() + "\n" +
					auctions.get(i).getMonth() + "-" 
					+ auctions.get(i).getDayOfMonth() + "-" 
					+ auctions.get(i).getYear() + "\n" +
					auctions.get(i).getHour() + ":" + auctions.get(i).getMin());
	    }
		
		
		User currentUser = null;
		boolean flag = true;
		while (flag) {
			currentUser = login();
			if (currentUser != null) {flag = false;}
		}
		
		//TODO Instantiate DisplayCalendar
		// load auction into calendar
		
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
		if (userType.equals("Bidder")) {
			return new Bidder(username);
		}
		return null;
	}
	
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

}