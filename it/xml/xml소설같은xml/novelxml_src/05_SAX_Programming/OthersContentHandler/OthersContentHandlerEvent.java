/**
ContentHandler의 기타 이벤트 메소드 작성 예제
**/
import javax.xml.parsers.*;
import org.xml.sax.*;

public class OthersContentHandlerEvent {
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
	public void startPrefixMapping(String prefix, String uri) {
		System.out.print("Namespace Prefix : " + prefix);
		System.out.print(", Namespace URI : " + uri + "\n");
	}
	public void endPrefixMapping(String prefix) {
		System.out.println(prefix + " Namespace is the End");
	}
	public void ignorableWhitespace(char[] ch, int start, int length) { }
	public void processingInstruction(String target, String data) {
		System.out.println("Processing Instruction");
		System.out.println("	Target : " + target);
		System.out.println("	Data : " + data);
	}
	public void setDocumentLocator(Locator locator) { }
	public void skippedEntity(String name) {
		System.out.println("Skipped Entity");
	}
}
/***
XML Document Start!!
Processing Instruction
        Target : xml-stylesheet
        Data : type="text/xsl" href="booklist.xsl"
Namespace Prefix : book, Namespace URI : http://www.xmlcommunity.net
book Namespace is the End
XML Document End!!
***/