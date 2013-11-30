package pebbleUI.fonts;

/**
 * @author joshglendenning
 */
public class Fonts {

	public static Font parse(String name) {
		Font font = null;
		if (SystemFonts.getSingleton().contains(name)) {
			font = SystemFonts.getSingleton().get(name);
		}
		if (CustomFonts.getSingleton().contains(name)) {
			font = CustomFonts.getSingleton().get(name);
		}
		return font;
	}
}
