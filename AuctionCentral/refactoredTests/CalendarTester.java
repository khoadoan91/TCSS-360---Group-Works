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
import refactored.Auction;
import refactored.Item;



public class CalendarTester {
	
	DisplayCalendar fullCalendar;
	DisplayCalendar oneCalendar;
	DisplayCalendar emptyCalendar;
	DisplayCalendar fiveInWeekCalendar;
	DisplayCalendar twoAuctionOneDayCalendar;
	Calendar day;
	Auction testAuction;
	Auction TESTORGAuction;
	Auction testAuction2far;
	Auction alreadyAuctionHere;
	
   @Before public void setUp() {
	   
      List<Item> aList = new LinkedList<Item>();
      List<Auction> empty = new ArrayList<Auction>();
	   aList.add(Item.makeRItem());
      fullCalendar = new DisplayCalendar(empty);
      oneCalendar = new DisplayCalendar(empty);
      emptyCalendar = new DisplayCalendar(empty);
      fiveInWeekCalendar = new DisplayCalendar(empty);
      twoAuctionOneDayCalendar = new DisplayCalendar(empty);
      day = Calendar.getInstance();
      day.get(Calendar.DAY_OF_YEAR);
      for(int i = 0; i < 25;i++){
         day.set(Calendar.DAY_OF_YEAR,day.get(Calendar.DAY_OF_YEAR) + 2);
         fullCalendar.addAuction(Auction.makeTestAuction(day));
      }
      day = Calendar.getInstance();
      day.set(Calendar.DAY_OF_YEAR,day.get(Calendar.DAY_OF_YEAR) + 8);
      oneCalendar.addAuction(Auction.makeTestAuction(day));
      alreadyAuctionHere = Auction.makeTestAuction(day);
//      
      for(int i = 0; i < 5; i++){
         day.set(Calendar.DAY_OF_YEAR,day.get(Calendar.DAY_OF_YEAR) + 1);
         fiveInWeekCalendar.addAuction(Auction.makeTestAuction(day));
      }
      day.set(Calendar.DAY_OF_YEAR,day.get(Calendar.DAY_OF_YEAR) + 1);
      testAuction = Auction.makeTestAuction(day);
      TESTORGAuction = new Auction("THEORGOFTESTING", aList, day, "03:01");
      day.set(Calendar.HOUR_OF_DAY,0);
      day.set(Calendar.HOUR_OF_DAY,day.get(Calendar.HOUR_OF_DAY) + 5);
      twoAuctionOneDayCalendar.addAuction(Auction.makeTestAuction(day));
      day.set(Calendar.HOUR_OF_DAY,day.get(Calendar.HOUR_OF_DAY) + 5);
      twoAuctionOneDayCalendar.addAuction(Auction.makeTestAuction(day));
      day.set(Calendar.YEAR,day.get(Calendar.YEAR) + 1);
      testAuction2far = Auction.makeTestAuction(day);
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
   
   @Test public void is90DaysAwayForTooFarAway() {
      Assert.assertTrue(oneCalendar.hasAuctionOver90Days(testAuction2far));
   }
   
   @Test public void is90DaysAwayForInRange() {
      Assert.assertFalse(oneCalendar.hasAuctionOver90Days(testAuction));
   }
   
   @Test public void hasMore5AuctionsIn7DaysTESTshouldWork(){
      Assert.assertFalse(oneCalendar.hasMore5AuctionsIn7Days(testAuction));
   }
   
   @Test public void hasMore5AuctionsIn7DaysTESTshouldFAIL(){
      Assert.assertTrue(fiveInWeekCalendar.hasMore5AuctionsIn7Days(testAuction));
   }
   
   @Test public void only2auctionsindayTESTfornoauction(){
      Assert.assertEquals(-2,oneCalendar.has2AuctionsInSameDay(testAuction));
   }
   
   @Test public void only2auctionsindayTESTfor2auctions(){
      Assert.assertEquals(-1,twoAuctionOneDayCalendar.has2AuctionsInSameDay(testAuction));
   }
   
   @Test public void noAuctionEvery2HoursforNOnearbyauction(){
      Assert.assertFalse(oneCalendar.has2HoursBetween2Auctions(testAuction));
   }
   
   @Test public void noAuctionEvery2HoursforAnearbyauction(){
      oneCalendar.addAuction(testAuction);
      Assert.assertTrue(oneCalendar.has2HoursBetween2Auctions(testAuction));
   }
   
//   @Test public void noAuctionOnSameYearTESTforhasauction(){
//	   Assert.assertTrue(false);//always fails TODO make an actual test
//   }
   
   @Test public void noAuctionOnSameYearTESTNOAUCTION(){
      Assert.assertTrue(oneCalendar.hasAuctionPerNPperYear(TESTORGAuction));
   }

}
/*
   DisplayCalendar myDC;
   Calendar day;

   
   //test max auction
   @Test public void testMaxAuctions() {
	  Assert.assertFalse(myDC.hasExceededAuction());
      for(int i = 0; i < 25; i++){
         day.set(Calendar.DAY_OF_YEAR,day.get(Calendar.DAY_OF_YEAR) + 1 + (i % 2));
         myDC.addAuction(Auction.makeTestAuction(day));
      }
      Assert.assertTrue(myDC.hasExceededAuction());
   }
   
   @Test public void is90DaysAway() {
      
      day.set(Calendar.DAY_OF_YEAR,day.get(Calendar.DAY_OF_YEAR) + 1);
      Assert.assertFalse(myDC.hasMoreThan90Days(Auction.makeTestAuction(day)));
      day.set(Calendar.DAY_OF_YEAR,day.get(Calendar.DAY_OF_YEAR) + 97);
      Assert.assertTrue(myDC.hasMoreThan90Days(Auction.makeTestAuction(day)));
   }
   
   // hasMore5AuctionsIn7Days(final Auction theAuc)
   @Test public void hasMore5AuctionsIn7DaysTEST(){
      day.set(Calendar.DAY_OF_YEAR,day.get(Calendar.DAY_OF_YEAR) + 1);
      Assert.assertFalse(myDC.hasExceededAuction());
      for(int i = 0; i < 5; i++){
         day.set(Calendar.DAY_OF_YEAR,day.get(Calendar.DAY_OF_YEAR) + 1 + (i % 2));
         myDC.addAuction(Auction.makeTestAuction(day));
      }
      Assert.assertTrue(myDC.hasMore5AuctionsIn7Days(Auction.makeTestAuction(day)));
      
   }
   
   // cant have 3 auctions in the same day
   @Test public void only2auctionsindayTEST(){
   //has2AuctionsInSameDay(final Auction theAuc){
      day.set(Calendar.DAY_OF_YEAR,day.get(Calendar.DAY_OF_YEAR) + 1);
      day.set(Calendar.HOUR_OF_DAY,0);
      Assert.assertEquals(-2, myDC.has2AuctionsInSameDay(Auction.makeTestAuction(day)));
      myDC.addAuction(Auction.makeTestAuction(day));
      day.set(Calendar.HOUR_OF_DAY,day.get(Calendar.HOUR_OF_DAY) + 5);
      myDC.addAuction(Auction.makeTestAuction(day));
      day.set(Calendar.HOUR_OF_DAY,day.get(Calendar.HOUR_OF_DAY) + 5);
      Assert.assertEquals(-1, myDC.has2AuctionsInSameDay(Auction.makeTestAuction(day)));
   }
   
   @Test public void noAuctionEvery2Hours(){
   //has2HoursBetween2Auctions(final Auction theAuc)
      day.set(Calendar.DAY_OF_YEAR,day.get(Calendar.DAY_OF_YEAR) + 1);
      day.set(Calendar.HOUR_OF_DAY,9);
      Assert.assertFalse(myDC.has2HoursBetween2Auctions(Auction.makeTestAuction(day)));
      myDC.addAuction(Auction.makeTestAuction(day));
      day.set(Calendar.HOUR_OF_DAY,day.get(Calendar.HOUR_OF_DAY) + 1);
      Assert.assertTrue(myDC.has2HoursBetween2Auctions(Auction.makeTestAuction(day)));
      day.set(Calendar.HOUR_OF_DAY,day.get(Calendar.HOUR_OF_DAY) - 2);
      Assert.assertTrue(myDC.has2HoursBetween2Auctions(Auction.makeTestAuction(day)));
      day.set(Calendar.HOUR_OF_DAY,day.get(Calendar.HOUR_OF_DAY) - 4);
      Assert.assertFalse(myDC.has2HoursBetween2Auctions(Auction.makeTestAuction(day)));
      day.set(Calendar.HOUR_OF_DAY,day.get(Calendar.HOUR_OF_DAY) + 14);
      Assert.assertFalse(myDC.has2HoursBetween2Auctions(Auction.makeTestAuction(day)));
   }
   
   @Test public void noAuctionOnSameYearTEST(){
      //public boolean hasAuctionPerNPperYear(final Auction theAuction) {
	  List<Item> aList= new LinkedList<Item>();
	  aList.add(Item.makeRItem());
      day.set(Calendar.DAY_OF_YEAR,day.get(Calendar.DAY_OF_YEAR) + 1);
      Auction TESTAuction = new Auction("THEORGOFTESTING", aList, day, "03:01");
      Assert.assertTrue(myDC.hasAuctionPerNPperYear(TESTAuction));
      myDC.addAuction(TESTAuction);
      day.set(Calendar.DAY_OF_YEAR,day.get(Calendar.DAY_OF_YEAR) + 1);
      TESTAuction = new Auction("THEORGOFTESTING", aList, day, "03:01");
      Assert.assertFalse(myDC.hasAuctionPerNPperYear(TESTAuction));
   }

   /** A test that always fails. 
   @Test public void defaultTest() {//this was auto created
      Assert.assertEquals("Default test added by jGRASP. Delete "
            + "this test once you have added your own.", 0, 1);
   }
   
   @Test public void alwaysTest(){
	   assertTrue(true);
   }
}
*/