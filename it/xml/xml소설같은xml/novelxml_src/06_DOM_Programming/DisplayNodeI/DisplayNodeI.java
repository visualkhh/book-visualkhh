/**
루트 엘리먼트를 얻어서 노드 타입을 출력하는 예제
**/
import org.w3c.dom.*;
import javax.xml.parsers.*;

public class DisplayNodeI {
	public static void main(String[] args) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			Document document = dBuilder.parse("DOMSample.xml");

			Node rootElement = document.getDocumentElement();
			displayNode(rootElement);
		} catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}
	public static void displayNode(Node node) {
		switch (node.getNodeType()) {
			case Node.ELEMENT_NODE:
				System.out.println("Element Node");
				break;
			case Node.TEXT_NODE:
				System.out.println("Text Node");
				break;
		}
	}
}
/***
Element Node
***/