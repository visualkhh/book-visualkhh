/**
startElement()와 endElement()를 이용한 엘리먼트 출력하기 코드
**/
import javax.xml.parsers.*;
import org.xml.sax.*;

public class PrintElement {
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
		System.out.print("Start Element's localName is <" + localName + ">,");
		System.out.print(" and qName is <" + qName + ">\n");
	}
	public void endElement(String uri, String localName, String qName) {
		System.out.print("End element's localName is </" + localName + ">,");
		System.out.print(" and qName is </" + qName + ">\n");
	}
	public void characters(char[] ch, int start, int length) { }
	public void startPrefixMapping(String prefix, String uri) { }
	public void endPrefixMapping(String prefix) { }
	public void ignorableWhitespace(char[] ch, int start, int length) { }
	public void processingInstruction(String target, String data) { }
	public void setDocumentLocator(Locator locator) { }
	public void skippedEntity(String name) { }
}
/***
Start Element's localName is <booklist>, and qName is <booklist>
Start Element's localName is <book>, and qName is <book>
Start Element's localName is <name>, and qName is <book:name>
End element's localName is </name>, and qName is </book:name>
Start Element's localName is <authors>, and qName is <book:authors>
Start Element's localName is <author>, and qName is <author>
End element's localName is </author>, and qName is </author>
End element's localName is </authors>, and qName is </book:authors>
... 이하 생략 ...
***/