/**
XML 문서의 노드 변경 예제
**/
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class ReplaceNode {
	public static void main(String[] args) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			Document document = dBuilder.parse("DOMSample.xml");

			// 루트 엘리먼트의 첫 번째 하위 엘리먼트를 얻어 이름을 rename_name으로 변경
			// <name>너나타</name> ---> <rename_name>너나타</rename_name>
			Element rootElement = document.getDocumentElement();
			Node renameElement = rootElement.getFirstChild();
			document.renameNode(renameElement, "", "rename_name");

			// 루트 엘리먼트의 마지막 하위 엘리먼트를 새로 만든 엘리먼트로 교체
			// <CC>2000</CC> ---> <blend>형대</blend>
			Element newElement = document.createElement("blend");
			Text newText = document.createTextNode("형대");
			newElement.appendChild(newText);
			Node replaceElement = rootElement.getLastChild();
			rootElement.replaceChild(newElement, replaceElement);
			
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
<car>
	<rename_name>너나타</rename_name>
	<blend>형대</blend>
</car>
***/