package pebbleUI;

import org.w3c.dom.Element;
import pebbleUI.GTypes.GBitmap;
import pebbleUI.GTypes.GColor;
import pebbleUI.compiler.Line;
import pebbleUI.compiler.UIElement;

/**
 * @author joshglendenning
 */
public class ActionBarLayer implements UIElement {

	public static UIElement parse(Element e) {
		String id = e.getAttribute("id");
		ActionBarLayer elem = new ActionBarLayer(id);
		if (e.hasAttribute("bg-color")) {
			elem.bg_color = GColor.getByType(e.getAttribute("bg-color"));
		}
		if (e.hasAttribute("theme")) {
			String theme = e.getAttribute("theme");
			if (theme.equals("light")) {
				elem.bg_color = GColor.WHITE;
			}
			if (theme.equals("dark")) {
				elem.bg_color = GColor.BLACK;
			}
		}
		if (e.hasAttribute("up-icon")) {
			String icon = e.getAttribute("up-icon");
			elem.UP_icon = GBitmap.CustomBitmaps.getSingleton().get(icon);
		}
		if (e.hasAttribute("select-icon")) {
			String icon = e.getAttribute("select-icon");
			elem.SELECT_icon = GBitmap.CustomBitmaps.getSingleton().get(icon);
		}
		if (e.hasAttribute("down-icon")) {
			String icon = e.getAttribute("down-icon");
			elem.DOWN_icon = GBitmap.CustomBitmaps.getSingleton().get(icon);
		}
		return elem;
	}

	//=== Properties ===============//

	public String id;
	public GColor bg_color;
	public GBitmap UP_icon;
	public GBitmap SELECT_icon;
	public GBitmap DOWN_icon;

	//=== Constructors =============//

	public ActionBarLayer(String id) {
		this.id = id;
	}

	//=== Methods ==================//

	@Override
	public String getID() {
		return id;
	}

	@Override
	public String getDeclaration() {
		return Line.encapsulate("static ActionBarLayer *"+id);
	}

	//=== Creation =======================//

	private String init() {
		return Line.encapsulate(id+"=action_bar_layer_create()");
	}

	private String attach(String window) {
		return Line.encapsulate("action_bar_layer_add_to_window("+id+","+window+")");
	}

	private String setBGColor() {
		if (bg_color != null) {
			return Line.encapsulate("action_bar_layer_set_background_color("+id+","+bg_color.getKey()+")");
		}
		return "";
	}

	private String setButtonIcons() {
		String s = "";
		if (UP_icon != null) {
			s += Line.encapsulate("action_bar_layer_set_icon("+id+","+"BUTTON_ID_UP,"+UP_icon.getID()+")");
		}
		if (SELECT_icon != null) {
			s += Line.encapsulate("action_bar_layer_set_icon("+id+","+"BUTTON_ID_SELECT,"+SELECT_icon.getID()+")");
		}
		if (DOWN_icon != null) {
			s += Line.encapsulate("action_bar_layer_set_icon("+id+","+"BUTTON_ID_DOWN,"+DOWN_icon.getID()+")");
		}
		return s;
	}

	@Override
	public String create(UIElement parent) {
		// Parent should be window
		return init()
			+ setBGColor()
			+ setButtonIcons()
			+ attach(parent.getID())
			;
	}

	@Override
	public String destroy(UIElement parent) {
		return Line.encapsulate("action_bar_layer_destroy("+id+")");
	}


}
