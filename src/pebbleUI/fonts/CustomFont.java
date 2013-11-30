package pebbleUI.fonts;

import org.w3c.dom.Element;
import pebbleUI.compiler.Line;
import pebbleUI.compiler.UIElement;

/**
 * @author joshglendenning
 */
public class CustomFont implements Font, UIElement {

	//=== Parser =======================//

	public static UIElement parse(Element e) {
		String id = e.getAttribute("id");
		String resource = e.getAttribute("resource-id");
		CustomFont elem = new CustomFont(id, resource);

		return elem;
	}

	//=== Properties ===============//

	String id;
	String resource_id;

	//=== Constructors =============//

	public CustomFont(String id, String resource) {
		this.id = id;
		this.resource_id = resource;
		CustomFonts.getSingleton().put(this);
	}

	//=== Methods ==================//

	@Override
	public String getKey() {
		return "RESOURCE_ID_FONT_"+resource_id;
	}

	@Override
	public String load() {
		return id;
	}

	@Override
	public String getID() {
		return id;
	}

	@Override
	public String getDeclaration() {
		return Line.encapsulate("static GFont "+id);
	}

	@Override
	public String create(UIElement parent) {
		return Line.encapsulate(id+" = "+"fonts_load_custom_font(resource_get_handle("+getKey()+"))");
	}

	@Override
	public String destroy(UIElement parent) {
		return Line.encapsulate("fonts_unload_custom_font("+id+")");
	}


}
