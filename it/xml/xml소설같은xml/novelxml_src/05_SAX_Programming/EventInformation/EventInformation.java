/**
Locator 객체를 이용해서 이벤트 발생 위치 정보를 출력하는 예제
**/
import javax.xml.parsers.*;
import org.xml.sax.*;

public class EventInformation {
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
	Locator locatorObj;
	
	public void startDocument() { }
	public void endDocument() { }
	public void startElement(String uri, String localName, String qName,
                                     Attributes atts) {
		System.out.print("startElement Event occurs in ");
		System.out.print(locatorObj.getLineNumber() + "th Line, ");
		System.out.print(locatorObj.getColumnNumber() + "th Column" + "\n");
	}
	public void endElement(String uri, String localName, String qName) { }
	public void characters(char[] ch, int start, int length) {
		System.out.print("characters Event occurs in ");
		System.out.print(locatorObj.getLineNumber() + "th Line, ");
		System.out.print(locatorObj.getColumnNumber() + "th Column" + "\n");
	}
	public void startPrefixMapping(String prefix, String uri) { }
	public void endPrefixMapping(String prefix) { }
	public void ignorableWhitespace(char[] ch, int start, int length) { }
	public void processingInstruction(String target, String data) { }
	public void setDocumentLocator(Locator locator) { 
		locatorObj = locator;
	}
	public void skippedEntity(String name) { }
}
/***
startElement Event occurs in 9th Line, 6th Column
startElement Event occurs in 10th Line, 17th Column
characters Event occurs in 10th Line, 20th Column
startElement Event occurs in 11th Line, 15th Column
characters Event occurs in 11th Line, 19th Column
***/