package refactoredTests;


import org.junit.Assert;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import refactored.DisplayCalendar;
import refactored.DisplayCalendar.Exceed5AuctionsIn7Days;
import refactored.DisplayCalendar.Exceed90Days;
import refactored.DisplayCalendar.ExceedAuctionLimit;
import refactored.DisplayCalendar.ExceedAuctionLimitPerDay;
import refactored.DisplayCalendar.ExceedOneAuctionPerYear;
import refactored.Auction;
import refactored.Item;



public class CalendarTester {

	DisplayCalendar overfullCalendar;
	DisplayCalendar fullCalendar;
	DisplayCalendar oneCalendar;
	DisplayCalendar emptyCalendar;
	DisplayCalendar fiveInWeekCalendar;
	DisplayCalendar twoAuctionOneDayCalendar;
	DisplayCalendar halfYearOldAuctionByTestOrgCalendar;
	Calendar day;
	Auction testAuction;
	Auction testAuction2far;
	Auction alreadyAuctionHere;
	Auction newAuctionByTestOrg;
	
   @Before public void setUp() throws ExceedAuctionLimit, Exceed90Days, Exceed5AuctionsIn7Days, ExceedAuctionLimitPerDay, ExceedOneAuctionPerYear {
	   
	  //Item list used for occasionally manually initializing an auction
      List<Item> aList = new LinkedList<Item>();
      
      //Lists used when initializing Calendars 
      List<Auction> empty = new ArrayList<Auction>();
      List<Auction> full = new ArrayList<Auction>();
      List<Auction> overfull = new ArrayList<Auction>();
      List<Auction> halfYearOldAuctionByTestOrg = new ArrayList<Auction>();
   
      //make the item list not empty
	  aList.add(Item.makeRItem());

	  //initialize calendars that will be loaded automatically
      oneCalendar = new DisplayCalendar(empty);
      emptyCalendar = new DisplayCalendar(empty);
      fiveInWeekCalendar = new DisplayCalendar(empty);
      twoAuctionOneDayCalendar = new DisplayCalendar(empty);
      
      //loading overfullcalendar with 100 auctions
      day = Calendar.getInstance();
      day.get(Calendar.DAY_OF_YEAR);
      for(int i = 0; i < 100;i++){
         day.set(Calendar.DAY_OF_YEAR,day.get(Calendar.DAY_OF_YEAR) + 2);
         overfull.add(Auction.makeTestAuction(day));
      }
      overfullCalendar = new DisplayCalendar(overfull);
      
      //loading a calendar with an old auction from a specific non profit
      day = Calendar.getInstance();
      day.set(Calendar.MONTH,day.get(Calendar.MONTH) - 6);
      halfYearOldAuctionByTestOrg.add(new Auction("TestOrg",aList,day,"01:01"));
      halfYearOldAuctionByTestOrgCalendar = new DisplayCalendar(halfYearOldAuctionByTestOrg);
      
      //loading a calendar with exactly 25 auctions
      day = Calendar.getInstance();
      day.set(Calendar.MONTH,day.get(Calendar.MONTH) + 1);
      day.set(Calendar.DAY_OF_YEAR,day.get(Calendar.DAY_OF_YEAR) + 8);
      newAuctionByTestOrg = new Auction("TestOrg",aList,day,"01:01");
      for(int i = 0; i < 25;i++){
          day.set(Calendar.DAY_OF_YEAR,day.get(Calendar.DAY_OF_YEAR) + 2);
          full.add(Auction.makeTestAuction(day));
       }
      fullCalendar = new DisplayCalendar(full);
      
      //loading an auction with exactly one auction
      day = Calendar.getInstance();
      day.set(Calendar.MONTH,day.get(Calendar.MONTH) + 1);
      day.set(Calendar.DAY_OF_YEAR,day.get(Calendar.DAY_OF_YEAR) + 8);
      oneCalendar.addAuction(Auction.makeTestAuction(day));
      
      //makes an auction at the same time as another auction
      alreadyAuctionHere = Auction.makeTestAuction(day);
      
      //loads 5 auction into the same week
      for(int i = 0; i < 5; i++){
         day.set(Calendar.DAY_OF_YEAR,day.get(Calendar.DAY_OF_YEAR) + 1);
         fiveInWeekCalendar.addAuction(Auction.makeTestAuction(day));
      }
      day.set(Calendar.DAY_OF_YEAR,day.get(Calendar.DAY_OF_YEAR) + 1);
      testAuction = Auction.makeTestAuction(day);
      
      //loads two auction into the same day
      day.set(Calendar.HOUR_OF_DAY,0);
      day.set(Calendar.HOUR_OF_DAY,day.get(Calendar.HOUR_OF_DAY) + 5);
      twoAuctionOneDayCalendar.addAuction(Auction.makeTestAuction(day));
      day.set(Calendar.HOUR_OF_DAY,day.get(Calendar.HOUR_OF_DAY) + 5);
      twoAuctionOneDayCalendar.addAuction(Auction.makeTestAuction(day));
      
      //makes an auction way into the future
      day.set(Calendar.YEAR,day.get(Calendar.YEAR) + 1);
      testAuction2far = Auction.makeTestAuction(day);
   }
   
