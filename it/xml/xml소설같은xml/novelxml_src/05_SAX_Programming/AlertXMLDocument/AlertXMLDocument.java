/**
startDocument()와 endDocument() 이벤트 메소드의 구현
**/
import javax.xml.parsers.*;
import org.xml.sax.*;

public class AlertXMLDocument {
	public static void main(String[] args) {
		try {
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
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
	public void startDocument() {
		System.out.println("XML Document Start!!");
	}
	public void endDocument() {
		System.out.println("XML Document End!!");
	}
	public void startElement(String uri, String localName, String qName,
                                     Attributes atts) { }
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
XML Document Start!!
XML Document End!!
***/