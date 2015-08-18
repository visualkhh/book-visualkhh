/**
DOM 입력과 출력을 이용한 XSLT 변환 코드
**/
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

public class DOMTransform {
	public static void main(String[] args) {
		try {
			DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dBF.newDocumentBuilder();
			Document document = dBuilder.parse("catalog.xml");
		
			DOMSource domSource = new DOMSource(document);
			StreamSource xsltSource = new StreamSource("catalog.xsl");
			DOMResult domResult = new DOMResult();
		
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer(xsltSource);
			transformer.transform(domSource, domResult);
			
			Document resultDoc = (Document)domResult.getNode();
			System.out.println("Success HTML Transformation..");
			System.out.print(resultDoc.toString());
		} catch(Exception e) {
			
		}
	}
}
/***
Success HTML Transformation..
<?xml version="1.0" encoding="UTF-16"?>
<HTML><HEAD><TITLE>Book Catalog</TITLE></HEAD><BODY><h2>Book Catalog</h2><hr color="black"/><p><font color="red"><b>Professional VB6 XML</b></font></p><p><b>Author : </b>1. Teun Duynstee, 2. James Britt</p><p><b>Price : </b>$ 49.99</p><p><img border="1" src="3323.gif" width="150"/></p><p><font color="blue"><b>XSLT Programmer's Reference</b></font></p><p><b>Author : </b>1. Michael Kay</p><p><b>Price : </b>$ 34.99</p><p><img border="1" src="3129.gif" width="150"/></p></BODY></HTML>
***/