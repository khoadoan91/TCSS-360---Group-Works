package model;

/**
 * @author nabilfadili
 *
 */
public class User {

	private String username;
	protected DisplayCalendar myCalendar;
	
	public User(String username, DisplayCalendar myCalendar) {
		this.username = username;
		this.myCalendar = myCalendar;
	}
	
	@Deprecated
	public User(String username) {
		this.username = username;
	}
	
	public DisplayCalendar getDisplayCalendar(){
		return myCalendar;
	}
	
	public String getUsername() {
		return username;
	}

	public String getUserType() {
		return "User unspecified";
	}
	
	
	/**
	 * This method is meant for override by the User Subclasses
	 */
	public void viewAuctionList() {
		System.out.println("Login Error. You are not an ACEmployee");
	}
	/**
	 * This method is meant for override by the User Subclasses
	 */
	public void selectAuction() {
		System.out.println("Login Error. You are not an ACEmployee");
	}
	/**
	 * This method is meant for override by the User Subclasses
	 */
	public void viewCalendar() {
		System.out.println("Login Error. You are not an ACEmployee.");
	}

}
