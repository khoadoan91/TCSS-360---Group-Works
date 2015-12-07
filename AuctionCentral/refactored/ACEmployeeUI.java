/**
 *
 */
package refactored;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

/**
 * @author nabilfadili
 *
 */
public class ACEmployeeUI implements UserUI {

	/**
	 * Accesses calendar singleton and prints upcoming auctions.
	 * @throws IOException
	 * @throws NumberFormatException
	 *
	 */
	private void viewUpcomingAuction(BufferedReader reader, CalendarUI cal) throws NumberFormatException, IOException {
		System.out.println("Please pick one auction for detail. ");
		List<Auction> upcomingAuc = cal.getDispCalendar().getUpcomingAuctions();
		for (int i = 0; i < upcomingAuc.size(); i++) {
			System.out.println((i + 1) + ") " + upcomingAuc.get(i));
		}
		System.out.println("\n Hit 0 to go back to main menu");
		int pickAuc = Integer.parseInt(reader.readLine());
		if (pickAuc == 0) return;
		for (int i = 1; i <= upcomingAuc.size(); i++) {
			if (i == pickAuc) System.out.println(displayItemsInAuction(upcomingAuc.get(i - 1)));
		}
	}

	String displayItemsInAuction(final Auction theAuction) {
		String result = theAuction.getOrganizationName() + " "
					+ theAuction.getDateAuctionStarts().getTime() + "\n";
		char c = 'a';
		List<Item> list = theAuction.getAllItems();
		for (int i = 0; i < list.size(); i++) {
			result += c++ + ") " + list.get(i) + "\n";
		}
		return result;
	}

	/**
	 * Runs main menu and prompts for an ACEmployee.
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	@Override
	public void promptMainMenu(BufferedReader reader, CalendarUI theCalendar, User currentUser) throws NumberFormatException, IOException {
		boolean isQuit = false;
		do {
			System.out.println("Please choose an option below or type any other number to exit.");
			System.out.println("1.  View the calendar");
			System.out.println("2.  View the details of an upcoming auction");
			System.out.println("3.  Exit");
			switch (Integer.parseInt(reader.readLine())) {
				case 1: viewCalendar(reader, theCalendar); break;
				case 2: viewUpcomingAuction(reader, theCalendar); break;
				case 3: isQuit = true; break;
				default: System.out.println("Invalid input.");
			}
		} while (!isQuit);
	}

	/**
	 * Displays calendar of auctions for current month. User is able to go forward and backward
	 * an unlimited number of months.
	 * @param scanner for menu choices
	 * @param theCalendar holds Auction objects
	 * @throws IOException
	 */
	private void viewCalendar(BufferedReader reader, CalendarUI theCalendar) throws IOException {
		Calendar timeRequested = Calendar.getInstance();
		boolean isQuit = false;
		do {
			System.out.println("Upcoming auctions: " + theCalendar.getDispCalendar().getUpcomingAuctions().size());
			System.out.println("Past auctions: " + theCalendar.getDispCalendar().getPastAuctions().size());
			System.out.println(theCalendar.displayCalendar(timeRequested));
			System.out.println("0.  Back to main menu\n" +
							   "1.  Previous month\n" +
							   "2.  Next month");
			switch (Integer.parseInt(reader.readLine())) {
				case 0: isQuit = true; break;
				case 1: timeRequested.set(Calendar.MONTH, timeRequested.get(Calendar.MONTH) - 1); break;
				case 2: timeRequested.set(Calendar.MONTH, timeRequested.get(Calendar.MONTH) + 1); break;
				default: System.out.println("Invalid input"); break;
			}
		} while(!isQuit);
	}

}
