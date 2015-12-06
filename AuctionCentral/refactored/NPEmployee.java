package refactored;

import java.util.ArrayList;

/**
 * Class representing a non-profit employee. Holds information on their one and only current
 * auction as well as their one and only organization name.
 * @author nabilfadili
 *
 */
public class NPEmployee extends User {

	private String myOrgName;
	private Auction myCurrentAuction;
	private ArrayList<Auction> myPastAuctions;

	public NPEmployee(final String orgName, final Auction theAuc) {
		myOrgName = orgName;
		myCurrentAuction = theAuc;
		myPastAuctions = new ArrayList<Auction>();
	}

	public String getMyOrgName() {
		return myOrgName;
	}

	public Auction getMyCurrentAuction() {
		return myCurrentAuction;
	}
	
	public void addAuction(Auction theAuction) {
		myCurrentAuction = theAuction;
	}

	public void removeAuction() {
		myCurrentAuction = null;
	}

	public ArrayList<Auction> getPastAuctionList() {
		return myPastAuctions;
	}
	
	public void addPastAuctions(ArrayList<Auction> theAuctions) {
		myPastAuctions.addAll(theAuctions);		//check this
	}
}
