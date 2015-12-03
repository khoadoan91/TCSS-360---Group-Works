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
 *
 */
public class FileHandler {
	//private Map<String, User> myUsers;
	private List<Auction> auctionList;
	
	
	@SuppressWarnings("unchecked")
	public Map<String, User> deserializeAllUsers(FileInputStream inFile) {
		HashMap<String, User> theUsers = null;
		try {
			ObjectInputStream inputStream = new ObjectInputStream(inFile);
			theUsers = (HashMap<String, User>)inputStream.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return theUsers;	
	}
	
	public void serializeAllUsers(Map<String, User> myUpdatedUsers) {
		FileOutputStream fileOut;
		ObjectOutputStream outputStream;
		try {
			fileOut = new FileOutputStream("user_list_final.ser");
			outputStream = new ObjectOutputStream(fileOut);
			outputStream.writeObject(myUpdatedUsers);
			outputStream.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
//	/**
//	 * Reads existing user from designated .ser file. All abjects held by a
//	 * User object are read as well.
//	 * @author nabilfadilli
//	 */
//	public Map<String, User> deserializeUserFile(FileInputStream inFile) {
//		Map<String, User> theUsers = new HashMap<String, User>();
//		try {
//			ObjectInputStream inputStream = new ObjectInputStream(inFile);
//			while (inputStream.available() > 0) {
//				theUsers.put((String)inputStream.readObject(), (User)inputStream.readObject());
//			}	
//			inputStream.close();
//			inFile.close();
//		} 
//		catch (IOException e) {
//			e.printStackTrace();
//		}
//		catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		return theUsers;
//	}
//	/**
//	 * Writes existing users to a .ser file.
//	 * @author nabilfadili
//	 */
//	public void serializeUserFile(Map<String, User> myUpdatedUsers) {
//		FileOutputStream fileOut;
//		ObjectOutputStream outputStream;
//		Iterator<Entry<String, User>> userList = myUpdatedUsers.entrySet().iterator();
//		try {
//			fileOut = new FileOutputStream("user_list_final.ser");
//			outputStream = new ObjectOutputStream(fileOut);
//			Entry<String, User> tempEntry;
//			User tempUser;
//			String userName;
//			while (userList.hasNext()) {
//				//System.out.println(userList.next());
//				tempEntry = userList.next();
//				userName = tempEntry.getKey();
//				System.out.print(userName + " = ");
//				tempUser = tempEntry.getValue();
//				System.out.println(tempUser);
//				outputStream.writeObject(userName);					//first writes the username String object
//				outputStream.writeObject(tempUser);	                //then writes the User object
//			}
//		} catch (IOException e) {
//			System.out.println("Something went wrong with the serialization");
//			e.printStackTrace();
//		}	
//	}
	
	/**
	 * Takes in a file of users and returns a map object of each username to User type.
	 * @param userFile file representing user objects (currently txt)
	 * @return Map<userName, userType>
	 */
	public Map<String, User> readUserFile(File userFile) {
		Map<String, User> myUsers = new HashMap<>();
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
		for (int i = 0; i < auctionList.size(); i++) {
			if (auctionList.get(i).getOrganizationNam().equals(orgName)) {
				return auctionList.remove(i);
			}
		}
		return null;
	}
}
