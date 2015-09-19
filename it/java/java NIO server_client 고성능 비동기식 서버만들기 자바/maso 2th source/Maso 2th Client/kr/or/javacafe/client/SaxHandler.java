package kr.or.javacafe.client;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @(#)SaxHandler.java
 * 
 * @author <a href="mailto:johnleen@hanmail.net">Song Ji-Hoon.</a>
 * @version 1.14, 02/10/29
 * 
 * SaxHandler 클래스는 NioClient 클래스의 inner 클래스인 ReceiveThread 클래스에서
 * 서버로 부터 받은 응답의 xml 명령을 파싱하는데 사용된다.
 * 싱글턴 패턴을 사용하여 단 한개의 객체만 생성해서 재사용하도록 하였다.
 */
public class SaxHandler extends DefaultHandler {
	
	private ArrayList list;
	
	private static SaxHandler instance = null;

	// *********** Singleton pattern ************
	public static SaxHandler getInstance() {
        if (instance == null) {
            instance = new SaxHandler();
        }

        return instance;
    }
    
	private SaxHandler() {
		list = new ArrayList();
	}
/*	
	public void startElement(String uri, 
	                           String localName, 
	                           String qName,
                               Attributes attributes)
                               throws SAXException {
                               
    	System.out.println("Element start = [" + qName + "]");	
    }
*/	
	public void characters(char[] ch, int start, int length) {
		String content = new String(ch, start, length);
		list.add(content);
		//System.out.println("Element content = [" + content + "]");
	}
/*	
	public void endElement(String uri,
	                         String localName,
	                         String qName)
	                         throws SAXException {
	                        
		System.out.println("Element end = [" + qName + "]");
	}
*/	
	public void clearSaxHandler() {
		list.clear();
	}
	
	public ArrayList getContents() {
		return list;
	}
	
}
