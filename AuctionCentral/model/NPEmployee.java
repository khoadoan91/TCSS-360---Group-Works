
package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Calendar;

/**
 * @author nabilfadili
 *
 */
public class NPEmployee extends User {

	final private String userType = "NPEmployee";

	private String myOrgName;
	private Auction myAuction;
	private DisplayCalendar myCalendar;
	private Scanner scan;
	
	// add myAuction from the calendar singleton
	public NPEmployee(String username, DisplayCalendar myCalendar) {
		super(username, myCalendar);
	}
	 
	@Deprecated
	public NPEmployee(String username) {
		this(username, "", new DisplayCalendar());
	}
	
	public NPEmployee(String username, String orgName, DisplayCalendar cal) {
		super(username, cal);
		myOrgName = orgName;
		myCalendar = cal;
		//FIXME What is this for????
		//scan = new Scanner(System.in);
	}

	public void addAuction() {
		int year, month, date, hour, min;
		Calendar tempCal = Calendar.getInstance();
		tempCal.clear();
		int itemCount;
		String itemTitl = "", itemDesc = "";
		int itemQt;
		List<Item> itemList = new ArrayList<>();

		System.out.print("Which day? In format: \"YYYY MM DD\" ");
		year = scan.nextInt();
		month = scan.nextInt();
		date = scan.nextInt();
		System.out.print("Hour and Minute? In format: \"Hour Min\" ");
		hour = scan.nextInt();
		min = scan.nextInt();
		System.out.print("How long for the Auction: \"HH:MM\" ");
		String timeDur = scan.next();
		tempCal.set(year, month, date, hour, min);

		System.out.print("How many items: ");
		itemCount = scan.nextInt();

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < itemCount; i++) {
			System.out.print("\nItem Title: ");
			try {
				itemTitl = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.print("Item Quantity: ");
			itemQt = scan.nextInt();
			System.out.print("Starting price: ");
			BigDecimal startPrice = new BigDecimal(scan.next());
			System.out.print("Description: ");
			try {
				itemDesc = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			itemList.add(new Item(itemTitl, itemQt, startPrice, itemDesc));
		}
		myAuction = new Auction(myOrgName, itemList, tempCal, timeDur);

		myCalendar.addAuction(myAuction);
	}

	public void editAuction() {
		System.out.println("Edit your auction. Choose your options");
		System.out.println("1) Change the auction day");
		System.out.println("2) Change the auction time");
		System.out.println("3) Change the auction time duration");
		System.out.println("4) Change the list of your item");
		switch (scan.nextInt()) {
			case 1: editAuctionDay(); break;
			case 2: editAuctionStartTime(); break;
			case 3: editAuctionEndTime(); break;
			case 4: editItem(); break;
		}
	}
	
	private void editItem() {
		List<Item> items = myAuction.getAllItems();
		System.out.println("Here is all items");
		for (int i = 0; i < items.size(); i++) {
			System.out.println((i + 1) + ") " + items.get(i));
		}
		System.out.println("Choose an item to edit");
		int pickItem = scan.nextInt();
		for (int i = 1; i <= items.size(); i++) {
			if (i == pickItem) {
				System.out.println("Choose your edit.");
				System.out.println("1. Edit Title");
				System.out.println("2. Edit Quantity");
				System.out.println("1. Edit Description");
				switch (scan.nextInt()) {
					case 1: 
						System.out.print("New Title : ");
						items.get(i - 1).setTitle(scan.next()); break;
					case 2: 
						System.out.print("New Quantity : ");
						items.get(i - 1).setQuantity(scan.nextInt()); break;
					case 3: 
						System.out.print("New Description : ");
						items.get(i - 1).setDescription(scan.next()); break;
				}
			}
		}
	}

	private void editAuctionEndTime() {
		System.out.println("Change the time auction ends \"HH:MM\" ");
		myAuction.setTimeDuration(scan.next());
	}

	private void editAuctionStartTime() {
		System.out.println("Change the time auction starts \"HH:MM\" ");
		myAuction.setStartingTime(scan.next());
	}

	private void editAuctionDay() {
		System.out.println("Change day to \"YYYY MM DD\": ");
		myAuction.setYear(scan.nextInt());
		myAuction.setMonth(scan.nextInt());
		myAuction.setDate(scan.nextInt());
	}

	public void removeAuction() {
		
	}

	public String getUserType() {
		return userType;
	}

	public static void main(String[] args) {
		DisplayCalendar cal = new DisplayCalendar();
		NPEmployee testUser = new NPEmployee("NPEmployee", "GOODWILL", cal);
		testUser.addAuction();
		testUser.editAuction();
	}
}
