/**
Node 인터페이스를 이용한 각 노드 검색 예제
**/
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class SearchNode {
	public static void main(String[] args) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			Document document = dBuilder.parse("DOMSample.xml");
			
			Node authorsElement = document.getElementsByTagName("authors").item(0);
			
			Node firstAuthorNode = authorsElement.getFirstChild();
			Node lastAuthorNode = authorsElement.getLastChild();
			Node preSiblingNode = authorsElement.getPreviousSibling();
			Node nextSiblingNode = authorsElement.getNextSibling();
			Node parentNode = authorsElement.getParentNode();
			Document authorsDocument = authorsElement.getOwnerDocument();
			
			System.out.print("Element Search By \"authors\" : ");
			System.out.print(authorsElement.getNodeName() + "\n");
			System.out.print("authors Element's FirstChild Value : ");
			System.out.print(firstAuthorNode.getTextContent() + "\n");
			System.out.print("authors Element's LastChild Value : ");
			System.out.print(lastAuthorNode.getTextContent() + "\n");
			System.out.print("authors Element's Previous Sibling Node Value : ");
			System.out.print(preSiblingNode.getTextContent() + "\n");
			System.out.print("authors Element's Next Sibling Node Value : ");
			System.out.print(nextSiblingNode.getTextContent() + "\n");
			System.out.print("authors Element's Parent Node Name : ");
			System.out.print(parentNode.getNodeName() + "\n");
			System.out.print("authors Element's Owner Document URI : ");
			System.out.print(authorsDocument.getDocumentURI() + "\n");
		} catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}
}
/***
Element Search By "authors" : authors
authors Element's FirstChild Value : Juval Lowy
authors Element's LastChild Value : McLaughlin
authors Element's Previous Sibling Node Value : Programming .NET Components, Second Edition
authors Element's Next Sibling Node Value : 512
authors Element's Parent Node Name : book
authors Element's Owner Document URI : file:///D:/SearchNode/DOMSample.xml
***/