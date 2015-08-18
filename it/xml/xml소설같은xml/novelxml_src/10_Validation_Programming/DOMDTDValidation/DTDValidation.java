/**
DOM을 이용해서 DTD로 정의된 XML 문서의 유효성 검사
**/
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class DTDValidation {
	public static void main(String[] args) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
			factory.setValidating(true);
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