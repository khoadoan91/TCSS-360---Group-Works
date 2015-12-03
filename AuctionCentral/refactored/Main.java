package refactored;

import java.io.File;
import java.util.Map;
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
		File userFile = new File("user_list.txt");
		File auctionFile = new File("current_auction_list.txt");
		File itemFile = new File("current_item_list.txt");
		
		FileHandler myFileHandler = new FileHandler();		
		myCalendar = new DisplayCalendar(myFileHandler.readAuctionFile(auctionFile, itemFile));
		myUsers = myFileHandler.readUserFile(userFile);
		
		MainUI ui = new MainUI(myUsers);
		ui.promptLogin(scanner);
		myUsers = ui.run(scanner, myCalendar);				//returns whatever happened to the user objects during run()
		
		myFileHandler.serializeUserFile(myUsers);
	}

}
