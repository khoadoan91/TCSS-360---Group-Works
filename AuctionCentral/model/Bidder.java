package model;

import java.util.ArrayList;
import java.util.List;

public class Bidder {
	
	private String myAddress;
	private String myCreditCard;
	private List<Bid> myBids;
	
	public Bidder(final String theAddress, final String theCreditCard) {
		if (theAddress == null) {
			throw new NullPointerException();
		} else if (theAddress.isEmpty()) {
			throw new IllegalArgumentException();
		} else if (theCreditCard == null) {
			throw new NullPointerException();
		} else if (theCreditCard.isEmpty()) {
			throw new IllegalArgumentException();
		}
		
		myAddress = theAddress;
		myCreditCard = theCreditCard;
		myBids = new ArrayList<Bid>();
	}
	
	public List<Bid> viewBids() {
		return myBids;
	}
	
	public void addBid(final Bid theBid) {
		if (theBid == null) {
			throw new NullPointerException();
		}
		
		myBids.add(theBid);
	}
	
	public void removeBid(final Bid theBid) {
		if (theBid == null) {
			throw new NullPointerException();
		} else if (!myBids.contains(theBid)) {
			throw new IllegalArgumentException();
		}
		
		myBids.remove(theBid);
	}
	
	/*public Bid editBid(final Bid theBid) {
		if (theBid == null) {
			throw new NullPointerException();
		} else if (!myBids.contains(theBid)) {
			throw new IllegalArgumentException();
		}
		
		
	}*/
}
