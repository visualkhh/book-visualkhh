/**
DeclHandler 구현 예제
**/
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.ext.*;

public class ExtDeclHandler {
	public static void main(String[] args) {
		try {
			String DECL_URI = "http://xml.org/sax/properties/declaration-handler";
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser saxParser = saxParserFactory.newSAXParser();
			XMLReader xmlReader = saxParser.getXMLReader();
			
			ImplHandler handler = new ImplHandler();
			xmlReader.setProperty(DECL_URI, handler);
			xmlReader.parse("SAXSample.xml");			
		} catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}
}

class ImplHandler implements DeclHandler {
	public void elementDecl(String name, String model) {
		System.out.println("<!ELEMENT " + name + " " + model + ">");
	}
	public void attributeDecl(String eName, String aName, String type,
                                      String mode, String value) {
		System.out.print("<!ATTLIST " + eName + " " + aName + " ");
		System.out.print(type + " " + mode + " " + value + ">\n");
	}
	public void internalEntityDecl(String name, String value) {
		System.out.println("<!ENTITY " + name + " \"" + value + "\">");
	}
	public void externalEntityDecl(String name, String publicId,
                                            String systemId) {
		System.out.print("<!ENTITY " + name + " ");
		System.out.print(publicId + " " + systemId + ">\n");
	}
}
/***
<!ELEMENT booklist (book+)>
<!ELEMENT book (name,authors,page,publish?,price)>
<!ELEMENT name (#PCDATA)>
<!ELEMENT authors (author+)>
<!ELEMENT author (#PCDATA)>
<!ELEMENT page (#PCDATA)>
<!ELEMENT publish EMPTY>
<!ELEMENT price (#PCDATA)>
<!ATTLIST book isbn ID #REQUIRED null>
<!ATTLIST name outpage CDATA #IMPLIED null>
<!ATTLIST publish year CDATA #IMPLIED null>
<!ATTLIST publish month CDATA #IMPLIED null>
<!ATTLIST publish day CDATA #IMPLIED null>
<!ATTLIST price unit CDATA null won>
<!ENTITY bookname "XML과 XML Web Services">
***/