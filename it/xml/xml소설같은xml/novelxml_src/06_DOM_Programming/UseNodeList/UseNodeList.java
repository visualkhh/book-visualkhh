/**
루트 엘리먼트가 가진 하위 노드를 NodeList 인터페이스로 얻어 출력하는 예제
**/
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class UseNodeList {
	public static void main(String[] args) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			Document document = dBuilder.parse("DOMSample.xml");

			Element rootElement = document.getDocumentElement();
			NodeList nodeList = rootElement.getChildNodes();
         
			for(int i = 0 ; i < nodeList.getLength() ; i++) {
				Node node = nodeList.item(i);
				String nodeName = node.getNodeName();
				System.out.println(nodeName);
			}
		} catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}
}
/***
#text
name
#text
CC
#text
***/