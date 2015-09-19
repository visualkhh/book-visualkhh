package kr.or.javacafe.pool;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @(#)SaxHandler.java
 * 
 * @author <a href="mailto:johnleen@hanmail.net">Song Ji-Hoon.</a>
 * @version 1.14, 02/10/26
 * 
 * SaxHandler 클래스는 WorkerThread 클래스에서 클라이언트의 xml 명령을 파싱하는데 사용된다.
 * 싱글턴 패턴을 사용하여 단 한개의 객체만 생성해서 재사용하도록 하였다.
 */
public class SaxHandler extends DefaultHandler {
	
	private ArrayList list;
	
	private static SaxHandler instance = new SaxHandler();

	// *********** Singleton pattern ************
	public static SaxHandler getInstance() {
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
