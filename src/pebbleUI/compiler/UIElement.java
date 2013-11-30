package pebbleUI.compiler;

/**
 * @author joshglendenning
 */
public interface UIElement {

	//=== Methods ==================//

	public String getID();

	public String getDeclaration();

//	public String init();

	public String create(UIElement parent);

	public String destroy(UIElement parent);

}
