package refactored;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class Main {
	
	private static Map<String, User> myUsers;
	private static CalendarUI myCalendar; 
	
	public static void main(String[] args) throws IOException {
		File userFile = new File("user_list.txt");
		File auctionFile = new File("current_auction_list.txt");
		File itemFile = new File("current_item_list.txt");
		
		FileHandler fileLoader = new FileHandler();		
		DisplayCalendar calendarModel = new 
				DisplayCalendar(fileLoader.readAuctionFile(auctionFile, itemFile));
		myCalendar = new CalendarUI(calendarModel);
		myUsers = fileLoader.readUserFile(userFile);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		MainUI ui = new MainUI(myUsers);
		ui.promptLogin(reader);
		ui.run(reader, myCalendar);
		reader.close();
	}

}
