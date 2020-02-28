package edu.smith.cs.csc212.spooky;

/**
 * @author macbookpro
 * This is a secret exit that require users to search. 
 */
public class SecretExit extends Exit {

	private boolean hidden = true;

	/**
	 * @param target - where it goes.
	 * @param description - how it looks.
	 */
	public SecretExit(String target, String description) {
		super(target, description);
	}
	
	/**
	 * This is a secret exit that require the player to search.
	 */
	@Override
	public boolean isSecret() {
		if (hidden) {
			return true;
		}
		else {
			return false;
		}
	} 	
	
	/**
	 * The status change after a player search.
	 * @param searched 
	 */
	@Override
	public void search() {
		this.hidden = false;
	}

}
