/**
NamedNodeMap 인터페이스를 이용한 어트리뷰트 집합 이용하기
**/
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class AttrGathering {
	public static void main(String[] args) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			Document document = dBuilder.parse("DOMSample.xml");

			Element rootElement = document.getDocumentElement();
			
			if(rootElement.hasAttributes()) {
				NamedNodeMap attrNodes = rootElement.getAttributes();
         
				for(int i = 0 ; i < attrNodes.getLength() ; i++) {
					Node node = attrNodes.item(i);
					System.out.println("Attribute Name : " + node.getNodeName());
					System.out.println("	Attribute Value : " + node.getNodeValue());
				}
			}
		} catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}
}
/***
Attribute Name : Attr1
        Attribute Value : 1
Attribute Name : Attr2
        Attribute Value : 2
Attribute Name : Attr3
        Attribute Value : 3
***/