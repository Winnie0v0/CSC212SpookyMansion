package edu.smith.cs.csc212.spooky;

import java.util.Random;

/**
 * Game time recored the time player is in the Mansion
 * @author macbookpro
 */
public class GameTime {

	private Random rand = new Random();
	
	int Hour = rand.nextInt(24);
	
	int finalHour;
	
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
	 * It increases hour by one every time it is called.
	 */
	public void increaseHour() {
		if (Hour < 24) {
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
		this.finalHour = Hour + 1;
	}

}
