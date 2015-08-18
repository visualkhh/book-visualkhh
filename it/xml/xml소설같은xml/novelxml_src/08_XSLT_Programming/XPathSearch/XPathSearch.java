/**
XPath를 이용한 노드 탐색
**/
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.xpath.*;
import javax.xml.namespace.*;

public class XPathSearch {
	public static void main(String[] args) {
		try {
			InputSource input = new InputSource("XPathSample.xml");
			
			XPathFactory xPathFactory = XPathFactory.newInstance();
			XPath xPath = xPathFactory.newXPath();

			String count = "count(//Book)";
			String title = "/Catalog/Book/Title";

			QName STRING_QNAME = XPathConstants.STRING;
			String cBook = (String)xPath.evaluate(count, input, STRING_QNAME);
			System.out.println(cBook + " Books : ");
			
			QName NODESET_QNAME = XPathConstants.NODESET;
			NodeList tList = (NodeList)xPath.evaluate(title, input, NODESET_QNAME);
			for (int i=0; i<tList.getLength(); i++) {
				System.out.println("	" + tList.item(i).getTextContent());
			}
		} catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}
}
/***
3 Books :
        Professional VB6 XML
        XSLT Programmer's Reference
        C++ Programming
***/