package edu.smith.cs.csc212.spooky;

/**
 * @author macbookpro
 * This is a locked exit that require users to have a key in their stuff to open search. 
 */
public class LockedExit extends Exit {

	String Key;

	/**
	 * @param target - where it goes.
	 * @param description - how it looks.
	 */
	public LockedExit(String target, String description, String key) {
		super(target, description);
		this.Key = key;
	}
	
	
	/**
	 * Can the player open this door?
	 * @param player - the player object (and all other state)
	 * @return true if it can be open, false if it can't.
	 */
	@Override
	public boolean canOpen(Player player) {
		if (player.getStuff().contains(Key)) {
			return true;
		 }
		else {
			return false;
		}
	}

}
