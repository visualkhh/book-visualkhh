/**
SOAP 메시지로부터 첨부 파일을 얻어 파일로 저장하는 코드
**/
package attachmentservice;

import javax.xml.soap.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.transform.*;
import java.util.*;
import java.io.*;
import javax.activation.DataHandler;

public class AttachmentServlet extends HttpServlet {
	public void doPost(HttpServletRequest request,
                             HttpServletResponse response)
                             throws ServletException, IOException {
		try {
			/* ... 1. SOAPMessage 객체 생성 ... */
		
			boolean result;
			result = saveAttachment(reqSOAP);
        	} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	static boolean saveAttachment(SOAPMessage soapMessage) {
		boolean result = false;
		
		try {
			Iterator iterator = soapMessage.getAttachments();
			while(iterator.hasNext()) {
				AttachmentPart attachment = (AttachmentPart)iterator.next();
			
				String attachmentId = attachment.getContentId();
				String attachmentType = attachment.getContentType();
				String fileType = new String();
				if(attachmentType.equals("text/plain")) {
					fileType = ".txt";
				} else if (attachmentType.equals("text/html")) {
					fileType = ".html";
				} else if (attachmentType.equals("image/jpeg")) {
					fileType = ".jpg";
				} else if (attachmentType.equals("image/gif")) {
					fileType = ".gif";
				} else {
					fileType = "";
				}
			
				DataHandler dataHandler = attachment.getDataHandler();
				InputStream content = dataHandler.getInputStream();
				File attachFile = new File("c:\\" + attachmentId + fileType);
				FileOutputStream fos = new FileOutputStream(attachFile);
				
				int streamTemp;
				int streamLength = 0;
				while((streamTemp = content.read()) != -1) {
					fos.write(streamTemp);
					streamLength++;
				}

				result = true;
				fos.close();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
}