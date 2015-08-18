/**
DefaultHandler의 구현 코드
**/
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class ImpleDefaultHandler {
	public static void main(String[] args) {
		try {
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			saxParserFactory.setNamespaceAware(true);
			SAXParser saxParser = saxParserFactory.newSAXParser();

			DefaultHandler defaultHandler = new ImplHandler();
			saxParser.parse("SAXSample.xml", defaultHandler);
		} catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}
}

class ImplHandler extends DefaultHandler {
	public void startDocument() {
		System.out.println("XML Document Start!!");
	}
	public void endDocument() {
		System.out.println("XML Document End!!");
	}
}
/***
XML Document Start!!
XML Document End!!
***/