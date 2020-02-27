package edu.smith.cs.csc212.spooky;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represents all of the game state needed to understand the player.
 * At the beginning of this project, it is just the ID or name of a place.
 * 
 * @author jfoley
 *
 */
public class Player {
	/**
	 * The ID of the Place object where the player is currently.
	 */
	private String place;
	/**
	 * The time now in the mansion.
	 */
	private GameTime time;
	/**
	 * The ID of the Place where player visited.
	 */
	private Set<String> visited;
	/**
	 * The ID of the list of stuff the player has.
	 */
	private List<String> stuffs;

	/**
	 * A player is created at the start of a game with just an initial place.
	 * @param initialPlace - where do we start?
	 * @param time - what time is it?
	 */
	public Player(String initialPlace, List<String> stuffPlayerHas, GameTime time) {
		this.place = initialPlace;
		this.stuffs = stuffPlayerHas;
		this.visited = new HashSet<>();
		this.time = time;
	}

	/**
	 * Get access to the place instance variable from outside this class.
	 * @return the id of the current location.
	 */
	public String getPlace() {
		return place;
	}
	
	/**
	 * Get access to the stuffs instance variable from outside this class.
	 * @return the id of the current list of stuffs the player has.
	 */
	public List<String> getStuff() {
		return stuffs;
	}
	
	/**
	 * Get access to the stuffs instance variable from outside this class.
	 * @return the id of the current list of stuffs the player has.
	 */
	public GameTime getTime() {
		return this.time;
	}

	/**
	 * Increase time every time the method is called
	 */
	public void increaseTime() {
		this.time.increaseHour();
	}
	
	/**
	 * Call this method when the player moves to a new place.
	 * @param place - the place we are now located at.
	 */
	public void moveTo(String place) {
		this.increaseTime();
		this.rememberThisPlace();
		this.place = place;
	}
	
	/**
	 * Put the place that the player has been before in a list.
	 */
	public void rememberThisPlace() {
		this.visited.add(place);
	}
	
	/**
	 * See if the player visited this place before.
	 */
	public boolean hasBeenHereBefore() {
		return this.visited.contains(this.getPlace());
	}
}
