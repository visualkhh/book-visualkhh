/**
SAAJ를 이용해서 XML 문서를 읽어서 SOAP 메시지로 만드는 클라이언트
**/
import javax.xml.soap.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.transform.dom.*;

public class SAAJwithDOMClient {
	public static void main(String args[]) throws Exception {
		String endpointURL = "http://localhost:8080/hello-jaxrpc/hello";
		
		DocumentBuilderFactory dBFactory = DocumentBuilderFactory.newInstance();
		dBFactory.setNamespaceAware(true);
		DocumentBuilder dBuilder = dBFactory.newDocumentBuilder();
		Document document = dBuilder.parse("SOAPMessage.xml");
		DOMSource requestXMLSource = new DOMSource(document);
			
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage reqMsg = messageFactory.createMessage();

		SOAPPart soapPart = reqMsg.getSOAPPart();
		soapPart.setContent(requestXMLSource);

		SOAPConnectionFactory conFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection con = conFactory.createConnection();

		SOAPMessage res = con.call(reqMsg, endpointURL);
		
		SOAPBody soapBody = res.getSOAPBody();
		NodeList nodeList = soapBody.getElementsByTagName("result");
		String hello = nodeList.item(0).getTextContent();
		System.out.println(hello);
	}
}