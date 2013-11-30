package pebbleUI.GTypes;

import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Element;
import pebbleUI.ResourceID;
import pebbleUI.compiler.Line;
import pebbleUI.compiler.UIElement;

/**
 * @author joshglendenning
 */
public class GBitmap implements UIElement {

	public static UIElement parse(Element e) {
		String id = e.getAttribute("id");
		String resource = e.getAttribute("resource-id");
		GBitmap elem = new GBitmap(id, resource);
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
			bitmaps.put(bitmap.id, bitmap);
		}
		public boolean contains(String id) {
			return bitmaps.containsKey(id);
		}
		public GBitmap get(String id) {
			return bitmaps.get(id);
		}
	}

	//=== Properties ===============//

	public String id;
	public ResourceID resource_id;

	//=== Constructors =============//

	public GBitmap(String id, String resource_id) {
		this.id = id;
		this.resource_id = new ResourceID(resource_id);
		CustomBitmaps.getSingleton().put(this);
	}

	//=== Methods ==================//

	public String getKey() {
		return resource_id.getID();
	}

	public String getID() {
		return id;
	}

	@Override
	public String getDeclaration() {
		return Line.encapsulate("static GBitmap *"+id);
	}

	//=== Creation =======================//

	private String init() {
		return Line.encapsulate(id+"=gbitmap_create_with_resource("+getKey()+")");
	}

	@Override
	public String create(UIElement parent) {
		return init()
			;
	}

	@Override
	public String destroy(UIElement parent) {
		return Line.encapsulate("gbitmap_destroy("+id+")");
	}

}
