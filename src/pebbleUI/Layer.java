package pebbleUI;

import org.w3c.dom.Element;
import pebbleUI.GTypes.GColor;
import pebbleUI.compiler.Line;
import pebbleUI.compiler.UIElement;

/**
 * @author joshglendenning
 */
public class Layer implements UIElement {

	public static UIElement parse(Element e) {
		String id = e.getAttribute("id");
		Layer elem = new Layer(id);

		if (e.hasAttribute("width")) {
			String width = e.getAttribute("width");
			if (width.endsWith("%")) {
				elem.widthIsRelative = true;
				elem.width = Integer.parseInt(width.substring(0, width.length()-1));
			} else {
				elem.width = Integer.parseInt(e.getAttribute("width"));
			}
		}
		if (e.hasAttribute("height")) {
			elem.height = Integer.parseInt(e.getAttribute("height"));
		}
		if (e.hasAttribute("x")) {
			elem.x = Integer.parseInt(e.getAttribute("x"));
		}
		if (e.hasAttribute("y")) {
			elem.y = Integer.parseInt(e.getAttribute("y"));
		}

		return elem;
	}

	//=== Properties ===============//

	public String id;
	public int x
		= 0;
	public int y
		= 0;
	public int width;
	public boolean widthIsRelative
		= false;
	public int height;

	//=== Constructors =============//

	public Layer(String id) {
		this.id = id;
	}

	//=== Methods ==================//

	private String attach(String window) {
		return Line.encapsulate(
			"layer_add_child(window_get_root_layer("+window+"), "+id+")"
		);
	}

	private String getGRect(String window) {
		String result = "(GRect)" +
			"{" +
			".origin={"+x+","+y+"},";
		if (widthIsRelative) {
			result += ".size={"+
				"(int)layer_get_frame(window_get_root_layer("+window+")).size.w * ((float)"+width+" / 100)"
				+","+
				height
				+"}}";
		} else {
			result += ".size={"+
				width
				+","+
				height
				+"}}";
		}
		return result;
	}

	@Override
	public String create(UIElement parent) {
		// Parent should be window
		return 
			init(parent.getID()) + 
			attach(parent.getID())
			;
	}

	@Override
	public String destroy(UIElement parent) {
		return Line.encapsulate("layer_destroy("+id+")");
	}

	public String init(String window) {
		return Line.encapsulate(id + " = " + "layer_create(" + getGRect(window) + ")");
	}

	@Override
	public String getDeclaration() {
		return Line.encapsulate("static Layer *"+id);
	}

	@Override
	public String getID() {
		return id;
	}

}
