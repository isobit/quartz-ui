package pebbleUI.compiler;

import java.util.LinkedList;
import java.util.List;
import pebbleUI.compiler.Line;

/**
 * @author joshglendenning
 */
public class UINode {

	//=== Properties ===============//

	List<UINode> children;
	UIElement element;

	//=== Constructors =============//

	public UINode(UIElement element) {
		this.element = element;
		this.children = new LinkedList<>();
	}

	//=== Methods =======================//

	public void attach(UINode node) {
		children.add(node);
	}

	public String create(UIElement parent) {
//		String result = Line.encapsulate(element.compile(parent));
		String result = element.create(parent);
		for (UINode child : children) {
//			result += Line.encapsulate(child.compile(element));
			result += child.create(element);
		}
		return result;
	}

	public String destroy(UIElement parent) {
//		String result = Line.encapsulate(element.compile(parent));
		String result = element.destroy(parent);
		for (UINode child : children) {
//			result += Line.encapsulate(child.compile(element));
			result += child.destroy(element);
		}
		return result;
	}

	public String getDeclarations() {
		String result = element.getDeclaration();
		for (UINode child : children) {
			result += child.getDeclarations();
		}
		return result;
	}


}
