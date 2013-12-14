package pebbleUI;

/**
 * @author joshglendenning
 */
public class ResourceName {

	//=== Properties ===============//

	public String id;

	//=== Constructors =============//

	public ResourceName(String id) {
		this.id = id;
	}

	//=== Methods ==================//

	public String getKey() {
		return "RESOURCE_ID_"+id;
	}

	public String getID() {
		return id;
	}

}
