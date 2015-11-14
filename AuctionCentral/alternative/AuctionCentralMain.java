package alternative;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import model.Auction;
import model.DisplayCalendar;
import model.Item;

/**
 * @author KyleD
 *
 */
public class AuctionCentralMain {
	private Map<String, User> myUsers;
	private DisplayCalendar myCalendar; 
	
	public AuctionCentralMain(File userInput, File auctionInput, File itemInput) {
		myUsers = new HashMap<>();
		List<Auction> aucList = readAuctionFile(auctionInput, readItemFile(itemInput));
		myCalendar = new DisplayCalendar(aucList);
		readUserFile(userInput, aucList);
	}
	
	private Auction matchAuctionAndNPE(List<Auction> auctionList, String orgName) {
		for (int i = 0; i < auctionList.size(); i++) {
			if (auctionList.get(i).getOrganizationNam().equals(orgName)) {
				return auctionList.remove(i);
			}
		}
		return null;
	}
	
	private void readUserFile(File userInput, List<Auction> auctionList) {
		try {
			Scanner scan = new Scanner(userInput);
			while (scan.hasNext()) { 
				String userName = scan.next();
				String userType = scan.next();
				if (userType.equals("NPEmployee")) {
					String orgName = scan.next();
					Auction auc = matchAuctionAndNPE(auctionList, orgName);
					myUsers.put(userName, new NPEmployee(orgName, auc));
				} else if (userType.equals("ACEmployee")) {
					myUsers.put(userName, new ACEmployee());
				} else {
					String[] bidderInfo = scan.nextLine().split(", ");
					myUsers.put(userName, new Bidder(bidderInfo[0], bidderInfo[1]));
				}
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.err.println(e);
		}
	}
	
	private List<Auction> readAuctionFile(File auctionInput, List<Item> allItems) {
		List<Auction> result = new LinkedList<>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(auctionInput));
			String line;
			int itemCount = 0;
			List<Item> itemsPerAuc = new LinkedList<>();
			while ((line = in.readLine()) != null) {
                 String aucInfo[] = line.split(", ");
                 for (int i = 0; i < Integer.parseInt(aucInfo[1]); i++) {
                	 itemsPerAuc.add(allItems.get(itemCount++)); 
                 }
                 result.add(new Auction(aucInfo[0], itemsPerAuc, aucInfo[2], aucInfo[3]));
                 itemsPerAuc.clear();
            }
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error reading auction file.");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error close the buffered reader.");
		} catch (ParseException e) {
			e.printStackTrace();
			System.err.println("Error parse the month of the auction.");
		}
		return result;
	}
	
	private List<Item> readItemFile(File itemInput) {
		List<Item> result = new ArrayList<>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(itemInput));
			String line;
			while ((line = in.readLine()) != null) {
				String itemInfo[] = line.split(", ");
				result.add(new Item(itemInfo[0], 
						Integer.parseInt(itemInfo[1]), new BigDecimal(itemInfo[2]), itemInfo[3]));
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error reading auction file.");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error close the buffered reader.");
		}
		return result;
	}
	
	public void promptLogin() {
		Scanner scanner = new Scanner(System.in);
		boolean isLogin = false;
		String user;
		do {
			System.out.print("Enter username: ");
			user = scanner.next();
			if (myUsers.containsKey(user)) {
				System.out.println("Welcome " + user + "!!!!");
				isLogin = true;
			} else {
				System.out.println("Oops, please try again");
			}
		} while (!isLogin);
		myUsers.get(user).run(scanner, myCalendar);
		scanner.close();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File inputFile = new File("user_list.txt");
		File auctionFile = new File("auction_list2.txt");
		File itemFile = new File("item_list.txt");
		AuctionCentralMain main = new AuctionCentralMain(inputFile, auctionFile, itemFile);
		main.promptLogin();
	}
}