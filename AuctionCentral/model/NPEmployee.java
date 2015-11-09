
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Calendar;
/**
 * @author nabilfadili
 *
 */
public class NPEmployee extends User {

	
	public NPEmployee(String username) {
		super(username);
	}
	
	public void addAuction() {
		Scanner scanner = new Scanner(System.in);
		int year, month, date, hour, min;
		Calendar tempCal = Calendar.getInstance();
		tempCal.clear();
		
		int itemCount;
		String itemTitl, itemDesc;
		int itemQt;
		List<Item> itemList = new ArrayList<>();
		
		
		System.out.print("Which day? In format: \"YYYY MM DD\"\n");
		year = scanner.nextInt();
		month = scanner.nextInt();
		date = scanner.nextInt();
		System.out.print("Hour and Minute? In format: \"Hour Min\"\n");
		hour = scanner.nextInt();
		min = scanner.nextInt();
		
		System.out.println(year + " " + month + " " + date);
		tempCal.set(year, month, date, hour, min);
		
		//TODO check if the date is available in the calendar
		
		System.out.print("How many items: ");
		itemCount = scanner.nextInt();
		
		for(int i = 0; i < itemCount; i++) {
			System.out.print("Item Title: ");
			itemTitl = scanner.next();
			System.out.print("Item Quantity: ");
			itemQt = scanner.nextInt();
			System.out.println("Description:");
			
			//TODO Only reads a single token. Figure out a way to read an entire string using Scanner
			itemDesc = scanner.next();							
			itemList.add(new Item(itemTitl, itemQt, itemDesc));
		}
		Auction newAuction = new Auction(itemList, tempCal.getTime(), hour);
		
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
	
	public static void main(String[] args) {
		NPEmployee npe = new NPEmployee("Kyle");
		npe.addAuction();
	}
}
