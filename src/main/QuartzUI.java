package main;

import pebbleUI.compiler.UINode;
import pebbleUI.compiler.UITree;
import pebbleUI.compiler.UIElement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pebbleUI.ActionBarLayer;
import pebbleUI.GTypes.GBitmap;
import pebbleUI.TextLayer;
import pebbleUI.Window;
import pebbleUI.fonts.CustomFont;
import pebbleUI.fonts.SystemFonts;

/**
 *
 * @author joshglendenning
 */
public class QuartzUI {
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		QuartzUI app = new QuartzUI();

		// Get inputted xml
		File inputFile = new File(args[0]);

		// Write to ui.h
		PrintWriter writer = new PrintWriter("src/ui.h", "UTF-8");
		writer.write(
			app.parseXML(inputFile).compile()
		);
		writer.close();

	}
	
	//=== Methods ==================//

	public UITree parseXML(File xmlfile) {

		UITree tree = null;
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlfile);
			doc.getDocumentElement().normalize();

			Node rootNode = doc.getDocumentElement();

			UINode root = parseNode(rootNode);
			tree = new UITree(root);

			if (rootNode.hasChildNodes()) {
				NodeList nodes = rootNode.getChildNodes();
				for (int i = 0; i < nodes.getLength(); i++) {
					Node node = nodes.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						root.attach(parseNode(node));
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tree;
	}

	public UINode parseNode(Node node) {
		UIElement elem = null;

		Element e = (Element) node;
		switch (e.getTagName()) {
			case "action_bar_layer":
				elem = ActionBarLayer.parse(e);
				break;
			case "custom_font":
				elem = CustomFont.parse(e);
				break;
			case "custom_bitmap":
				elem = GBitmap.parse(e);
				break;
			case "text_layer":
				elem = TextLayer.parse(e);
				break;
			case "window":
				elem = Window.parse(e);
				break;
		}

		return new UINode(elem);
	}
	
	
}
