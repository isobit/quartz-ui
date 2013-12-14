package pebbleUI.GTypes;

import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Element;
import pebbleUI.ResourceName;
import pebbleUI.compiler.Line;
import pebbleUI.compiler.UIElement;

/**
 * @author joshglendenning
 */
public class GBitmap implements UIElement {

	public static UIElement parse(Element e) {
		String resource = e.getAttribute("resource-name");
		GBitmap elem = new GBitmap(resource);
		return elem;
	}

	//=== Static Tracking =======================//

	public static class CustomBitmaps {
		private static CustomBitmaps instance;
		public static CustomBitmaps getSingleton() {
			if (instance == null) {
				instance = new CustomBitmaps();
			}
			return instance;
		}

		private Map<String, GBitmap> bitmaps;
		private CustomBitmaps() {
			this.bitmaps = new HashMap<>();
		}
		public void put(GBitmap bitmap) {
			bitmaps.put(bitmap.getID(), bitmap);
		}
		public boolean contains(String id) {
			return bitmaps.containsKey(id);
		}
		public GBitmap get(String id) {
			return bitmaps.get(id);
		}
	}

	//=== Properties ===============//

	public ResourceName resource;

	//=== Constructors =============//

	public GBitmap(String resource_id) {
		this.resource = new ResourceName(resource_id);
		CustomBitmaps.getSingleton().put(this);
	}

	//=== Methods ==================//

	public String getKey() {
		return resource.getKey();
	}

	@Override
	public String getID() {
		return resource.getID();
	}

	@Override
	public String getDeclaration() {
		return Line.encapsulate("static GBitmap *"+getID());
	}

	//=== Creation =======================//

	private String init() {
		return Line.encapsulate(getID()+"=gbitmap_create_with_resource("+getKey()+")");
	}

	@Override
	public String create(UIElement parent) {
		return init()
			;
	}

	@Override
	public String destroy(UIElement parent) {
		return Line.encapsulate("gbitmap_destroy("+getID()+")");
	}

}
