package pebbleUI.fonts;

/**
 * @author joshglendenning
 */
public class SystemFonts implements FontLibrary {

	//=== Singleton =======================//

	public static SystemFonts fonts;

	public static SystemFonts getSingleton() {
		if (fonts == null) {
			fonts = new SystemFonts();
		}
		return fonts;
	}

	//=== Enum =======================//

	public enum Fonts {
		GOTHIC_14,
		GOTHIC_14_BOLD,
		GOTHIC_18,
		GOTHIC_18_BOLD,
		GOTHIC_24,
		GOTHIC_24_BOLD,
		GOTHIC_28,
		GOTHIC_28_BOLD,
		BITHAM_30_BLACK,
		BITHAM_42_BOLD,
		BITHAM_42_LIGHT,
		BITHAM_42_MEDIUM_NUMBERS,
		BITHAM_34_MEDIUM_NUMBERS,
		BITHAM_34_LIGHT_SUBSET,
		BITHAM_18_LIGHT_SUBSET,
		ROBOTO_CONDENSED_21,
		ROBOTO_BOLD_SUBSET_49,
		DROID_SERIF_28_BOLD,
		;
		
		SystemFont font;
		
		Fonts() {
			this.font = new SystemFont(this.name());
		}
		
		public Font getFont() {
			return this.font;
		}
		
	}
	
	//=== Methods =======================//
	
	@Override
	public boolean contains(String id) {
		for (Fonts font : Fonts.values()) {
			if (id.equals(font.name())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Font get(String id) {
		if (contains(id)) {
			return Fonts.valueOf(id.toUpperCase()).getFont();
		}
		return null;
	}

}