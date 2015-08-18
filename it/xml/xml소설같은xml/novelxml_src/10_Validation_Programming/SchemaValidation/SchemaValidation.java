/**
SAX를 이용해서 XML Schema로 정의된 XML 문서의 유효성 검사
**/
import javax.xml.parsers.*;
import org.xml.sax.*;
import java.io.*;

public class SchemaValidation {
	public static void main(String[] args) {
		try {
			String LANG = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
			String XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
			String SRC = "http://java.sun.com/xml/jaxp/properties/schemaSource";

			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			saxParserFactory.setNamespaceAware(true);
			saxParserFactory.setValidating(true);
			
			SAXParser saxParser = saxParserFactory.newSAXParser();
			saxParser.setProperty(LANG, XML_SCHEMA);
			saxParser.setProperty(SRC, new File("SAXSample.xsd"));

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