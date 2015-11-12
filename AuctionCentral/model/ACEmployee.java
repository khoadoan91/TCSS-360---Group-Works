package model;

import java.util.Date;
import java.util.List;

/**
 * @author nabilfadili
 *
 */
public class ACEmployee extends User {
	
	final private String userType = "ACEmployee";
	// private DisplayCalendar;

	// TODO fix constructor to take the calendar from main
	public ACEmployee(String username, DisplayCalendar myCalendar) {
		super(username, myCalendar);
	}
	
//	@Deprecated
//	public ACEmployee(String username) {
//		super(username);
//	}
	
	/**
	 * Accesses calendar singleton and returns a list of upcoming auctions.
	 * !!!Should this return a list or simply print the list?? !!!This would
	 * mean changing the return type to void and printing from this method.
	 * 
	 * @return
	 */
	public List<Auction> viewAuctionList() {
		// TODO access calendar object and display upcoming auctions
		return null;
	}

	/**
	 * Prints details of an Auction object. !!!Would need to access the calendar
	 * singleton and get an Auction object directly !!!from it. Or call
	 * viewAuctionList() and then iterate through the list till we find !!!the
	 * correct auction.
	 * 
	 * @param auctionID
	 */
	public void selectAuction(String auctionName) {
		// TODO view details of an auction object, including list of items

	}

	public String getUserType() {
		return userType;
	}

}
