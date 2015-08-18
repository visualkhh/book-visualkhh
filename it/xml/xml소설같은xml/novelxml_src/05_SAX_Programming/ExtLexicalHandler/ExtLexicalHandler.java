/**
LexicalHandler의 구현 예제
**/
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.ext.*;

public class ExtLexicalHandler {
	public static void main(String[] args) {
		try {
			String LEXICAL_URI = "http://xml.org/sax/properties/lexical-handler";
			
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser saxParser = saxParserFactory.newSAXParser();
			XMLReader xmlReader = saxParser.getXMLReader();
			
			ImplHandler handler = new ImplHandler();
			xmlReader.setProperty(LEXICAL_URI, handler);
			xmlReader.parse("SAXSample.xml");			
		} catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}
}

class ImplHandler implements LexicalHandler {
	public void startDTD(String name, String publicId, String systemId) {
		System.out.println("Start DTD");
		System.out.println("	Document Name : " + name);
		System.out.println("	Document Public URI : " + publicId);
		System.out.println("	Document System URI : " + systemId);
	}
	public void endDTD() {
		System.out.println("End DTD");
	}
	public void startEntity(String name) {
		System.out.println(name + " Entity Start");
	}
	public void endEntity(String name)  {
		System.out.println(name + " Entity End");
	}
	public void startCDATA() {
		System.out.println("Start CDATA Section");
	}
	public void endCDATA() {
		System.out.println("End CDATA Section");
	}
	public void comment(char[] ch, int start, int length) {
		String commentString = new String(ch, start, length);
		System.out.println("Comment : " + commentString);
	}
}
/***
Start DTD
        Document Name : booklist
        Document System URI : SAXSample.dtd
[dtd] Entity Start
[dtd] Entity End
End DTD
Comment :  책 정보에 관한 XML
bookname Entity Start
bookname Entity End
Start CDATA Section
End CDATA Section
***/