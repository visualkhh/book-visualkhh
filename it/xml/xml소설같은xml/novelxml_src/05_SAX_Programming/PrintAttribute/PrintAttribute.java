/**
Attributes 인터페이스를 이용해서 어트리뷰트를 출력하는 예제
**/
import javax.xml.parsers.*;
import org.xml.sax.*;

public class PrintAttribute {
	public static void main(String[] args) {
		try {
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			saxParserFactory.setNamespaceAware(true);
			SAXParser saxParser = saxParserFactory.newSAXParser();
			XMLReader xmlReader = saxParser.getXMLReader();

			ImplHandler contentHandler = new ImplHandler();
			xmlReader.setContentHandler(contentHandler);
			xmlReader.parse("SAXSample.xml");
		} catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}
}

class ImplHandler implements ContentHandler {
	public void startDocument() { }
	public void endDocument() { }
	public void startElement(String uri, String localName, String qName,
                                     Attributes atts) {
		if(atts.getLength() != 0 ) {
			System.out.println(qName + " Element's Attributes : ");
			
			for(int i = 0 ; i < atts.getLength() ; i++) {
				System.out.print("	" + (i+1) + "'th name is " + atts.getQName(i));
				System.out.print(", value is " + atts.getValue(i) + "\n");
			}
		}
	}
	public void endElement(String uri, String localName, String qName) { }
	public void characters(char[] ch, int start, int length) { }
	public void startPrefixMapping(String prefix, String uri) { }
	public void endPrefixMapping(String prefix) { }
	public void ignorableWhitespace(char[] ch, int start, int length) { }
	public void processingInstruction(String target, String data) { }
	public void setDocumentLocator(Locator locator) { }
	public void skippedEntity(String name) { }
}
/***
book Element's Attributes :
        1'th Attribute name is isbn, value is h0-596-00762-0
book:name Element's Attributes :
        1'th Attribute name is outpage, value is ProgrammingNETComponents.gif
book:publish Element's Attributes :
        1'th Attribute name is year, value is 2005
        2'th Attribute name is month, value is 06
book:price Element's Attributes :
        1'th Attribute name is unit, value is won
***/