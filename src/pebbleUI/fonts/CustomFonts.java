package pebbleUI.fonts;

import java.util.HashMap;
import java.util.Map;

/**
 * @author joshglendenning
 */
public class CustomFonts implements FontLibrary {

	//=== Singleton =======================//

	public static CustomFonts fonts;

	public static CustomFonts getSingleton() {
		if (fonts == null) {
			fonts = new CustomFonts();
		}
		return fonts;
	}

	//=== Properties ===============//

	private Map<String, CustomFont> map;

	//=== Constructors =============//

	private CustomFonts() {
		this.map = new HashMap<>();
	}

	//=== Methods ==================//

	public void put(CustomFont font) {
		map.put(font.getID(), font);
	}

	@Override
	public boolean contains(String id) {
		return map.containsKey(id);
	}

	@Override
	public CustomFont get(String id) {
		return map.get(id);
	}

}
