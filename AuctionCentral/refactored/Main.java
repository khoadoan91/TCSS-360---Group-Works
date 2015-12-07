package refactored;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

	private static HashMap<String, User> myUsers;
	private static CalendarUI myCalendar;

	public static void main(String[] args) throws IOException {
		/** Text File input**/
//		File userFile = new File("user_list_final.txt");
//		File auctionFile = new File("business_rule_4_auction_list.txt");
//		File itemFile = new File("business_rule_4_item_list.txt");
//	    FileHandler myFileHandler = new FileHandler();
//		DisplayCalendar calendarModel = new DisplayCalendar(myFileHandler.readAuctionFile(auctionFile, itemFile));
//		myCalendar = new CalendarUI(calendarModel);
//		myUsers = myFileHandler.readUserFile(userFile);
//	    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//		MainUI ui = new MainUI(myUsers);
//		ui.promptLogin(reader);
//		myUsers = ui.run(reader, myCalendar);
//		reader.close();
		
		
		/**Serialized Input. Select from:
		 * business_rule_1_user_list_final.ser
		 * business_rule_2_user_list_final.ser
		 * business_rule_3_user_list_final.ser
		 * business_rule_4_user_list_final.ser
		 * business_rule_5_user_list_final.ser
		 * 
		 * */
        FileHandler myFileHandler = new FileHandler();
	    try {
			FileInputStream inFile = new FileInputStream("business_rule_4_user_list_final.ser");
			myUsers = myFileHandler.deserializeAllUsers(inFile);
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
		
//		User tempUser; 
//		Iterator<Entry<String, User>> it = myUsers.entrySet().iterator();myUsers.entrySet().iterator();
//		while (it.hasNext()) {
//			tempUser = it.next().getValue();
//			if (tempUser instanceof NPEmployee) {
//				System.out.println(((NPEmployee) tempUser).getMyCurrentAuction());
//			}
//		}
		/** Always serialize*/
		myFileHandler.serializeAllUsers(myUsers);			
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
				if (((NPEmployee)tempUser).getMyCurrentAuction() != null){
					allAuctions.add(((NPEmployee) tempUser).getMyCurrentAuction());
				}
			}
		}
		return allAuctions;
	}

}
