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
		this.myCalender = myCalendar;
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
	
}