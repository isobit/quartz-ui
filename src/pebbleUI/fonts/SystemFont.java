package pebbleUI.fonts;

/**
 * @author joshglendenning
 */
public class SystemFont implements Font {

	//=== Properties ===============//

	String id;

	//=== Constructors =============//

	public SystemFont(String id) {
		this.id = id;
	}

	//=== Methods ==================//

	public String getID() {
		return id;
	}

	@Override
	public String getKey() {
		return "FONT_KEY_"+id;
	}

	@Override
	public String load() {
		return "fonts_get_system_font("+getKey()+")";
	}

}
