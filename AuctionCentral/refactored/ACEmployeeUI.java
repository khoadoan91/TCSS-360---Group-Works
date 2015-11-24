/**
 * 
 */
package refactored;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import current.User;

/**
 * @author nabilfadili
 *
 */
public class ACEmployeeUI implements UserUI {

	/**
	 * Accesses calendar singleton and prints upcoming auctions.
	 * 
	 */
	public void viewUpcomingAuction(Scanner scanner, DisplayCalendar cal) {
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
	public void promptMainMenu(Scanner scanner, DisplayCalendar cal) {
		boolean isQuit = false;
		do {
			// TODO view next month, view past auction
			System.out.println("Please choose an option below.");
			System.out.println("1.  View a calendar");
			System.out.println("2.  View upcoming auction");
			System.out.print("------Done!! want to exit? type any number ");
			switch (scanner.nextInt()) {
				case 1: System.out.println(cal); break;
				case 2: viewUpcomingAuction(scanner, cal); break;
				default: isQuit = true; break;
			}
		} while (!isQuit);	
	}

}
