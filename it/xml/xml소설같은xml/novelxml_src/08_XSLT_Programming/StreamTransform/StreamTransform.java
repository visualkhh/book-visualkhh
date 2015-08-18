/**
스트림 형태의 입력과 출력을 이용한 XSLT 변환 코드
**/
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.io.File;

public class StreamTransform {
	public static void main(String[] args) {
		try {
			Source xmlSource = new StreamSource("catalog.xml");
			Source xsltSource = new StreamSource("catalog.xsl");
			Result htmlResult = new StreamResult("catalog.html");
			
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer(xsltSource);
			transformer.transform(xmlSource, htmlResult);
		} catch(Exception e) {
		}
	}
}