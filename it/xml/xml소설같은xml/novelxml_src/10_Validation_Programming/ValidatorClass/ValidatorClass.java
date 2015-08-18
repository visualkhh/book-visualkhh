/**
Validator 클래스를 이용한 유효성 검사
**/
import javax.xml.*;
import javax.xml.transform.stream.*;
import javax.xml.validation.*;
import org.xml.sax.*;
import java.io.*;

public class ValidatorClass {
	public static void main(String[] args) {
		try {
			String XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

			SchemaFactory schemaFactory = SchemaFactory.newInstance(XML_SCHEMA);
			ImplHandler errorHandler = new ImplHandler();
			schemaFactory.setErrorHandler(errorHandler);
			Schema schema = schemaFactory.newSchema(new File("DOMSample.xsd"));

			Validator validator = schema.newValidator();
			validator.setErrorHandler(errorHandler);
			StreamSource xmlSource = new StreamSource(new File("DOMSample.xml"));
			validator.validate(xmlSource);
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