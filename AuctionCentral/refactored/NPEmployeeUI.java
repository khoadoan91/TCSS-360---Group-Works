package refactored;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import refactored.DisplayCalendar.Exceed5AuctionsIn7Days;
import refactored.DisplayCalendar.Exceed90Days;
import refactored.DisplayCalendar.ExceedAuctionLimit;
import refactored.DisplayCalendar.ExceedAuctionLimitPerDay;
import refactored.DisplayCalendar.ExceedOneAuctionPerYear;

/**
 * Handles I/O for NPEmployees
 * @author Kyle Doan
 * @author nabilfadili
 *
 */
public class NPEmployeeUI implements UserUI {

	@Override
	public void promptMainMenu(BufferedReader reader, CalendarUI theCalendar, User currentUser) throws IOException, InterruptedException {
		Calendar timeRequested = Calendar.getInstance();
		boolean isQuit = false;
		do {
			System.out.println("Upcoming auctions: " + theCalendar.getDispCalendar().getUpcomingAuctions().size());
			System.out.println(theCalendar.displayCalendar(timeRequested));
			System.out.println("Please choose an option below.");
			System.out.println("1.  Add a new auction");
			System.out.println("2.  Edit my auction");
			System.out.println("3.  Remove my auction");
			System.out.println("4.  View my current auction");
			System.out.println("5.  View previous month.");
			System.out.println("6.  View next month.");
			System.out.println("7.  Exit");
			switch (Integer.parseInt(reader.readLine())) {
				case 1: addAuction(reader, theCalendar, (NPEmployee)currentUser); break;
				case 2: editAuction(reader, theCalendar, (NPEmployee)currentUser); break;
				case 3: removeAuction(theCalendar, (NPEmployee)currentUser); break;
				case 4: viewUserAuction(((NPEmployee)currentUser).getMyCurrentAuction()); break;
				case 5: timeRequested.set(Calendar.MONTH, timeRequested.get(Calendar.MONTH) - 1); break;
				case 6: timeRequested.set(Calendar.MONTH, timeRequested.get(Calendar.MONTH) + 1); break;
				case 7 :isQuit = true; break;
				default: System.err.println("Invalid choice"); Thread.sleep(10); break;
			}
		} while (!isQuit);
	}

	private void removeAuction(CalendarUI theCalendar, NPEmployee currentUser) {
		theCalendar.getDispCalendar().removeAuction(currentUser.getMyCurrentAuction());
		currentUser.removeAuction();
	}

	void viewUserAuction(final Auction theAuction) throws InterruptedException {
		if (theAuction != null) {
			String result = theAuction.getOrganizationName() + " "
						+ theAuction.getDateAuctionStarts().getTime() + "\n";
			char c = 'a';
			List<Item> list = theAuction.getAllItems();
			for (int i = 0; i < list.size(); i++) {
				result += c++ + ") " + list.get(i) + "\n";
			}
			System.out.println(result);
		} else {
			System.err.println("You do not have an auction scheduled.");
			Thread.sleep(10);
		}
	}

	private void editAuction(BufferedReader reader, CalendarUI theCalendar, NPEmployee currentUser) throws NumberFormatException, IOException, InterruptedException {
		if (currentUser.getMyCurrentAuction() != null) {
			System.out.println("What would you like to edit?");
			System.out.println("0) Go Back");
			System.out.println("1) Change the auction day");
			System.out.println("2) Change the auction time");
			System.out.println("3) Change the auction time duration");
			System.out.println("4) Change the list of your item");
			System.out.println("5) Add another item");
			switch (Integer.parseInt(reader.readLine())) {
				case 0: return;
				case 1: editAuctionDay(reader, theCalendar, (NPEmployee)currentUser); break;
				case 2: editAuctionStartTime(reader, theCalendar, (NPEmployee)currentUser); break;
				case 3: editAuctionDuration(reader, theCalendar, (NPEmployee)currentUser); break;
				case 4: editItem(reader, theCalendar, (NPEmployee)currentUser); break;
				case 5: promptAddItem(reader, (NPEmployee)currentUser); break;
				default: System.err.println("Invalid choice"); break;
			}
		}
		else {
			System.err.println("You do not have an auction");
		}
	}

	private void promptAddItem(BufferedReader reader, NPEmployee currentUser) throws IOException {
		System.out.println("Enter a new item");
		Item anotherItem = getItemFromPrompt(reader);
		currentUser.getMyCurrentAuction().addItem(anotherItem);
		System.out.println("You have sucessfully added a new item to your auction!");
	}

	private Item getItemFromPrompt(BufferedReader reader) throws IOException {
		String itemTitle = "", itemDesc = "";
		System.out.print("\nItem Title: ");
		itemTitle = reader.readLine();
		System.out.print("Item Quantity: ");
		int itemQt = Integer.parseInt(reader.readLine());
		System.out.print("Starting price: ");
		BigDecimal startPrice = new BigDecimal(reader.readLine());
		System.out.print("Description: ");
		itemDesc = reader.readLine();
		return new Item(itemTitle, itemQt, startPrice, itemDesc);
	}

	private void editAuctionDuration(BufferedReader reader, CalendarUI theCalendar, NPEmployee currentUser) throws IOException, InterruptedException {
		System.out.println("Change the time auction duration \"HH:MM\" ");

		String changedDur = reader.readLine();
		Auction backupAuction = currentUser.getMyCurrentAuction().clone();
		Auction changedAuction = currentUser.getMyCurrentAuction();
		changedAuction.setTimeDuration(changedDur);
		if (!theCalendar.getDispCalendar().changeAuction(backupAuction, changedAuction)) {
			currentUser.addAuction(backupAuction);
		}
		else {
			System.err.println("You sucessfully changed the time duration");
		}
		Thread.sleep(10);				//To prevent the two output streams from overlapping
	}

