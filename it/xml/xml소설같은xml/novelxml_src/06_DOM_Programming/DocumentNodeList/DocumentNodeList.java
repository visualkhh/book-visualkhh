/**
Document로부터 NodeList 얻기
**/
import org.w3c.dom.*;
import javax.xml.parsers.*;

public class DocumentNodeList{
	public static void main(String[] args) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			Document document = dBuilder.parse("DOMSample.xml");

			NodeList nodeList = document.getChildNodes();
			for (int i = 0 ; i < nodeList.getLength() ; i++){
				Node node = nodeList.item(i);
				System.out.println(node.getNodeType());
			}
		} catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}
}
/***
7
10
8
1
***/