package pebbleUI;

/**
 * @author joshglendenning
 */
public class ResourceID {

	//=== Properties ===============//

	public String id;

	//=== Constructors =============//

	public ResourceID(String id) {
		this.id = id;
	}

	//=== Methods ==================//

	public String getID() {
		return "RESOURCE_ID_"+id;
	}

}
