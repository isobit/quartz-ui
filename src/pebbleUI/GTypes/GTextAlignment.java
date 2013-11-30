package pebbleUI.GTypes;

/**
 * @author joshglendenning
 */
public enum GTextAlignment {

	LEFT	("Left"),
	RIGHT	("Right"),
	CENTER	("Center")
	;

	String key;
	GTextAlignment(String type) {
		this.key = "GTextAlignment"+type;
	}

	public static GTextAlignment getByType(String type) {
		switch (type.toLowerCase()) {
			case "left":
				return LEFT;
			case "right":
				return RIGHT;
			case "center":
				return CENTER;
		}
		return null;
	}

	public String getKey() {
		return key;
	}
}
