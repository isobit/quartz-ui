package pebbleUI.GTypes;

/**
 * @author joshglendenning
 */
public class GPoint {

	//=== Properties ===============//

	public int x;
	public int y;

	//=== Constructors =============//

	public GPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}

	//=== Methods ==================//

	public String load() {
		return "(GPoint) {.x="+x+".y="+y+"}";
	}

}
