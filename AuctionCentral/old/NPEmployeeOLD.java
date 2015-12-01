
package old;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import refactored.Auction;
import refactored.DisplayCalendar;
import refactored.Item;

import java.util.Calendar;

/**
 * @author nabilfadili
 * @author KyleD
 */
public class NPEmployeeOLD implements UserOLD {


	private String myOrgName;
	private Auction myAuction;
	
	public NPEmployeeOLD(final String orgName, final Auction theAuc) {
		myOrgName = orgName;
		myAuction = theAuc;
	}
	
	// This method is used for reading the input file.
	public void setAuction(final Auction theAuction) {
		myAuction = theAuction;
	}

	public void addAuction(Scanner scanner, DisplayCalendar cal) {
		if (myAuction == null) {
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
			int bsrule = cal.addAuction(myAuction);
			switch (bsrule) {
				case 0: System.out.println("You sucessfully added your auction!"); break;
				case 1: System.out.println("Sorry! We reach the maximum 25 auctions."); break;
				case 2: System.out.println("Your auction may not be scheduled more than"
								+ " 90 days from the current date."); break;
				case 3: System.out.println("No more than 5 auctions may be scheduled for "
									+ "any rolling 7 day period."); break;
				case 4: System.out.println("No more than 2 auctions can be scheduled on "
								+ "the same day, and the start time of the second can be "
								+ "no earlier than 2 hours after the end time of the first"); break;
				case 5: System.out.println("No more than one auction per year per "
								+ "Non-profit organization can be scheduled."); break;
			}
			if (bsrule != 0) myAuction = null;    // remove the violation BS auction.
		} else {
			System.out.println("You've already had an auction");
		}
	}

	public void editAuction(Scanner scanner) {
		if (myAuction != null) {
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
		else {
			System.out.println("You do not have an auction");
		}
	}
	
	private void editItem(Scanner scanner) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
					try {
						items.get(i - 1).setTitle(reader.readLine());
					} catch (IOException e) {
						e.printStackTrace();
					} break;
					case 2: 
						System.out.print("New Quantity : ");
						items.get(i - 1).setQuantity(scanner.nextInt()); break;
					case 3:
						System.out.print("New Starting Price: ");
						items.get(i - 1).setStartingPrice(new BigDecimal(scanner.next())); break;
					case 4: 
						System.out.print("New Description : ");
					try {
						items.get(i - 1).setDescription(reader.readLine());
					} catch (IOException e) {
						e.printStackTrace();
					} break;
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
		myAuction.setMonth(scanner.nextInt() - 1);
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

	@Override
	public void run(Scanner scanner, DisplayCalendar cal) {
		Calendar timeRequested = Calendar.getInstance();
		boolean isQuit = false;
		do {
			System.out.println(cal.displayCalendar(timeRequested));
			System.out.println("Please choose an option below.");
			System.out.println("1.  Add a new auction");
			System.out.println("2.  Edit my auction");
			System.out.println("3.  Remove my auction");
			System.out.println("4.  View my current auction");
			System.out.println("5.  View next month.");
			System.out.println("6.  View last month.");
			System.out.print("------Done?? want to exit? type other number ");
			switch (scanner.nextInt()) {
				case 1: addAuction(scanner, cal); break;
				case 2: editAuction(scanner); break;
				case 3: removeAuction(cal); break;
				case 4: viewMyAuction(); break;
				case 5: timeRequested.set(Calendar.MONTH, timeRequested.get(Calendar.MONTH) + 1); break;
				case 6: timeRequested.set(Calendar.MONTH, timeRequested.get(Calendar.MONTH) - 1); break;
				default: isQuit = true; break;
			}
		} while (!isQuit);
		exit(scanner, cal);
	}
	
	private void exit(Scanner scanner, DisplayCalendar cal) {
		PrintWriter auctionWriter = null, itemWriter = null;
		try {
			auctionWriter = new PrintWriter("current_auction_list.txt", "UTF-8");
			itemWriter = new PrintWriter("current_item_list.txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}		
		for (int i = 0; i < cal.getPastAuctions().size(); ++i) {
			auctionWriter.println(cal.getPastAuctions().get(i).toStringTextFile());
			for (int j = 0; j < cal.getPastAuctions().get(i).getAllItems().size(); ++j) {
				itemWriter.println(cal.getPastAuctions().get(i).getAllItems().get(j).toStringTextFile());
			}
		}
		for (int i = 0; i < cal.getUpcomingAuctions().size(); ++i) {
			auctionWriter.println(cal.getUpcomingAuctions().get(i).toStringTextFile());
			for (int j = 0; j < cal.getUpcomingAuctions().get(i).getAllItems().size(); ++j) {
				itemWriter.println(cal.getUpcomingAuctions().get(i).getAllItems().get(j).toStringTextFile());
			}
		}
		auctionWriter.close();
		itemWriter.close();
	}
}
