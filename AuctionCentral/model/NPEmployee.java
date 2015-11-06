
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author nabilfadili
 *
 */
public class NPEmployee extends User {

	
	public NPEmployee(String username) {
		super(username);
	}
	
	public void addAuction(DisplayCalendar myCalendar) {
		Scanner scanner = new Scanner(System.in);
		String myDate;
		int myHour;
		
		int itemCount;
		String myTitle, myDesc;
		int myQuantity;
		List<Item> itemList = new ArrayList<>();
		
		
		System.out.println("Date:");
		myDate = scanner.nextLine();
		System.out.println("Hour:");
		myHour = scanner.nextInt();
		
		//TODO check if the date is available in the calendar
		
		System.out.println("How many items:");
		itemCount = scanner.nextInt();
		
		for(int i = 0; i < itemCount; ++i) {
			System.out.println("Item Title:");
			myTitle = scanner.nextLine();
			System.out.println("Item Quantity: ");
			myQuantity = scanner.nextInt();
			System.out.println("Description:");
			myDesc = scanner.nextLine(); 
			itemList.add(new Item(myTitle, myQuantity, myDesc));
		}
		Auction newAuction = new Auction(itemList, myDate, myHour);
		
		//TODO add this auction into the calendar
		
	}
	public void editAuction() {
		
		
	}
	public void removeAuction() {
		
	}
	
	/**
	 * Method for testing
	 */
	public void printPermissions() {
		System.out.println("I can schedule an auction, edit an auction, "
				+ "or remove an auction.");
	}
}