	private void editAuctionStartTime(BufferedReader reader, CalendarUI theCalendar, NPEmployee currentUser) throws IOException, InterruptedException {
		System.out.println("Change the time auction starts \"HH:MM\" ");

		String changedTime = reader.readLine();
		Auction backupAuction = currentUser.getMyCurrentAuction().clone();
		Auction changedAuction = currentUser.getMyCurrentAuction();
		changedAuction.setStartingTime(changedTime);
		if (!theCalendar.getDispCalendar().changeAuction(backupAuction, changedAuction)) {
			currentUser.addAuction(backupAuction);
		}
		else {
			System.err.println("You sucessfully changed the starting time");			
		}
		Thread.sleep(10);			//To prevent the two output streams from overlapping
	}

	private void editAuctionDay(BufferedReader reader, CalendarUI theCalendar, NPEmployee currentUser) throws IOException, InterruptedException {
		System.out.println("Change day to \"YYYY MM DD\": ");
		String[] date = reader.readLine().split(" ");

		Auction backupAuction = currentUser.getMyCurrentAuction().clone();
		Auction changedAuction = currentUser.getMyCurrentAuction();
		changedAuction.setYear(Integer.parseInt(date[0]));
		changedAuction.setMonth(Integer.parseInt(date[1]));
		changedAuction.setDateOfMonth(Integer.parseInt(date[2]));
		if (!theCalendar.getDispCalendar().changeAuction(backupAuction, changedAuction)) {
			currentUser.addAuction(backupAuction);
		}
		else {
			System.err.println("You sucessfully changed the auction date");		
		}
		Thread.sleep(10);			//To prevent the two output streams from overlapping
	}

	private void editItem(BufferedReader reader, CalendarUI theCalendar, NPEmployee currentUser) throws NumberFormatException, IOException, InterruptedException {
		List<Item> items = currentUser.getMyCurrentAuction().getAllItems();
		System.out.println("Here are all items:");
		for (int i = 0; i < items.size(); i++) {
			System.out.println((i + 1) + ") " + items.get(i));
		}
		System.out.println("Choose an item to edit or hit 0 to go back");
		int pickItem = Integer.parseInt(reader.readLine());
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
				switch (Integer.parseInt(reader.readLine())) {
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
						items.get(i - 1).setQuantity(Integer.parseInt(reader.readLine())); break;
					case 3:
						System.out.print("New Starting Price: ");
						items.get(i - 1).setStartingPrice(new BigDecimal(reader.readLine())); break;
					case 4:
						System.out.print("New Description : ");
						try {
							items.get(i - 1).setDescription(reader.readLine());
						} catch (IOException e) {
							e.printStackTrace();
						} break;
					case 5:
						System.err.println("Item was removed!");
						Thread.sleep(10);					//To prevent the two output streams from overlapping
						currentUser.getMyCurrentAuction().removeItem(items.remove(i - 1)); break;
					default: break;
				}
			}
		}
	}

	private void addAuction(BufferedReader reader, CalendarUI theCalendar, NPEmployee currentUser) throws IOException, InterruptedException {
		if (currentUser.getMyCurrentAuction() != null) {
			System.out.println("You already have an auction scheduled.");
		} else {
			currentUser.addAuction(enterAuctionInfo(reader, currentUser));
			try {
				theCalendar.getDispCalendar().addAuction(currentUser.getMyCurrentAuction());

				System.err.println("Your auction has been sucessfully scheduled");
				Thread.sleep(10);				//To prevent the two output streams from overlapping
				System.out.print("\nHow many items: ");
				int itemCount = Integer.parseInt(reader.readLine());
				for (int i = 0; i < itemCount; i++) {
					currentUser.getMyCurrentAuction().addItem(getItemFromPrompt(reader));
				}
			} catch (ExceedAuctionLimit | Exceed90Days | Exceed5AuctionsIn7Days |
					ExceedAuctionLimitPerDay | ExceedOneAuctionPerYear e) {
				System.err.println(e.getMessage());
				Thread.sleep(10);				//To prevent the two output streams from overlapping
				currentUser.removeAuction();    // remove the violation BS auction.
			}
		}
	}

	private Auction enterAuctionInfo(BufferedReader reader, NPEmployee currentUser) throws IOException {
		int year, month, date, hour, min;
		Calendar tempCal = Calendar.getInstance();
		tempCal.clear();
		System.out.print("Which day? In format: \"YYYY MM DD\" ");
		String[] aucDate = reader.readLine().split(" ");
		year = Integer.parseInt(aucDate[0]);
		month = Integer.parseInt(aucDate[1]);
		date = Integer.parseInt(aucDate[2]);
		System.out.print("Hour and Minute? In format: \"HH:MM\" ");
		String[] time = reader.readLine().split(":");
		hour = Integer.parseInt(time[0]);
		min = Integer.parseInt(time[1]);
		System.out.print("How long for the Auction: \"HH:MM\" ");
		String timeDur = reader.readLine();
		tempCal.set(year, month, date, hour, min);
		return new Auction(currentUser.getMyOrgName(), new LinkedList<>(), tempCal, timeDur);
	}

}
