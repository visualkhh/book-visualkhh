/**
재귀 함수를 이용한 XML 문서 전체 노드 방문 예제
**/
import org.w3c.dom.*;
import javax.xml.parsers.*;

public class DisplayNodeIV {
	static String xmlString = new String();
	
	public static void main(String[] args) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			Document document = dBuilder.parse("DOMSample.xml");
			
			displayNode(document.getDocumentElement());
			System.out.println(xmlString);
		} catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	public static void displayNode(Node node) {
		switch (node.getNodeType()) {
			case Node.ELEMENT_NODE:
				xmlString += "<" + node.getNodeName();
				NamedNodeMap attributes = node.getAttributes();
				if (attributes.getLength() != 0) {
					for (int i = 0 ; i < attributes.getLength() ; i++) {
						displayNode(attributes.item(i));
					}
				}
				xmlString += ">";
				NodeList elementNodeList = node.getChildNodes();
				if (elementNodeList != null) {
					for (int i = 0; i < elementNodeList.getLength() ; i++) {                        
						displayNode(elementNodeList.item(i));
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
					//
				} else {
					xmlString += nodeValue;
				}
				break;
			case Node.COMMENT_NODE:
				xmlString += "<!-- " + node.getNodeValue() + " -->";
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
<booklist><!--  Book Information  --><book isbn="h0-596-00762-0"><name outpage="ProgrammingNETComponents.gif"></name><authors><author>Juval Lowy</author></authors><page>648</page><publish month="06" year="2005"></publish><price unit="won"><![CDATA[ 51300]]></price></book></booklist>
***/