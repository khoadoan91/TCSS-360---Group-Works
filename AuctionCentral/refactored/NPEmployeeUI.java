package refactored;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class NPEmployeeUI implements UserUI {

	@Override
	public void promptMainMenu(Scanner scanner, CalendarUI theCalendar, User currentUser) {
		Calendar timeRequested = Calendar.getInstance();
		boolean isQuit = false;
		do {
			System.out.println(theCalendar.displayCalendar(timeRequested));
			System.out.println("Please choose an option below.");
			System.out.println("1.  Add a new auction");
			System.out.println("2.  Edit my auction");
			System.out.println("3.  Remove my auction");
			System.out.println("4.  View my current auction");
			System.out.println("5.  View previous month.");
			System.out.println("6.  View next month.");
			System.out.println("7.  Exit");
			switch (scanner.nextInt()) {
				case 1: addAuction(scanner, theCalendar, (NPEmployee)currentUser); break;
				case 2: editAuction(scanner, theCalendar, (NPEmployee)currentUser); break;
				case 3: removeAuction(theCalendar, (NPEmployee)currentUser); break;
				case 4: viewUserAuction((NPEmployee)currentUser); break;
				case 5: timeRequested.set(Calendar.MONTH, timeRequested.get(Calendar.MONTH) - 1); break;
				case 6: timeRequested.set(Calendar.MONTH, timeRequested.get(Calendar.MONTH) + 1); break;
				case 7 :isQuit = true; break;
				default: System.out.println("Invalid choice"); break;
			}
		} while (!isQuit);
	}
	
	private void removeAuction(CalendarUI theCalendar, NPEmployee currentUser) {
		theCalendar.getDispCalendar().removeAuction(currentUser.getMyAuction());
		currentUser.removeAuction();
	}
	
	public void viewUserAuction(NPEmployee currentUser) {
		System.out.println(currentUser.viewAuction());
	}
	
	private void editAuction(Scanner scanner, CalendarUI theCalendar, NPEmployee currentUser) {
		if (currentUser.getMyAuction() != null) {
			System.out.println("What would you like to edit?");
			System.out.println("0) Go Back");
			System.out.println("1) Change the auction day");
			System.out.println("2) Change the auction time");
			System.out.println("3) Change the auction time duration");
			System.out.println("4) Change the list of your item");
			switch (scanner.nextInt()) {
				case 0: return;
				case 1: editAuctionDay(scanner, theCalendar, (NPEmployee)currentUser); break;
				case 2: editAuctionStartTime(scanner, theCalendar, (NPEmployee)currentUser); break;
				case 3: editAuctionDuration(scanner, theCalendar, (NPEmployee)currentUser); break;
				case 4: editItem(scanner, theCalendar, (NPEmployee)currentUser); break;
				default: System.out.println("Invalid choice"); break;
			}
		}
		else {
			System.out.println("You do not have an auction");
		}
	}
	
	private void editAuctionDuration(Scanner scanner, CalendarUI theCalendar, NPEmployee currentUser) {
		System.out.println("Change the time auction duration \"HH:MM\" ");
		currentUser.getMyAuction().setTimeDuration(scanner.next());
		// FIXME check 4th business rule
	}

	private void editAuctionStartTime(Scanner scanner, CalendarUI theCalendar, NPEmployee currentUser) {
		System.out.println("Change the time auction starts \"HH:MM\" ");
		currentUser.getMyAuction().setStartingTime(scanner.next());
		// FIXME check 4th business rule.
	}

	private void editAuctionDay(Scanner scanner, CalendarUI theCalendar, NPEmployee currentUser) {
		System.out.println("Change day to \"YYYY MM DD\": ");
		// FIXME check 2nd, 3rd and 4th business rules.
		currentUser.getMyAuction().setYear(scanner.nextInt());
		currentUser.getMyAuction().setMonth(scanner.nextInt() - 1);
		currentUser.getMyAuction().setDate(scanner.nextInt());
	}
	
	private void editItem(Scanner scanner, CalendarUI theCalendar, NPEmployee currentUser) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		List<Item> items = currentUser.getMyAuction().getAllItems();
		System.out.println("Here is all items");
		for (int i = 0; i < items.size(); i++) {
			System.out.println((i + 1) + ") " + items.get(i));
		}
		System.out.println("Choose an item to edit or hit 0 to go back");
		int pickItem = scanner.nextInt();
		if (pickItem == 0) return;
		for (int i = 1; i <= items.size(); i++) {
			if (i == pickItem) {
				System.out.println("Choose your edit.");
				System.out.println("0. Go back");
				System.out.println("1. Edit Title");
				System.out.println("2. Edit Quantity");
				System.out.println("3. Edit Starting Price");
				System.out.println("4. Edit Description");
				System.out.println("5. Remove an item");
				switch (scanner.nextInt()) {
					case 0: return;
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
						currentUser.getMyAuction().removeItem(items.remove(i - 1)); break;
					default: break;
				}
			}
		}
	}
	
	private void addAuction(Scanner scanner, CalendarUI theCalendar, NPEmployee currentUser) {
		if (currentUser.getMyAuction() != null) {
			System.out.println("You already have an auction scheduled.");
		} else {
			currentUser.addAuction(enterAuctionInfo(scanner, currentUser.getMyOrgName()));
			int bsrule = theCalendar.getDispCalendar().addAuction(currentUser.getMyAuction());
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
			if (bsrule != 0) currentUser.removeAuction();    // remove the violation BS auction.
		}
	}
	
	private Auction enterAuctionInfo(Scanner scanner, String orgName) {
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
		return new Auction(orgName, itemList, tempCal, timeDur);
	}

}
