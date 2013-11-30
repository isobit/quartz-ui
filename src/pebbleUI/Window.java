package pebbleUI;

import pebbleUI.GTypes.GColor;
import pebbleUI.compiler.UIElement;
import org.w3c.dom.Element;
import pebbleUI.compiler.Line;

/**
 * @author joshglendenning
 */
public class Window implements UIElement {

	public static UIElement parse(Element e) {
		String id = e.getAttribute("id");
		Window elem = new Window(id);

		if (e.hasAttribute("bg-color")) {
			elem.bg_color = GColor.getByType(e.getAttribute("bg-color"));
		}
		if (e.hasAttribute("animated")) {
			elem.animated = Boolean.parseBoolean(e.getAttribute("animated"));
		}
		if (e.hasAttribute("fullscreen")) {
			elem.fullscreen = Boolean.parseBoolean(e.getAttribute("fullscreen"));
		}

		return elem;
	}

	//=== Properties ===============//

	public String id;
	public GColor bg_color;
	public boolean animated;
	public boolean fullscreen =
		false;

	//=== Constructors =============//

	public Window(String name) {
		this.id = name;
	}

	//=== Methods ==================//

	private String set_fullscreen() {
		if (fullscreen) {
			return Line.encapsulate("window_set_fullscreen("+id+","+String.valueOf(fullscreen)+")");
		}
		return "";
	}

	private String set_bg_color() {
		return Line.encapsulate("window_set_background_color("+id+","+bg_color.getKey()+")");
	}

	private String stack_push() {
		return Line.encapsulate("window_stack_push("+id+","+String.valueOf(animated)+")");
	}

	@Override
	public String getID() {
		return id;
	}

	@Override
	public String create(UIElement parent) {
		return init() +
			set_bg_color() +
			set_fullscreen() +
			stack_push()
			;
	}

	@Override
	public String destroy(UIElement parent) {
		return Line.encapsulate("window_destroy("+id+")");
	}

	public String init() {
		return Line.encapsulate(id + " = " + "window_create()");
	}

	@Override
	public String getDeclaration() {
		return Line.encapsulate("static Window *" + id);
	}

}
