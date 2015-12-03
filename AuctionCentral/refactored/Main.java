package refactored;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

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
	private static DisplayCalendar myCalendar; 
	
	public static void main(String[] args) {
		//File userFile = new File("user_list.txt");
		//File auctionFile = new File("current_auction_list.txt");
		//File itemFile = new File("current_item_list.txt");
		FileHandler myFileHandler = new FileHandler();	
		
		try {
			FileInputStream inFile = new FileInputStream("user_list_final.ser");
			myUsers = myFileHandler.deserializeAllUsers(inFile);
			System.out.println(myUsers.size());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		myCalendar = new DisplayCalendar(retrieveAuctions(myUsers));
		//myCalendar = new DisplayCalendar(myFileHandler.readAuctionFile(auctionFile, itemFile));
		//myUsers = myFileHandler.readUserFile(userFile);
		
		
		MainUI ui = new MainUI(myUsers);					//passes loaded User objects
		ui.promptLogin(scanner);
		myUsers = ui.run(scanner, myCalendar);				//returns whatever happened to the User objects during run()
		
		myFileHandler.serializeAllUsers(myUsers);			//saves those changes to the .ser file
	}
	
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
				//Do what ya gotta do 
			}
		}
		for (Auction a: allAuctions) {
			System.out.println(a.toString());
		}
		return allAuctions;
	}

}
