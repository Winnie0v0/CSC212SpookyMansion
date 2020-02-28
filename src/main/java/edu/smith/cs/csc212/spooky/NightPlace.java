package edu.smith.cs.csc212.spooky;

/**
 * The night place has their own description that work when it is night time.
 * @author macbookpro
 */
public class NightPlace extends Place {
	
	String descriptionNight;
	String description;

	protected NightPlace(String id, String description, boolean terminal, String descriptionNight) {
		super(id, description, terminal);
		this.descriptionNight = descriptionNight;
		this.description = description;
	}
	
	/**
	 * The narrative description of this place.
	 * @param Time determine whether it is day or night.
	 * @return what we show to a player about this place.
	 */
	@Override
	public String getDescription(GameTime Time) {
		if (Time.isNightTime()) {
			return this.descriptionNight;
		}
		else {
			return description;
		}	
	}
	
	/**
	 * Create a place with an id and description.
	 * @param id - this is the id of the place (for creating {@link Exit} objects that go here).
	 * @param description - this is what we show to the user.
	 * @return the new Place object (add exits to it).
	 */
	public static NightPlace create(String id, String description, String descriptionNight) {
		return new NightPlace(id, description, false, descriptionNight);
	}

}
