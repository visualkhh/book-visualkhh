/**
DOM 파서를 생성하고, XML 문서를 파싱하거나 새로운 XML 문서를 생성하는 예제
**/
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class CreateDOMParser {
	public static void main(String[] args) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();

			// 빈 Document 객체 생성
			Document emptyDocument = dBuilder.newDocument();
			System.out.println("빈 Document 객체 생성 완료");
			
			// XML 파일을 로딩하여 Document 객체 생성
			Document xmlDocument = dBuilder.parse("DOMSample.xml");
			System.out.println("DOMSample.xml 문서를 입력으로 Document 객체 생성 완료");
		} catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}
}
/***
빈 Document 객체 생성 완료
DOMSample.xml 문서를 입력으로 Document 객체 생성 완료
***/