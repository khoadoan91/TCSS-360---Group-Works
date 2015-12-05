package refactored;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

public class BidderUI implements UserUI {
	
	private Bidder myBidder;

	@Override
	public void promptMainMenu(BufferedReader reader, CalendarUI theCalendar, User currentUser) throws NumberFormatException, IOException {
		myBidder = (Bidder) currentUser;
		boolean isQuit = false;
		do {
			System.out.println(theCalendar);
			System.out.println("Please choose an option below.");
			System.out.println("1.  View all available auctions");
			System.out.println("2.  Create or change a bid on an item");
			System.out.println("3.  View your current bids");
			System.out.print("------Done!! want to exit? type any number ");
			switch (Integer.parseInt(reader.readLine())) {
				case 1: viewUpcomingAuction(theCalendar); break;
				case 2: makeOrChangeBid(reader, theCalendar); break;
				case 3: viewCurrentBids(); break;
				default: isQuit = true; break;
			}
		} while (!isQuit);
	}
	
	private void viewCurrentBids() {
		Map<Auction, Map<Item, BigDecimal>> myBids = myBidder.viewBids();
		for (Auction auc : myBids.keySet()) {
			System.out.println(auc);
			for (Item item : myBids.get(auc).keySet()) {
				System.out.println(item);
				System.out.println("Your bid: " 
				+ NumberFormat.getCurrencyInstance().format(myBids.get(auc).get(item)));
			}
			System.out.println();
		}
	}
	
	private void viewUpcomingAuction(CalendarUI cal) {
		System.out.println("Please pick one auction for detail. ");
		List<Auction> upcomingAuc = cal.getDispCalendar().getUpcomingAuctions();
		for (int i = 0; i < upcomingAuc.size(); i++) {
			System.out.println((i + 1) + ") " + displayItemsInAuction(upcomingAuc.get(i)));
		}
	}
	
	private String displayItemsInAuction(final Auction theAuction) {
		String result = theAuction.getOrganizationName() + " " 
					+ theAuction.getDateAuctionStarts().getTime() + "\n";
		char c = 'a';
		List<Item> list = theAuction.getAllItems();
		for (int i = 0; i < list.size(); i++) {
			result += c++ + ") " + list.get(i) + "\n";
		}
		return result;		
	}
	
	private void makeOrChangeBid(BufferedReader reader, CalendarUI cal) throws NumberFormatException, IOException {
		Auction chosenAuc = chooseAuction(reader, cal);
		if (chosenAuc != null) {
			Item chosenItem = chooseItem(reader, chosenAuc);
			if (chosenItem != null) {
				nextAction(reader, chosenAuc, chosenItem);
			} else {
				makeOrChangeBid(reader, cal);
			}
		}
	}
	
	private Auction chooseAuction(BufferedReader reader, CalendarUI cal) throws NumberFormatException, IOException {
		System.out.println("Choose an auction or hit other number to go back. ".toUpperCase());
		List<Auction> upcomingAuc = cal.getDispCalendar().getUpcomingAuctions();
		for (int i = 0; i < upcomingAuc.size(); i++) {
			System.out.println((i + 1) + ") " + upcomingAuc.get(i));
		}
		int pickAuc = Integer.parseInt(reader.readLine()) - 1;
		for (int i = 0; i < upcomingAuc.size(); i++) {
			if (i == pickAuc) { 
				return upcomingAuc.get(i);
			}
		}
		return null;
	}
	
	private Item chooseItem(BufferedReader reader, Auction chosenAuc) throws NumberFormatException, IOException {
		System.out.println("Choose an item. ");
		for (int i = 0; i < chosenAuc.getAllItems().size(); i++) {
			System.out.println((i + 1) + ") " + chosenAuc.getAllItems().get(i));
		}
		int pickItem = Integer.parseInt(reader.readLine()) - 1;
		for (int i = 0; i < chosenAuc.getAllItems().size(); i++) {
			if (i == pickItem) {
				return chosenAuc.getAllItems().get(i);
			}
		}
		return null;
	}
	
	private void nextAction(BufferedReader reader, Auction auction, Item item) throws IOException {
		if (myBidder.containsBid(auction, item)) {
			System.out.println("You have made a bid on this item.");
			System.out.println("Your bid on this item was " 
			+ NumberFormat.getCurrencyInstance().format(myBidder.getBidFrom(auction, item)));
			System.out.println("Enter the amount you want to change the bid to.");
		} else {
			System.out.println("You have not made a bid on this item.");
			System.out.println("Enter the amount you want to bid.");
		}
		String price = reader.readLine();
		myBidder.addBid(auction, item, new BigDecimal(price));
//		Bid existingBid = null;
//		for (Bid bid : myBidder.viewBids()) {
//			if (bid.getItem().equals(item)) {
//				hasBid = true;
//				existingBid = bid;
//				break;
//			}
//		}
//		
//		if (hasBid) {
//			if (existingBid != null) {
//				System.out.println("You have made a bid on this item.");
//				System.out.println("You bid $" + existingBid.getBidAmount() + " on the item.");
//				System.out.println("Enter the $ amount you want to change the bid to.");
//				double newAmount = scanner.nextDouble();
//				existingBid.setBidAmount(newAmount);
//				int i = auction.viewBids().indexOf(existingBid);
//				auction.viewBids().get(i).setBidAmount(newAmount);
//			}
//		} else {
//			System.out.println("You have not made a bid on this item.");
//			System.out.println("Enter the $ amount you want to bid.");
//			double bidAmount = scanner.nextDouble();
//			Bid newBid = new Bid(item, bidAmount, myBidder.toString());
//			myBidder.addBid(newBid);
//			auction.addBid(newBid);
//		}
	}
}
