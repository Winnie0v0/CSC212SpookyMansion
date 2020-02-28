package edu.smith.cs.csc212.spookycz;

/**
 * This exit only show at nighttime
 * @author macbookpro
 */
public class NightExit extends Exit{
	
	GameTime time = new GameTime();

	/**
	 * 
	 * @param target see in Exit
	 * @param description see in Exit
	 */
	public NightExit(String target, String description) {
		super(target, description);
	}
	
	/**
	 * This is a secret exit that only open during the day.
	 */
	@Override
	public boolean isSecret() {
		if (!time.isNightTime()) {
			return true;
		}
		else {
			return false;
		}
	} 

}
