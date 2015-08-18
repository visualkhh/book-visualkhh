/**
DTD를 이용하여 XML 문서 구조가 정의된 NodeList 출력 코드
**/
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class IgnoringElementWhitespace {
	public static void main(String[] args) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
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
name
CC
***/