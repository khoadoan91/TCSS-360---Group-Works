package current;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a user which can bid on items in auctions.
 * 
 * @author Nina Chepovska
 * @version Nov 6, 2015
 */
public class Bidder implements User { 

//	final private String userType = "Bidder";

	/** The billing address of the bidder. */
	@SuppressWarnings("unused")
	private String myAddress;

	/** The credit card number of the bidder. */
	@SuppressWarnings("unused")
	private String myCreditCard;

	/** The bids this bidder has made. */
	private List<Bid> myBids;
	
	public Bidder(String theAddr, String theCredit) {
		this(theAddr, theCredit, new ArrayList<Bid>());
	}
	
	public Bidder(String theAddr, String theCredit, List<Bid> theBids) {
		myAddress = theAddr;
		myCreditCard = theCredit;
		myBids = theBids;
	}

	/**
	 * Constructs a new bidder object.
	 * 
	 * @param theAddress
	 * @param theCreditCard
	 */
//	public Bidder(DisplayCalendar myCalendar) {
//		myBids = new ArrayList<Bid>();
//	}

	/**
	 * Gets all the bids the bidder has made.
	 * 
	 * @return the bids.
	 */
	public List<Bid> viewBids() {
		return myBids;
	}

	/**
	 * Adds a new bid.
	 * 
	 * @param theBid
	 */
	public void addBid(final Bid theBid) {
		if (theBid == null) {
			throw new NullPointerException();
		}

		myBids.add(theBid);
	}

	/**
	 * Removes the specified bid.
	 * 
	 * @param theBid
	 */
	public void removeBid(final Bid theBid) {
		if (theBid == null) {
			throw new NullPointerException();
		} else if (!myBids.contains(theBid)) {
			throw new IllegalArgumentException();
		}

		myBids.remove(theBid);
	}

	/*
	 * public Bid editBid(final Bid theBid) { if (theBid == null) { throw new
	 * NullPointerException(); } else if (!myBids.contains(theBid)) { throw new
	 * IllegalArgumentException(); }
	 * 
	 * 
	 * }
	 */
//	public String getUserType() {
//		return userType;
//	}
	
	
	public void viewUpcomingAuction(Scanner scanner, DisplayCalendar cal) {
		System.out.println("Please pick one auction for detail. ");
		List<Auction> upcomingAuc = cal.getUpcomingAuctions();
		for (int i = 0; i < upcomingAuc.size(); i++) {
			System.out.println((i + 1) + ") " + upcomingAuc.get(i));
		}
		int pickAuc = scanner.nextInt();
		for (int i = 1; i <= upcomingAuc.size(); i++) {
			if (i == pickAuc) System.out.println(upcomingAuc.get(i - 1).displayAuction());
		}
	}
	
	public void makeBid(Scanner scanner, DisplayCalendar cal) {
		System.out.println("You are about to make a bid!");
		System.out.println("Choose an auction. ");
		List<Auction> upcomingAuc = cal.getUpcomingAuctions();
		for (int i = 0; i < upcomingAuc.size(); i++) {
			System.out.println((i + 1) + ") " + upcomingAuc.get(i));
		}
		int pickAuc = scanner.nextInt();
		Auction chosenAuc = null;
		for (int i = 1; i <= upcomingAuc.size(); i++) {
			if (i == pickAuc) { 
				chosenAuc = upcomingAuc.get(i - 1);
				System.out.println(chosenAuc.getAuctionName());
			}
		}
		if (chosenAuc != null) {
			System.out.println("Choose an item. ");
			for (int i = 0; i < chosenAuc.getAllItems().size(); i++) {
				System.out.println((i + 1) + ") " + chosenAuc.getAllItems().get(i));
			}
			int pickItem = scanner.nextInt();
			Item chosenItem = null;
			for (int i = 0; i < chosenAuc.getAllItems().size(); i++) {
				if (i == pickItem - 1) {
					chosenItem = chosenAuc.getAllItems().get(i);
					System.out.println(chosenAuc.getAllItems().get(i).getTitle());
				}
			}
			
			for (Bid bid : this.viewBids()) {
				System.out.println("How about here?");
				if (bid.getItem().equals(chosenItem)) {
					System.out.println("You have made a bid on this item.");
				}
			}
		}
	}

	@Override
	public void run(Scanner scanner, DisplayCalendar cal) {
		boolean isQuit = false;
		do {
			System.out.println(cal);
			System.out.println("Please choose an option below.");
			System.out.println("1.  Choose an available auction");
			System.out.println("2.  Create or change a bid on an item");
			System.out.print("------Done!! want to exit? type any number ");
			switch (scanner.nextInt()) {
				case 1: viewUpcomingAuction(scanner, cal); break;
				case 2: makeBid(scanner, cal); break;
				default: isQuit = true; break;
			}
		} while (!isQuit);
		
	}
}
