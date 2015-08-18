/**
XML 문서의 오류를 검사하기 위한 ErrorHandler 구현 코드
**/
import javax.xml.parsers.*;
import org.xml.sax.*;

public class UseErrorHandler {
	public static void main(String[] args) {
		try {
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			saxParserFactory.setNamespaceAware(true);
			saxParserFactory.setValidating(true);
			SAXParser saxParser = saxParserFactory.newSAXParser();
			XMLReader xmlReader = saxParser.getXMLReader();

			ImplHandler errorHandler = new ImplHandler();
			xmlReader.setErrorHandler(errorHandler);
			xmlReader.parse("SAXSample.xml");
		} catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}
}

class ImplHandler implements ErrorHandler {
	public void error(SAXParseException exception)  {
		System.out.println("Call error Event");
		System.out.println(exception);
	}
	public void fatalError(SAXParseException exception) {
		System.out.println("Call fatalError Event");
		System.out.println(exception);
	}
	public void warning(SAXParseException exception) {
		System.out.println("Call warning Event");
		System.out.println(exception);
	}
}