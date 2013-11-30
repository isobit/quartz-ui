package pebbleUI;

import pebbleUI.GTypes.GColor;
import pebbleUI.GTypes.GTextAlignment;
import pebbleUI.compiler.UIElement;
import org.w3c.dom.Element;
import pebbleUI.compiler.Line;
import pebbleUI.fonts.Font;
import pebbleUI.fonts.Fonts;

/**
 * @author joshglendenning
 */
public class TextLayer implements UIElement {

	public static UIElement parse(Element e) {
		String id = e.getAttribute("id");
		TextLayer elem = new TextLayer(id);

		if (e.hasAttribute("text-color")) {
			elem.text_color = GColor.getByType(e.getAttribute("text-color"));
		}
		if (e.hasAttribute("bg-color")) {
			elem.bg_color = GColor.getByType(e.getAttribute("bg-color"));
		}
		if (e.hasAttribute("theme")) {
			String theme = e.getAttribute("theme");
			if (theme.equals("light")) {
				elem.text_color = GColor.BLACK;
				elem.bg_color = GColor.WHITE;
			}
			if (theme.equals("dark")) {
				elem.text_color = GColor.WHITE;
				elem.bg_color = GColor.BLACK;
			}
		}
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
		if (e.hasAttribute("font")) {
			String name = e.getAttribute("font");
			elem.font = Fonts.parse(name);
		}
		

		return elem;
	}

	//=== Properties ===============//

	String font_setter = "text_layer_set_font";

	public String id;
	public int x
		= 0;
	public int y
		= 0;
	public int width;
	public boolean widthIsRelative
		= false;
	public int height;
	public Font font;
	public GColor bg_color;
	public GColor text_color;
	public GTextAlignment alignment;

	//=== Constructors =============//

	public TextLayer(String name) {
		this.id = name;
	}

	//=== Methods ==================//

	//=== Code Generators =======================//

	private String attach(String window) {
		return Line.encapsulate(
			"layer_add_child(window_get_root_layer("+window+"), text_layer_get_layer("+id+"))"
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

	private String setTextColor() {
		if (text_color != null) {
			return Line.encapsulate(
				"text_layer_set_text_color(" + id + "," + text_color.getKey() +")"
			);
		}
		return "";
	}

	private String setBGColor() {
		if (bg_color != null) {
			return Line.encapsulate(
				"text_layer_set_background_color(" + id + "," + bg_color.getKey() +")"
			);
		}
		return "";
	}

	private String setFont() {
		if (font != null) {
			return Line.encapsulate(
				font_setter + "(" + id + "," + font.load() + ")"
			);
		}
		return "";
	}

	//=== Element Methods =======================//

	@Override
	public String getID() {
		return id;
	}

	@Override
	public String create(UIElement parent) {
		// Parent should be window
		return 
			init(parent.getID()) + 
			setFont() + 
			setTextColor() +
			setBGColor() +
			attach(parent.getID())
			;
	}

	@Override
	public String destroy(UIElement parent) {
		return Line.encapsulate("text_layer_destroy("+id+")");
	}

	public String init(String window) {
		return Line.encapsulate(id + " = " + "text_layer_create(" + getGRect(window) + ")");
	}

	@Override
	public String getDeclaration() {
		return Line.encapsulate("static TextLayer *"+id);
	}

}
