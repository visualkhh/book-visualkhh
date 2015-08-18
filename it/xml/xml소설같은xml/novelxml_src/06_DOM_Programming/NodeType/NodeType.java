/**
노드 타입의 상수 값을 이용해서 현재 노드의 종류를 출력하는 코드
**/
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class NodeType {
	public static void main(String[] args) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			Document document = dBuilder.parse("DOMSample.xml");
			
			if(document.getNodeType() == Node.DOCUMENT_NODE)
			{
				System.out.print("현재 노드는 도큐먼트 노드이고, 노드 상수는 ");
				System.out.print(document.getNodeType() + "입니다. \n");
			}
			
			Element node = document.getDocumentElement();
			if(node.getNodeType() == Node.ELEMENT_NODE)
			{
				System.out.print("현재 노드는 엘리먼트 노드이고, 노드 상수는 ");
				System.out.print(node.getNodeType() + "입니다. \n");
			}
		} catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}
}
/***
현재 노드는 도큐먼트 노드이고, 노드 상수는 9입니다.
현재 노드는 엘리먼트 노드이고, 노드 상수는 1입니다.
***/