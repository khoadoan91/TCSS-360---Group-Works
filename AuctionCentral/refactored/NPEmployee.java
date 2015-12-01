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
