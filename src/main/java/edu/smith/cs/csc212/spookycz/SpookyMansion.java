package edu.smith.cs.csc212.spookycz;

import java.util.HashMap;
import java.util.Map;

/**
 * SpookyMansion, the game.
 * @author jfoley
 *
 */
public class SpookyMansion implements GameWorld {
	private Map<String, Place> places = new HashMap<>();

	/**
	 * Where should the player start?
	 */
	@Override
	public String getStart() {
		return "entranceHall";
	}

	/**
	 * This constructor builds our SpookyMansion game.
	 */
	public SpookyMansion() {
		Place entranceHall = insert(
				Place.create("entranceHall", "You are in the entrance hall of a large morden white building.\n"
						+ "The front door is locked. Where is your one card?"));
		entranceHall.addExit(new Exit("basement", "There are stairs leading down."));
		entranceHall.addExit(new Exit("roomFloor", "There are stairs leading up."));
		entranceHall.addExit(new Exit("elevator", "There is an elevator that is still working. Weird."));
		// Stuffs in the entrance hall
		entranceHall.addStuffs("a card", "There is someone's card");

		Place basement = insert(
				Place.create("basement", "You have found the basement in CZ.\n" + "It is darker down here.\n"
						+ "You get the sense a secret is nearby, but you only see the stairs you came from."));
		basement.addExit(new Exit("entranceHall", "There are stairs leading up."));
		basement.addExit(new Exit("elevator", "There is an elevator that is still working. Weird."));
		basement.addExit(new Exit("storageRoom", "There s a storage room. You wonder what is inside"));
		// Secret exit added.
		basement.addSecretExit(new SecretExit("crypt", "There is a trap door that was obscured by dust!"));

		Place roomFloor = insert(Place.create("roomFloor",
				"You find the room floor. Creepy... It seems no one is living here anymore"));
		roomFloor.addExit(new Exit("entranceHall", "There are stairs leading down."));
		roomFloor.addLockedExit(new LockedExit("bedroom", "There is someone's room", "a card"));
		roomFloor.addExit(new Exit("rooftop", "There are more stairs leading up."));

		Place jump = insert(Place.terminal("jump", "I wonder what you expected to happen here."));

		Place elevator = insert(Place.create("elevator", "There is an elevator that is still working. Weird."));
		elevator.addExit(new Exit("basement", "Take it to the bottom."));
		elevator.addExit(new Exit("entranceHall", "Take it to the middle-level."));
		elevator.addExit(new Exit("roomFloor", "Take it up to the top."));
				
		Place rooftop = insert(Place.create("rooftop", 
				"You have found the rooftop. You breath the fresh air."));
		rooftop.addExit(new Exit("roomFloor", "Go back."));
		rooftop.addExit(new Exit("jump", "You feel like jump."));
		
		Place bedroom = insert(Place.create("bedroom", 
				"It is someone's bedroom, there is still left over food there, but the student just vanished!"));
		bedroom.addExit(new Exit("roomFloor", "Go back."));
		
		Place storageRoom = insert(Place.create("storageRoom", "You have found the storage room. "
				+ "Storgeboxes and luggages are inside, it seems students still live here."));
		storageRoom.addExit(new Exit("basement", "Go back."));

		Place crypt = insert(Place.terminal("crypt", "You have found the crypt.\n"
				+ "It is scary here, but there is an exit to outside.\n" + "Maybe you'll be safe out there."));

		// Make sure your graph makes sense!
		checkAllExitsGoSomewhere();
	}

	/**
	 * This helper method saves us a lot of typing. We always want to map from p.id
	 * to p.
	 * 
	 * @param p - the place.
	 * @return the place you gave us, so that you can store it in a variable.
	 */
	private Place insert(Place p) {
		places.put(p.getId(), p);
		return p;
	}
	
	/**
	 * This helper method saves us a lot of typing. We always want to map from p.id
	 * to p.
	 * 
	 * @param p - the place.
	 * @return the place you gave us, so that you can store it in a variable.
	 */
	private NightPlace insert(NightPlace p) {
		places.put(p.getId(), p);
		return p;
	}

	/**
	 * I like this method for checking to make sure that my graph makes sense!
	 */
	private void checkAllExitsGoSomewhere() {
		boolean missing = false;
		// For every place:
		for (Place p : places.values()) {
			// For every exit from that place:
			for (Exit x : p.getVisibleExits()) {
				// That exit goes to somewhere that exists!
				if (!places.containsKey(x.getTarget())) {
					// Don't leave immediately, but check everything all at once.
					missing = true;
					// Print every exit with a missing place:
					System.err.println("Found exit pointing at " + x.getTarget() + " which does not exist as a place.");
				}
			}
		}

		// Now that we've checked every exit for every place, crash if we printed any
		// errors.
		if (missing) {
			throw new RuntimeException("You have some exits to nowhere!");
		}
	}

	/**
	 * Get a Place object by name.
	 */
	public Place getPlace(String id) {
		return this.places.get(id);
	}
}
