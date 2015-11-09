package model;

public class Bidder extends User {
	
	final private String userType = "Bidder";

	public Bidder(String username) {
		super(username);
	}
	
	public String getUserType() {
		return userType;
	}
}
