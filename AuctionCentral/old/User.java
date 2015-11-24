package old;

import refactored.DisplayCalendar;

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
	

}
