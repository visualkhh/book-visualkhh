/**
특정 노드를 삭제하는 예제
**/
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class RemoveNode {
	public static void main(String[] args) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			Document document = dBuilder.parse("DOMSample.xml");
			Element rootElement = document.getDocumentElement();

			// 루트 엘리먼트의 마지막 하위 엘리먼트를 얻어서 삭제
			// <Element3>C</Element3> ---> 삭제
			Node removeElement = rootElement.getLastChild();
			rootElement.removeChild(removeElement);

			// 루트 엘리먼트의 Attr3 어트리뷰트 삭제
			// <root Attr1="1" Attr2="2" Attr3="3"> ---> <root Attr="1" Attr2="2">
			rootElement.removeAttribute("Attr3");
			
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult("BookList.xml");
			transformer.transform(domSource, streamResult);
		} catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}
}
/***
<?xml version="1.0" encoding="UTF-8"?>
<root Attr1="1" Attr2="2">
	<Element1>A</Element1>
	<Element2>B</Element2>
</root>
***/