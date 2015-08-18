import javax.xml.parsers.*;
import org.w3c.dom.*;

public class ExtractValue {
	public static void main(String[] args) {
		try {
			DocumentBuilderFactory dBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dBuilderFactory.newDocumentBuilder();

			Document document = dBuilder.parse("DOMSample.xml");
			
			Element rootElement = document.getDocumentElement();
			
			System.out.println("Root Element's Value : " + rootElement.getTextContent());
		} catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}
}