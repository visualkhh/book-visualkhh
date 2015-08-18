/**
DOM을 이용해서 XML Schema로 정의된 XML 문서의 유효성 검사를 하는 코드
**/
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import java.io.*;

public class SchemaValidation {
	public static void main(String[] args) {
		try {
			String LANG = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
			String XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
			String SRC = "http://java.sun.com/xml/jaxp/properties/schemaSource";

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
			factory.setValidating(true);
			factory.setAttribute(LANG, XML_SCHEMA);
			factory.setAttribute(SRC, new File("DOMSample.xsd"));
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			
			ImplHandler errorHandler = new ImplHandler();
			dBuilder.setErrorHandler(errorHandler);
			Document document = dBuilder.parse("DOMSample.xml");
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