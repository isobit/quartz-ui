package pebbleUI.compiler;

/**
 * @author joshglendenning
 */
public class Line {

	//=== Properties ===============//

	//=== Constructors =============//

	//=== Methods ==================//

	public static String encapsulate(String str) {
		String s = str;
		if (!str.trim().endsWith(";")) {
			s += ";";
		}
		return s + System.getProperty("line.separator");
	}

	public static String newline() {
		return System.getProperty("line.separator");
	}

}
