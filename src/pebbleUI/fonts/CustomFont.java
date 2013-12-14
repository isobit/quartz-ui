package pebbleUI.fonts;

import org.w3c.dom.Element;
import pebbleUI.ResourceName;
import pebbleUI.compiler.Line;
import pebbleUI.compiler.UIElement;

/**
 * @author joshglendenning
 */
public class CustomFont implements Font, UIElement {

	//=== Parser =======================//

	public static UIElement parse(Element e) {
		String resource = e.getAttribute("resource-name");
		String id = e.getAttribute("id");
		CustomFont elem = new CustomFont(id, resource);

		return elem;
	}

	//=== Properties ===============//

	String id;
	ResourceName name;

	//=== Constructors =============//

	public CustomFont(String id, String resource) {
		this.id = id;
		this.name = new ResourceName(resource);
		CustomFonts.getSingleton().put(this);
	}

	//=== Methods ==================//

	@Override
	public String getKey() {
		return name.getKey();
	}

	@Override
	public String load() {
		return getID();
	}

	@Override
	public String getID() {
		return id;
	}

	@Override
	public String getDeclaration() {
		return Line.encapsulate("static GFont "+getID());
	}

	@Override
	public String create(UIElement parent) {
		return Line.encapsulate(getID()+" = "+"fonts_load_custom_font(resource_get_handle("+getKey()+"))");
	}

	@Override
	public String destroy(UIElement parent) {
		return Line.encapsulate("fonts_unload_custom_font("+getID()+")");
	}


}
