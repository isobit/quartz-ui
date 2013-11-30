package pebbleUI.GTypes;

/**
 * @author joshglendenning
 */
public enum GColor {

	CLEAR	("Clear"),
	BLACK	("Black"),
	WHITE	("White"),
	;

	String key;
	GColor(String type) {
		this.key = "GColor"+type;
	}

	public static GColor getByType(String type) {
		switch (type.toLowerCase()) {
			case "clear":
				return CLEAR;
			case "black":
				return BLACK;
			case "white":
				return WHITE;
		}
		return null;
	}

	public String getKey() {
		return key;
	}
}
