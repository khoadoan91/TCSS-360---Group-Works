package model;

import java.util.Date;
import java.util.List;

/**
 * @author nabilfadili
 *
 */
public class ACEmployee extends User {
	
	final private String userType = "ACEmployee";

	public ACEmployee(String username, DisplayCalendar myCalendar) {
		super(username, myCalendar);
	}
	
	/**
	 * Accesses calendar singleton and returns a list of upcoming auctions.
	 * !!!Should this return a list or simply print the list?? !!!This would
	 * mean changing the return type to void and printing from this method.
	 * 
	 * @return
	 */
	public void viewAuctionList() {
		// TODO access calendar object and display upcoming auctions
	}

	/**
	 * Prints details of an Auction object. !!!Would need to access the calendar
	 * singleton and get an Auction object directly !!!from it. Or call
	 * viewAuctionList() and then iterate through the list till we find !!!the
	 * correct auction.
	 * 
	 * @param auctionID
	 */
	public void selectAuction() {
		// TODO view details of an auction object, including list of items
		//This methods should show the list of auctions and then let the user choose one to view.
	}
	
	public void viewCalendar() {
		System.out.println(myCalendar.toString());
	}

	public String getUserType() {
		return userType;
	}

}
