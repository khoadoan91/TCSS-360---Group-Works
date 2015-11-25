/**
 * 
 */
package refactored;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import old.User;

/**
 * @author nabilfadili
 *
 */
public class ACEmployeeUI implements UserUI {

	/**
	 * Accesses calendar singleton and prints upcoming auctions.
	 * 
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
	 * Runs main menu and prompts for an ACEmployee.
	 */
	@Override
	public void promptMainMenu(Scanner scanner, DisplayCalendar theCalendar) {
		boolean isQuit = false;
		do {
			System.out.println("Please choose an option below or type any other number to exit.");
			System.out.println("1.  View the calendar");
			System.out.println("2.  View upcoming auction");
			switch (scanner.nextInt()) {
				case 1: viewCalendar(scanner, theCalendar); break;
				case 2: viewUpcomingAuction(scanner, theCalendar); break;
				default: isQuit = true; break;
			}
		} while (!isQuit);	
	}
	
	/**
	 * Displays calendar of auctions for current month. User is able to go forward and backward 
	 * an unlimited number of months.
	 * @param scanner for menu choices
	 * @param theCalendar holds Auction objects
	 */
	private void viewCalendar(Scanner scanner, DisplayCalendar theCalendar) {
		Calendar timeRequested = Calendar.getInstance();
		boolean isQuit = false;
		do {
			System.out.println(theCalendar.displayCalendar(timeRequested));
			System.out.println("1.  Previous month\n" +
							   "2.  Next month\n" +
							   "3.  Back to main menu");
			switch (scanner.nextInt()) {
			case 1: timeRequested.set(Calendar.MONTH, timeRequested.get(Calendar.MONTH) - 1); break;
			case 2: timeRequested.set(Calendar.MONTH, timeRequested.get(Calendar.MONTH) + 1); break;
			case 3: isQuit = true; break;
			default: System.out.println("Invalid input"); break;
			}
		} while(!isQuit);	
	}

}
