/**
엘리먼트와 텍스트 노드 전체를 방문하는 재귀 함수의 코드
**/
import org.w3c.dom.*;
import javax.xml.parsers.*;

public class DisplayNodeII {
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
				xmlString += "<" + node.getNodeName() + ">";
				NodeList elementNodeList = node.getChildNodes();
				if (elementNodeList != null) {
					for (int i = 0; i < elementNodeList.getLength() ; i++) {                        
						displayNode(elementNodeList.item(i));
					}
				}
				xmlString += "</" + node.getNodeName() + ">";
				break;
			case Node.TEXT_NODE:
				String nodeValue = node.getNodeValue();
				if(nodeValue.trim().equals("") | nodeValue.trim().equals("\n")) {
					//
				} else {
					xmlString += nodeValue;
				}
				break;
		}
	}              
}
/***
<booklist><book><name>Programming .NET Components, Second Edition</name><authors><author>Juval Lowy</author></authors><page>648</page><publish></publish><price>51300</price></book></booklist>
***/