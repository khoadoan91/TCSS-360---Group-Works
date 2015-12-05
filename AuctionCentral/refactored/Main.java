package refactored;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;


/**
 * Main program start. Scanner singleton is initialized and passed around the program's
 * UI classes as it is needed.
 * Files are read and passed here.
 * @author nabilfadili
 *
 */
public class Main {

	final private static Scanner scanner = new Scanner(System.in);
	private static Map<String, User> myUsers;
	private static CalendarUI myCalendar;

	public static void main(String[] args) throws IOException {
		/** Text File input**/
//		File userFile = new File("user_list.txt");
//		File auctionFile = new File("current_auction_list.txt");
//		File itemFile = new File("current_item_list.txt");
//	    FileHandler myFileHandler = new FileHandler();
//		DisplayCalendar calendarModel = new DisplayCalendar(myFileHandler.readAuctionFile(auctionFile, itemFile));
//		myCalendar = new CalendarUI(calendarModel);
//		myUsers = myFileHandler.readUserFile(userFile);
//	    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//		MainUI ui = new MainUI(myUsers);
//		ui.promptLogin(reader);
//		myUsers = ui.run(reader, myCalendar);
//		reader.close();

		/**Serialized Input*/
        FileHandler myFileHandler = new FileHandler();
	    try {
			FileInputStream inFile = new FileInputStream("user_list_final.ser");
			myUsers = myFileHandler.deserializeAllUsers(inFile);
			System.out.println(myUsers.size());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		DisplayCalendar calendarModel = new DisplayCalendar(retrieveAuctions(myUsers));
		myCalendar = new CalendarUI(calendarModel);
	    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		MainUI ui = new MainUI(myUsers);
		ui.promptLogin(reader);
		myUsers = ui.run(reader, myCalendar);
		reader.close();
		
		
		/** Always serialize*/
		myFileHandler.serializeAllUsers(myUsers);			//saves those changes to the .ser file
	}

	/**
	 * Retrieves the Auction objects of each User from the list of users.
	 * Lets Main add the auctions to the calendar
	 * @param theUsers all users read from .ser file
	 * @return a list of all auctions held by all users
	 */
	private static ArrayList<Auction> retrieveAuctions(Map<String, User> theUsers) {
		ArrayList<Auction> allAuctions = new ArrayList<Auction>();
		Iterator<Entry<String, User>> it = theUsers.entrySet().iterator();
		User tempUser = null;
		while (it.hasNext()) {
			tempUser = it.next().getValue();
			if (tempUser instanceof NPEmployee) {
				if (((NPEmployee)tempUser).getMyAuction() != null){
					allAuctions.add(((NPEmployee) tempUser).getMyAuction());
				}
			}
			if (tempUser instanceof Bidder) {
				//TODO Figure out Bidder/Bid changes
				//System.out.println("Bidder! " + ((Bidder)tempUser).viewBids());
			}
		}
		return allAuctions;
	}

}
