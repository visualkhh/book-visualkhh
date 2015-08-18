import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.io.File;

public class OutputProperty {
	public static void main(String[] args) {
		try {
			Source xmlSource = new StreamSource("catalog.xml");
			Source xsltSource = new StreamSource("catalog.xsl");
			
			Result htmlResult = new StreamResult("catalog.html");
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer(xsltSource);
			
			transformer.setOutputProperty(OutputKeys.METHOD, "html");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			
			transformer.transform(xmlSource, htmlResult);
		} catch(Exception e) {
		}
	}
}