package refactored;

import java.util.Scanner;
import java.util.List;

public class BidderUI implements UserUI {
	
	private Bidder myBidder;

	@Override
	public void promptMainMenu(Scanner scanner, CalendarUI theCalendar, User currentUser) {
		myBidder = (Bidder) currentUser;
		boolean isQuit = false;
		do {
			System.out.println(theCalendar);
			System.out.println("Please choose an option below.");
			System.out.println("1.  Choose an available auction");
			System.out.println("2.  Create or change a bid on an item");
			System.out.print("------Done!! want to exit? type any number ");
			switch (scanner.nextInt()) {
				case 1: viewUpcomingAuction(scanner, theCalendar); break;
				case 2: makeOrChangeBid(scanner, theCalendar); break;
				default: isQuit = true; break;
			}
		} while (!isQuit);
	}
	
	private void viewUpcomingAuction(Scanner scanner, CalendarUI cal) {
		System.out.println("Please pick one auction for detail. ");
		List<Auction> upcomingAuc = cal.getDispCalendar().getUpcomingAuctions();
		for (int i = 0; i < upcomingAuc.size(); i++) {
			System.out.println((i + 1) + ") " + upcomingAuc.get(i));
		}
		int pickAuc = scanner.nextInt();
		for (int i = 1; i <= upcomingAuc.size(); i++) {
			if (i == pickAuc) System.out.println(upcomingAuc.get(i - 1).displayAuction());
		}
	}
	
	private void makeOrChangeBid(Scanner scanner, CalendarUI cal) {
		System.out.println("You are about to make or change a bid!");
		listAuctions(cal);
		
		int pickAuc = scanner.nextInt();
		Auction chosenAuc = chooseAuction(pickAuc, cal);
		
		if (chosenAuc != null) {
			listItems(chosenAuc);
			
			int pickItem = scanner.nextInt();
			Item chosenItem = chooseItem(pickItem, chosenAuc);
			
			nextAction(scanner, chosenAuc, chosenItem);
		}
	}
	
	private void listAuctions(CalendarUI cal) {
		System.out.println("Choose an auction. ");
		List<Auction> upcomingAuc = cal.getDispCalendar().getUpcomingAuctions();
		for (int i = 0; i < upcomingAuc.size(); i++) {
			System.out.println((i + 1) + ") " + upcomingAuc.get(i));
		}
	}
	
	private Auction chooseAuction(int pickAuc, CalendarUI cal) {
		Auction chosenAuc = null;
		List<Auction> upcomingAuc = cal.getDispCalendar().getUpcomingAuctions();
		for (int i = 1; i <= upcomingAuc.size(); i++) {
			if (i == pickAuc) { 
				chosenAuc = upcomingAuc.get(i - 1);
				System.out.println(chosenAuc.getAuctionName());
			}
		}
		
		return chosenAuc;
	}
	
	private void listItems(Auction chosenAuc) {
		System.out.println("Choose an item. ");
		for (int i = 0; i < chosenAuc.getAllItems().size(); i++) {
			System.out.println((i + 1) + ") " + chosenAuc.getAllItems().get(i));
		}
	}
	
	private Item chooseItem(int pickItem, Auction chosenAuc) {
		Item chosenItem = null;
		for (int i = 0; i < chosenAuc.getAllItems().size(); i++) {
			if (i == pickItem) {
				chosenItem = chosenAuc.getAllItems().get(i - 1);
				System.out.println(chosenAuc.getAllItems().get(i - 1).getTitle());
			}
		}
		
		return chosenItem;
	}
	
	private void nextAction(Scanner scanner, Auction auction, Item item) {
		boolean hasBid = false;
		Bid existingBid = null;
		for (Bid bid : myBidder.viewBids()) {
			if (bid.getItem().equals(item)) {
				hasBid = true;
				existingBid = bid;
				break;
			}
		}
		
		if (hasBid) {
			if (existingBid != null) {
				System.out.println("You have made a bid on this item.");
				System.out.println("You bid $" + existingBid.getBidAmount() + " on the item.");
				System.out.println("Enter the $ amount you want to change the bid to.");
				double newAmount = scanner.nextDouble();
				existingBid.setBidAmount(newAmount);
				int i = auction.viewBids().indexOf(existingBid);
				auction.viewBids().get(i).setBidAmount(newAmount);
			}
		} else {
			System.out.println("You have not made a bid on this item.");
			System.out.println("Enter the $ amount you want to bid.");
			double bidAmount = scanner.nextDouble();
			Bid newBid = new Bid(item, bidAmount, myBidder.toString());
			myBidder.addBid(newBid);
			auction.addBid(newBid);
		}
	}
}
