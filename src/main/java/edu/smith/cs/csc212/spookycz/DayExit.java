package edu.smith.cs.csc212.spookycz;

/**
 * This exit only show at daytime
 * @author macbookpro
 */
public class DayExit extends Exit{
	
	GameTime time = new GameTime();

	/**
	 * @param target see Exit
	 * @param description see Exit
	 */
	public DayExit(String target, String description) {
		super(target, description);
	}
	
	/**
	 * This is a secret exit that only open during the day.
	 */
	@Override
	public boolean isSecret() {
		if (time.isNightTime()) {
			return true;
		}
		else {
			return false;
		}
	} 

}
