/**
SOAP 메시지에 첨부 파일이 포함되지 않았을 경우 SOAP Fault를 작성하는 코드
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
			
			/* ... 2. SOAP 메시지의 첨부 파일 저장 ... */
			
			SOAPMessage responseSOAPMsg = mFactory.createMessage();
			SOAPPart responseSOAPPart = responseSOAPMsg.getSOAPPart();
			SOAPEnvelope responseEnvelope = responseSOAPPart.getEnvelope();
			SOAPBody responseBody = responseEnvelope.getBody();
		
			SOAPFactory soapFactory = SOAPFactory.newInstance();
			if(!result) {	// SOAP 메시지에 첨부 파일이 포함되어 있지 않다면...
				SOAPFault fault = responseBody.addFault();
				Name faultName = soapFactory.createName("Client", "SOAP-ENV", null);
				fault.setFaultCode(faultName);
				fault.setFaultActor("http://localhost:8080");
				fault.setFaultString("No Attachment File..!!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}