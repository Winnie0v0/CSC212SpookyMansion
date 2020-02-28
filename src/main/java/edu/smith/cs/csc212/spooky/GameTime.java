package edu.smith.cs.csc212.spooky;

import java.util.Random;

/**
 * Game time recored the time player is in the Mansion
 * @author macbookpro
 */
public class GameTime {

	private  Random rand = new Random();
	
	int Hour = rand.nextInt(24); 
	
	int finalHour = 0;
	
	public GameTime() {	
		
	}
	
	/**
	 * It tells you what time is it.
	 * @return Time
	 */
	public int getHour() {
		return Hour;
	}
	
	/**
	 * It tells you how much time did you spend in the Mansion.
	 * @return Time
	 */
	public int getFinalHour() {
		return finalHour;
	}
	
	/**
	 * It increases hour by one every time it is called.
	 */
	public void increaseHour() {
		if (Hour < 23) {
			Hour += 1;
		}
		else {
			Hour = 0;
		}
	}
	
	/**
	 * It increases hour by one every time it is called.
	 */
	public void increaseFinalHour() {
		finalHour += 1;
	}
	
	/**
	 * It test if it is night
	 */
	public boolean isNightTime() {
		if ( Hour >= 23 || Hour <= 5 ) {
			return true;
		}
		else {
			return false;
		}
		
	}


}
