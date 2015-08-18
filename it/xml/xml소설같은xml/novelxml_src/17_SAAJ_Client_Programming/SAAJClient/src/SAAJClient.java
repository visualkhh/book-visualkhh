/**
SAAJ를 이용해서 수동으로 SOAP 메시지를 작성하고 웹 서비스를 이용하는 웹 서비스 클라이언트
**/
import javax.xml.soap.*;

public class SAAJClient {
	public static void main(String args[]) throws Exception {
		String xsdNamespaceURI = "http://www.w3.org/2001/XMLSchema";
		String xsiNamespaceURI = "http://www.w3.org/2001/XMLSchema-instance";
		String encNamespaceURI = "http://schemas.xmlsoap.org/soap/encoding/";
		String ns0NamespaceURI = "http://localhost:8080";
		String endpointURL = "http://localhost:8080/hello-jaxrpc/hello";
		
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage reqMsg = messageFactory.createMessage();

		SOAPPart soapPart = reqMsg.getSOAPPart();
		SOAPEnvelope env = soapPart.getEnvelope();
		SOAPBody body = env.getBody();
		
		env.addNamespaceDeclaration("xsd", xsdNamespaceURI);
		env.addNamespaceDeclaration("xsi", xsiNamespaceURI);
		env.addNamespaceDeclaration("enc", encNamespaceURI);
		env.addNamespaceDeclaration("ns0", ns0NamespaceURI);

		SOAPFactory factory = SOAPFactory.newInstance();

		Name methodName = factory.createName("sayHello", "ns0", null);
		SOAPBodyElement methodElement = body.addBodyElement(methodName);

		Name pName = factory.createName("String_1");
		SOAPElement pElement = methodElement.addChildElement(pName);
		Name typeName = factory.createName("type", "xsi", null);
		pElement.addAttribute(typeName, "xsd:string");
		pElement.addTextNode("Jhone!"); 

		Name encoding = factory.createName("encodingStyle", "SOAP-ENV", null);
		env.addAttribute(encoding, "http://schemas.xmlsoap.org/soap/encoding/");

		SOAPConnectionFactory conFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection connection = conFactory.createConnection();

		SOAPMessage res = connection.call(reqMsg, endpointURL);
		
		SOAPBody soapBody = res.getSOAPBody();
		NodeList nodeList = soapBody.getElementsByTagName("result");
		String hello = nodeList.item(0).getTextContent();
		System.out.println(hello);
	}
}