   @Test public void testMaxAuctionsForOverMaxAuctions(){
	      Assert.assertTrue(fullCalendar.hasExceededAuction());
	}
   
   @Test public void testMaxAuctionsForMaxAuctions(){
      Assert.assertTrue(fullCalendar.hasExceededAuction());
   }
   
   @Test public void testMaxAuctionsForEmptyAuctions(){
       Assert.assertFalse(emptyCalendar.hasExceededAuction());
   }
   
   @Test public void testMaxAuctionsLessThanMax(){
      Assert.assertFalse(oneCalendar.hasExceededAuction());
   }
   
   @Test public void testIs90DaysAwayForTooFarAway() {
      Assert.assertTrue(oneCalendar.hasAuctionOver90Days(testAuction2far));
   }
   
   @Test public void testIs90DaysAwayForInRange() {
      Assert.assertFalse(oneCalendar.hasAuctionOver90Days(testAuction));
   }
   
   @Test public void testHasMore5AuctionsIn7DaysDoesNotHave5AuctionIn7Days(){
      Assert.assertFalse(oneCalendar.hasMore5AuctionsIn7Days(testAuction));
   }
   
   @Test public void testHasMore5AuctionsIn7DaysHas5AuctionIn7Days(){
      Assert.assertTrue(fiveInWeekCalendar.hasMore5AuctionsIn7Days(testAuction));
   }
   
   @Test public void testOnly2AuctionsInDayForNoAuction(){
      Assert.assertEquals(-2,oneCalendar.has2AuctionsInSameDay(testAuction));
   }
   
   @Test public void testOnly2AuctionsInDayFor2Auctions(){
      Assert.assertEquals(-1,twoAuctionOneDayCalendar.has2AuctionsInSameDay(testAuction));
   }
   
   @Test public void testNoAuctionEvery2HoursForNoNearbyAuction(){
      Assert.assertFalse(oneCalendar.has2HoursBetween2Auctions(testAuction));
   }
   
   @Test public void testNoAuctionEvery2HoursForANearbyAuction() throws ExceedAuctionLimit, Exceed90Days, Exceed5AuctionsIn7Days, ExceedAuctionLimitPerDay, ExceedOneAuctionPerYear{
      oneCalendar.addAuction(testAuction);
      Assert.assertTrue(oneCalendar.has2HoursBetween2Auctions(testAuction));
   }
   
   @Test public void testNoAuctionOnSameYearForHasAuction(){
	   Assert.assertFalse(halfYearOldAuctionByTestOrgCalendar.hasAuctionPerNPperYear(newAuctionByTestOrg));
   }
   
   @Test public void testNoAuctionOnSameYearNoAuction(){
      Assert.assertTrue(oneCalendar.hasAuctionPerNPperYear(newAuctionByTestOrg));
   }

}

