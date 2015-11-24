package refactored;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

import current.DisplayCalendar;
import current.User;

public class Main {
	
	private static Map<String, User> myUsers;
	private static DisplayCalendar myCalendar; 
	
	public static void main(String[] args) {
		File userFile = new File("user_list.txt");
		File auctionFile = new File("current_auction_list.txt");
		File itemFile = new File("current_item_list.txt");
		
		FileHandler fileLoader = new FileHandler();		
		myCalendar = new DisplayCalendar(fileLoader.readAuctionFile(auctionFile, itemFile));
		myUsers = fileLoader.readUserFile(userFile);
		
		UserUI ui = new UserUI(myUsers);
		User theUser = ui.promptLogin();
		
		Scanner scanner = new Scanner(System.in);
		theUser.run(scanner, myCalendar);



	}

}
