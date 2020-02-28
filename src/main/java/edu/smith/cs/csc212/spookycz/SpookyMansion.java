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
				Place.create("entranceHall", "You are in the grand entrance hall of a large building.\n"
						+ "The front door is locked. How did you get here?"));
		entranceHall.addExit(new Exit("basement", "There are stairs leading down."));
		entranceHall.addExit(new Exit("attic", "There are stairs leading up."));
		entranceHall.addExit(new Exit("kitchen", "There is a red door."));
		entranceHall.addExit(new Exit("closet", "There is a brown door."));
		// Stuffs in the entrance hall
		entranceHall.addStuffs("a small key", "There is a small key.");

		String EMOJI_SKULL = "\uD83D\uDC80";
		Place closet = insert(Place.create("closet", "On the wall is scratched a series of letters and a skull icon ("+EMOJI_SKULL+").\n"
				+ "North.. North.. East.. South.\n" 
				+ "What could it mean?"));
		closet.addExit(new Exit("entranceHall", "Go back."));

		Place basement = insert(
				Place.create("basement", "You have found the basement of the mansion.\n" + "It is darker down here.\n"
						+ "You get the sense a secret is nearby, but you only see the stairs you came from."));
		basement.addExit(new Exit("entranceHall", "There are stairs leading up."));
		basement.addExit(new Exit("fallingPit", "There appears to be a pit in the center of the room you could climb into..."));
		basement.addExit(new Exit("storageRoom", "There is a wood door with a word 'FOOD' on it."));
		// Secret exit added.
		basement.addSecretExit(new SecretExit("secretRoom", "There is a trap door that was obscured by dust!"));
		
		Place fallingPit = insert(
				Place.create("fallingPit", "I don't know what you were thinking..."));
		fallingPit.addExit(new Exit("labyrinth0", "Keep falling."));

		Place attic = insert(Place.create("attic",
				"Something rustles in the rafters as you enter the attic. Creepy.\n" + "It's big up here."));
		attic.addExit(new Exit("entranceHall", "There are stairs leading down."));
		attic.addExit(new Exit("attic2", "There is more through an archway."));

		// The description of this place change at night
		NightPlace attic2 = insert(NightPlace.create("attic2", 
				"There's definitely a bat in here somewhere.\n"
				+ "This part of the attic is brighter, so maybe you're safe here.",
				"There's definitely a bat in here somewhere.\n"
				+ "Moon light shines in so it is brighter. You maybe safe here."));
		attic2.addExit(new Exit("attic", "There is more back through the archway."));
		attic2.addExit(new Exit("balcony", "There is a balcony."));
		attic2.addExit(new Exit("dumbwaiter", "There is a dumbwaiter."));
		attic2.addExit(new Exit("rooftop", "There is more stairs leading up"));
		
		// The description of this place change at night
		NightPlace balcony = insert(NightPlace.create("balcony", "The sun is covered by the cloud", "The night is pitch-black."));
		balcony.addExit(new Exit("attic2", "Return to the attic."));
		balcony.addExit(new Exit("jump", "You could jump off, but you can't see the ground."));

		Place jump = insert(Place.terminal("jump", "I wonder what you expected to happen here."));
		
		Place kitchen = insert(
				Place.create("kitchen", "You've found the kitchen. You smell old food and some kind of animal."));
		kitchen.addExit(new Exit("entranceHall", "There is a red door."));
		kitchen.addExit(new Exit("dumbwaiter", "There is a dumbwaiter."));

		Place dumbwaiter = insert(Place.create("dumbwaiter", "You crawl into the dumbwaiter. What are you doing?"));
		dumbwaiter.addExit(new Exit("secretRoom", "Take it to the bottom."));
		dumbwaiter.addExit(new Exit("kitchen", "Take it to the middle-level."));
		dumbwaiter.addExit(new Exit("attic2", "Take it up to the top."));

		Place secretRoom = insert(Place.create("secretRoom", "You have found the secret room."));
		secretRoom.addExit(new Exit("labyrinth0", "There is door with a skull on it... "+EMOJI_SKULL));
		// A locked exit.
		secretRoom.addLockedExit(new LockedExit("hallway0","There is a locked door.","a small key"));
		secretRoom.addExit(new Exit("basement", "There is a old wood door."));
		secretRoom.addExit(new Exit("dumbwaiter", "There is a dumbwaiter."));
				
		// The description of this place change at night
		NightPlace rooftop = insert(NightPlace.create("rooftop", 
				"You have found the rooftop. You breath the fresh air and feel the sun.", 
				"You have found the rooftop. The moon looks creepy and it is cold out there"));
		rooftop.addExit(new Exit("attic2", "Go back."));
		
		Place storageRoom = insert(Place.create("storageRoom", "You have found the storage room. "
				+ "There is food and water in the room but you don't seem to need it now"));
		storageRoom.addExit(new Exit("basement", "Go back."));

		int hallwayDepth = 5;
		int lastHallwayPart = hallwayDepth - 1;
		for (int i = 0; i < hallwayDepth; i++) {
			int depth = hallwayDepth-i;
			Place hallwayPart = insert(Place.create("hallway" + i, "This is a very long hallway. You see some number " 
		+ depth + " scratched on the wall but you don't know what it means..."));
			if (i == 0) {
				hallwayPart.addExit(new Exit("secretRoom", "Go back."));
			} else {
				hallwayPart.addExit(new Exit("hallway" + (i - 1), "Go back."));
			}
			if (i != lastHallwayPart) {
				hallwayPart.addExit(new Exit("hallway" + (i + 1), "Go forward."));
			} else {
				hallwayPart.addExit(new Exit("crypt", "There is darkness ahead."));
			}
		}

		Place crypt = insert(Place.terminal("crypt", "You have found the crypt.\n"
				+ "It is scary here, but there is an exit to outside.\n" + "Maybe you'll be safe out there."));

		String labyrinthDescription = "You see four hallways stretching out into the mist.\n"
				+ "On the ground, there is tile shaped like a compass.";
		Place labyrinth0 = insert(Place.create("labyrinth0", labyrinthDescription));
		Place labyrinth1 = insert(Place.create("labyrinth1", labyrinthDescription));
		Place labyrinth2 = insert(Place.create("labyrinth2", labyrinthDescription));
		Place labyrinth3 = insert(Place.create("labyrinth3", labyrinthDescription));
		
		// solution: North.
		labyrinth0.addExit(new Exit("labyrinth1", "Go North."));
		labyrinth0.addExit(new Exit("labyrinth0", "Go East."));
		labyrinth0.addExit(new Exit("labyrinth0", "Go South."));
		labyrinth0.addExit(new Exit("labyrinth0", "Go West."));
		
		// solution: North.
		labyrinth1.addExit(new Exit("labyrinth2", "Go North."));
		labyrinth1.addExit(new Exit("labyrinth0", "Go East."));
		labyrinth1.addExit(new Exit("labyrinth0", "Go South."));
		labyrinth1.addExit(new Exit("labyrinth0", "Go West."));
		
		// solution: East.
		labyrinth2.addExit(new Exit("labyrinth0", "Go North."));
		labyrinth2.addExit(new Exit("labyrinth3", "Go East."));
		labyrinth2.addExit(new Exit("labyrinth0", "Go South."));
		labyrinth2.addExit(new Exit("labyrinth0", "Go West."));
		
		// solution: South.
		labyrinth3.addExit(new Exit("labyrinth0", "Go North."));
		labyrinth3.addExit(new Exit("labyrinth3", "Go East."));
		labyrinth3.addExit(new Exit("entranceHall", "Go South."));	
		labyrinth3.addExit(new Exit("labyrinth0", "Go West."));
		



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