package refactored;

/**
 * Class representing a non-profit employee. Holds information on their one and only current
 * auction as well as their one and only organization name.
 * @author nabilfadili
 *
 */
public class NPEmployee extends User {
	
	private String myOrgName;
	private Auction myAuction;
	
	public NPEmployee(final String orgName, final Auction theAuc) {
		myOrgName = orgName;
		myAuction = theAuc;
	}
	
	public String getMyOrgName() {
		return myOrgName;
	}
	
	public Auction getMyAuction() {
		return myAuction;
	}
	public void addAuction(Auction theAuction) {
		myAuction = theAuction;
	}
	
	public void removeAuction() {
		myAuction = null;
	}
	public String viewAuction() {
		if (myAuction == null) {
			return "You do not have an auction scheduled.";
		} else {
			return myAuction.toString();
		}
	}
}
