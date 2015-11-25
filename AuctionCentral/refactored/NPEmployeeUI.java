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
	public void promptMainMenu(Scanner scanner, DisplayCalendar theCalendar) {
			
	}
	public Auction addAuction(Scanner scanner, DisplayCalendar theCalendar, String orgName) {
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
