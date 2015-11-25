package refactored;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class NPEmployee extends User {
	
	private String myOrgName;
	private Auction myAuction;
	private NPEmployeeUI ui;
	
	public NPEmployee(final String orgName, final Auction theAuc) {
		myOrgName = orgName;
		myAuction = theAuc;
		ui = new NPEmployeeUI();
	}
	public String getMyOrgName() {
		return myOrgName;
	}
	
	public void addAuction(Scanner scanner, DisplayCalendar theCalendar) {
		if (myAuction != null) {
			System.out.println("You already have an auction scheduled.");
		} else {
			myAuction = ui.addAuction(scanner, theCalendar, myOrgName);
			boolean isSuccess = theCalendar.addAuction(myAuction);
			if (!isSuccess) {
				System.out.println("Error! We are not allowed to add your auction.");
				myAuction = null;
			} else {
				System.out.println("You sucessfully add your auction!");
			}
		}	
	}
	
	public void editAuction(Scanner scanner) {
		
	}
	private void editItem(Scanner scanner) {
		
	}
	private void editAuctionDuration(Scanner scanner) {
		
	}
	private void editAuctionStartTime(Scanner scanner) {
		
	}
	private void editAuctionDay(Scanner scanner) {
		
	}
	public void removeAuction(final DisplayCalendar cal) {
		cal.removeAuction(myAuction);
		myAuction = null;
	}
	public void viewMyAuction() {
		if (myAuction == null) {
			System.out.println("Oops, you don't have any auction.");
		} else {
			System.out.println(myAuction);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	// I dont think we need this method!!!
		public void setAuction(final Auction theAuction) {
			myAuction = theAuction;
		}

}
