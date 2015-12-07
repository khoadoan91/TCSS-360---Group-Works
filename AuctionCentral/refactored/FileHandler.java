package refactored;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * Class handles file i/o for both text files and serial files.
 * @author nabilfadili
 */
public class FileHandler {
	
	/** Used with text file Auction and Item reading only **/
	private List<Auction> auctionList;
	
	/**
	 * Reads .ser file and returns a Map of usernames and User objects to
	 * the main program.
	 * @param inFile .ser file of User objects
	 * @return Map<String, User>
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, User> deserializeAllUsers(FileInputStream inFile) {
		HashMap<String, User> theUsers = null;
		try {
			ObjectInputStream inputStream = new ObjectInputStream(inFile);
			theUsers = (HashMap<String, User>)inputStream.readObject();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return theUsers;	
	}
	
	/**
	 * Saves all User objects in a .ser file for the next program start. User objects hold
	 * Auctions, which hold Items, which hold Bids.
	 * @param myUpdatedUsers all User's and their associated objects
	 */
	public void serializeAllUsers(HashMap<String, User> myUpdatedUsers) {
		FileOutputStream fileOut;
		ObjectOutputStream outputStream;
		try {
			fileOut = new FileOutputStream("business_rule_4_user_list_final.ser");
			outputStream = new ObjectOutputStream(fileOut);
			outputStream.writeObject(myUpdatedUsers);
			outputStream.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Takes in a file of users and returns a map object of each username to User type.
	 * @param userFile text file representing user objects 
	 * @return Map<userName, userType>
	 */
	public HashMap<String, User> readUserFile(File userFile) {
		HashMap<String, User> myUsers = new HashMap<>();
		try {
			myUsers = new HashMap<>();
			Scanner scan = new Scanner(userFile);
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
		return myUsers;
	}
	/**
	 * Takes in a file of all auction info and a file of all item info. The two files
	 * must be in sync for this to work.
	 * @param auctionFile File representing all auction objects (currently txt)
	 * @param itemFile File representing all item objects (currently txt)
	 * @return list of all auctions with item within
	 */
	public List<Auction> readAuctionFile(File auctionFile, File itemFile) {
		List<Item> allItems = readItemFile(itemFile);
		auctionList = new LinkedList<>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(auctionFile));
			String line;
			int itemCount = 0;
			List<Item> itemsPerAuc = new LinkedList<>();
			while ((line = in.readLine()) != null) {
                 String aucInfo[] = line.split(", ");
                 int itemNumber = Integer.parseInt(aucInfo[1]);
                 for (int i = 0; i < itemNumber; i++) {
                	 itemsPerAuc.add(allItems.get(itemCount++)); 
                 }
                 auctionList.add(new Auction(aucInfo[0], itemsPerAuc, aucInfo[2], aucInfo[3]));
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
		return auctionList;
	}
	/**
	 * Helper method for reading from auction file. Maintains sync of items to auctions.
	 * @param itemInput File of item objects (currently txt)
	 * @return list of all items for every auction
	 */
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
	
	/**
	 * Insures the user's orgName matches the orgName listed for the auction
	 * @param auctionList list of all auctions
	 * @param orgName user's orgName
	 * @return the auction if it matches
	 */
	private Auction matchAuctionAndNPE(List<Auction> auctionList, String orgName) {
		Calendar now = Calendar.getInstance();
		for (int i = 0; i < auctionList.size(); i++) {
			if (auctionList.get(i).getDateAuctionStarts().compareTo(now) >= 0 
					&& auctionList.get(i).getOrganizationName().equals(orgName)) {
				return auctionList.remove(i); 
			}
		}
		return null;
	}
}
