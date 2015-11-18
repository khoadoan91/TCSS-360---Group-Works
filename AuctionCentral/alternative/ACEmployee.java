package alternative;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import model.DisplayCalendar;
import model.Auction;

/**
 * @author nabilfadili
 *
 */
public class ACEmployee implements User {
	
//	final private String userType = "ACEmployee";
//	private DisplayCalendar myCalendar;

	// TODO fix constructor to take the calendar from main
//	public ACEmployee(DisplayCalendar theCalendar) {
//		myCalendar = theCalendar; 
//	}
	
//	@Deprecated
//	public ACEmployee(String username) {
//		super(username);
//	}
	
	/**
	 * Accesses calendar singleton and returns a list of upcoming auctions.
	 * !!!Should this return a list or simply print the list?? !!!This would
	 * mean changing the return type to void and printing from this method.
	 * 
	 * @return
	 */
	private void viewUpcomingAuction(Scanner scanner, DisplayCalendar cal) {
		System.out.println("Please pick one auction for detail. ");
		List<Auction> upcomingAuc = cal.getUpcomingAuctions();
		for (int i = 0; i < upcomingAuc.size(); i++) {
			System.out.println((i + 1) + ") " + upcomingAuc.get(i));
		}
		int pickAuc = scanner.nextInt();
		for (int i = 1; i <= upcomingAuc.size(); i++) {
			if (i == pickAuc) System.out.println(upcomingAuc.get(i - 1).displayAuction());
		}
	}

	/**
	 * Prints details of an Auction object. !!!Would need to access the calendar
	 * singleton and get an Auction object directly !!!from it. Or call
	 * viewAuctionList() and then iterate through the list till we find !!!the
	 * correct auction.
	 * 
	 * @param auctionID
	 */
//	public void selectAuction(Scanner scanner) {
//		// TODO view details of an auction object, including list of items
//	
//	}

//	public String getUserType() {
//		return userType;
//	}
	private void viewCalendar(Scanner scanner, DisplayCalendar cal) {
		boolean isBack = false;
		Calendar timeRequested = Calendar.getInstance();
		do {
			System.out.println(cal.displayCalendar(timeRequested));
			System.out.println("1.  View next month.");
			System.out.println("2.  View last month.");
			System.out.print("------Done!! want to go back? type any number ");
			switch (scanner.nextInt()) {
				case 1: timeRequested.set(Calendar.MONTH, timeRequested.get(Calendar.MONTH) + 1); break;
				case 2: timeRequested.set(Calendar.MONTH, timeRequested.get(Calendar.MONTH) - 1); break;
				default: isBack = true; break;
			}
		} while (!isBack);
	}

	@Override
	public void run(Scanner scanner, DisplayCalendar cal) {
		boolean isQuit = false;
		do {
			System.out.println("Please choose an option below.");
			System.out.println("1.  View a calendar");
			System.out.println("2.  View upcoming auction");
			System.out.print("------Done!! want to exit? type any number ");
			switch (scanner.nextInt()) {
				case 1: viewCalendar(scanner, cal); break;
				case 2: viewUpcomingAuction(scanner, cal); break;
				default: isQuit = true; break;
			}
		} while (!isQuit);
	}

}
