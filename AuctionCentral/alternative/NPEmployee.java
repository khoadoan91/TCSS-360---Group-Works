
package alternative;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Calendar;

import model.Auction;
import model.DisplayCalendar;
import model.Item;

/**
 * @author nabilfadili
 * @author KyleD
 */
public class NPEmployee implements User {

//	final private String userType = "NPEmployee";

	private String myOrgName;
	private Auction myAuction;
//	private DisplayCalendar myCalendar;  
	
//	// add myAuction from the calendar singleton
//	public NPEmployee(String username, DisplayCalendar myCalendar) {
//		super(username, myCalendar);
//	}
//	
//	@Deprecated
//	public NPEmployee(String username) {
//		this(username, "", new DisplayCalendar());
//	}
	
	public NPEmployee(final String orgName, final Auction theAuc) {
		myOrgName = orgName;
		myAuction = theAuc;
	}
	
	// This method is used for reading the input file.
	public void setAuction(final Auction theAuction) {
		myAuction = theAuction;
	}

	public void addAuction(Scanner scanner, DisplayCalendar cal) {
		int year, month, date, hour, min;
		Calendar tempCal = Calendar.getInstance();
		tempCal.clear();
		int itemCount;
		String itemTitl = "", itemDesc = "";
		int itemQt;
		List<Item> itemList = new ArrayList<>();

		System.out.print("Which day? In format: \"YYYY MM DD\" ");
		year = scanner.nextInt();
		month = scanner.nextInt();
		date = scanner.nextInt();
		System.out.print("Hour and Minute? In format: \"HH:MM\" ");
		String[] time = scanner.next().split(":");
		hour = Integer.parseInt(time[0]);
		min = Integer.parseInt(time[1]);
		System.out.print("How long for the Auction: \"HH:MM\" ");
		String timeDur = scanner.next();
		tempCal.set(year, month, date, hour, min);
		System.out.print("How many items: ");
		itemCount = scanner.nextInt();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < itemCount; i++) {
			System.out.print("\nItem Title: ");
			try {
				itemTitl = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.print("Item Quantity: ");
			itemQt = scanner.nextInt();
			System.out.print("Starting price: ");
			BigDecimal startPrice = new BigDecimal(scanner.next());
			System.out.print("Description: ");
			try {
				itemDesc = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			itemList.add(new Item(itemTitl, itemQt, startPrice, itemDesc));
		}
		myAuction = new Auction(myOrgName, itemList, tempCal, timeDur);
		boolean isSucess = cal.addAuction(myAuction);
		if (!isSucess) {
			myAuction = null;
			System.out.println("Oops!! We are not allowed to add your auction!");
		} else System.out.println("You sucessfully add your auction!");
	}

	public void editAuction(Scanner scanner) {
		System.out.println("Edit your auction. Choose your options");
		System.out.println("1) Change the auction day");
		System.out.println("2) Change the auction time");
		System.out.println("3) Change the auction time duration");
		System.out.println("4) Change the list of your item");
		switch (scanner.nextInt()) {
			case 1: editAuctionDay(scanner); break;
			case 2: editAuctionStartTime(scanner); break;
			case 3: editAuctionDuration(scanner); break;
			case 4: editItem(scanner); break;
		}
	}
	
	private void editItem(Scanner scanner) {
		List<Item> items = myAuction.getAllItems();
		System.out.println("Here is all items");
		for (int i = 0; i < items.size(); i++) {
			System.out.println((i + 1) + ") " + items.get(i));
		}
		System.out.println("Choose an item to edit");
		int pickItem = scanner.nextInt();
		for (int i = 1; i <= items.size(); i++) {
			if (i == pickItem) {
				System.out.println("Choose your edit.");
				System.out.println("1. Edit Title");
				System.out.println("2. Edit Quantity");
				System.out.println("3. Edit Starting Price");
				System.out.println("4. Edit Description");
				System.out.println("5. Remove an item");
				switch (scanner.nextInt()) {
					case 1: 
						System.out.print("New Title : ");
						items.get(i - 1).setTitle(scanner.next()); break;
					case 2: 
						System.out.print("New Quantity : ");
						items.get(i - 1).setQuantity(scanner.nextInt()); break;
					case 3:
						System.out.print("New Starting Price: ");
						items.get(i - 1).setStartingPrice(new BigDecimal(scanner.next())); break;
					case 4: 
						System.out.print("New Description : ");
						items.get(i - 1).setDescription(scanner.next()); break;
					case 5:
						System.out.println("Item was removed!");
						myAuction.removeItem(items.remove(i - 1).getTitle()); break;
					default: break;
				}
			}
		}
	}

	private void editAuctionDuration(Scanner scanner) {
		System.out.println("Change the time auction duration \"HH:MM\" ");
		myAuction.setTimeDuration(scanner.next());
	}

	private void editAuctionStartTime(Scanner scanner) {
		System.out.println("Change the time auction starts \"HH:MM\" ");
		myAuction.setStartingTime(scanner.next());
	}

	private void editAuctionDay(Scanner scanner) {
		System.out.println("Change day to \"YYYY MM DD\": ");
		myAuction.setYear(scanner.nextInt());
		myAuction.setMonth(scanner.nextInt());
		myAuction.setDate(scanner.nextInt());
	}

	public void removeAuction(final DisplayCalendar cal) {
		cal.removeAuction(myAuction);
		myAuction = null;
	}
	
	public void viewMyAuction() {
		if (myAuction == null) {
			System.out.println("Oops, you don't have any auction.");
		} else {
			System.out.println(myAuction);
		}
	}

//	public String getUserType() {
//		return userType;
//	}

//	public static void main(String[] args) {
//		NPEmployee testUser = new NPEmployee("Goodwill", new DisplayCalendar());
//		testUser.run(new Scanner(System.in));
//	}

	@Override
	public void run(Scanner scanner, DisplayCalendar cal) {
		boolean isQuit = false;
		do {
			System.out.println(cal);
			System.out.println("Please choose an option below.");
			System.out.println("1.  Add a new auction");
			System.out.println("2.  Edit my auction");
			System.out.println("3.  Remove my auction");
			System.out.println("4.  View my current auction");
			System.out.print("------Done?? want to exit? type other number ");
			switch (scanner.nextInt()) {
				case 1: addAuction(scanner, cal); break;
				case 2: editAuction(scanner); break;
				case 3: removeAuction(cal); break;
				case 4: viewMyAuction(); break;
				default: isQuit = true; break;
			}
		} while (!isQuit);
	}
}
