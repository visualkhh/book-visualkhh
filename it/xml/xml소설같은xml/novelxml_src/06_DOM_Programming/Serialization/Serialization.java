/**
재귀 함수를 이용한 XML 문서 전체 노드 방문 예제
**/
import org.w3c.dom.*;
import javax.xml.parsers.*;

public class Serialization {
	static String xmlString = new String();
	
	public static void main(String[] args) {
		try {
			DocumentBuilderFactory dBFactory = DocumentBuilderFactory.newInstance();
			dBFactory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder dBuilder = dBFactory.newDocumentBuilder();
			Document document = dBuilder.parse("DOMSample.xml");
			
			serializeNode(document);
			
			System.out.println(xmlString);
		} catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	public static void serializeNode(Node node) {
		switch (node.getNodeType()) {
			case Node.DOCUMENT_NODE:
				Document xmlDoc = (Document)node;
				xmlString = "<?xml version=\"" + xmlDoc.getXmlVersion();
				xmlString += "\" encoding=\"" + xmlDoc.getXmlEncoding();
				xmlString += "\" standalone=\"" + xmlDoc.getXmlStandalone();
				xmlString += "\"?>\n";
				
				NodeList nodeList = node.getChildNodes();
				if (nodeList != null) {
					for (int i = 0 ; i < nodeList.getLength() ; i++) {
						serializeNode(nodeList.item(i));
					}
				}
				break;
                	
			case Node.PROCESSING_INSTRUCTION_NODE:
				xmlString += "<?" + ((ProcessingInstruction)node).getTarget();
				xmlString += " " + ((ProcessingInstruction)node).getData() + "?>\n";
				break;
        	        
			case Node.DOCUMENT_TYPE_NODE: 
				DocumentType docType = (DocumentType)node;
				xmlString += "<!DOCTYPE " + docType.getName();
				if ((docType.getPublicId() != null) && (docType.getSystemId() != null)) {
					if (docType.getPublicId() != null) {
						xmlString += " PUBLIC \"" + docType.getPublicId() + "\" ";
					} else {
						xmlString += " SYSTEM ";
					}
					xmlString += "\"" + docType.getSystemId() + "\">\n";
				} else {
					xmlString += "[ ... ]>\n";
				}
				break;
        	        
			case Node.COMMENT_NODE:
				xmlString += "<!-- " + node.getNodeValue() + " -->";
				break;
        	        
			case Node.ELEMENT_NODE:
				xmlString += "<" + node.getNodeName();
				NamedNodeMap attributes = node.getAttributes();
				if (attributes.getLength() != 0) {
					for (int i = 0 ; i < attributes.getLength() ; i++) {
						serializeNode(attributes.item(i));
					}
				}
				xmlString += ">";
	                
				NodeList elementNodeList = node.getChildNodes();
				if (elementNodeList != null) {
					for (int i = 0; i < elementNodeList.getLength() ; i++) {                        
						serializeNode(elementNodeList.item(i));
					}
				}
				xmlString += "</" + node.getNodeName() + ">";
				break;
        	        
			case Node.ATTRIBUTE_NODE:
				xmlString += " " + node.getNodeName();
				xmlString += "=\"" + node.getNodeValue() + "\"";
				break;
        	        
			case Node.TEXT_NODE:
				String nodeValue = node.getNodeValue();
				if(nodeValue.trim().equals("") | nodeValue.trim().equals("\n")) {
				} else {
					xmlString += nodeValue;
				}
				break;
        	        
			case Node.CDATA_SECTION_NODE:
				xmlString += "<![CDATA[ " + node.getNodeValue() + "]]>";
				break;
        	        
			case Node.ENTITY_REFERENCE_NODE:
				xmlString += "&" + node.getNodeName() + ";";
				break;
		}
	}              
}
/***
<?xml version="1.0" encoding="euc-kr" standalone="false"?>
<!DOCTYPE booklist[ ... ]>
<!--  Book List XML Document  --><booklist><book isbn="h0-596-00762-0"><name outpage="ProgrammingNETComponents.gif">Programming .NET Components, Second Edition</name><authors><author>Juval Lowy</author></authors><page>648</page><publish month="06" year="2005"></publish><price unit="won">51300</price></book></booklist>
***/