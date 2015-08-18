/**
클라이언트의 SOAP 메시지로부터 첨부 파일을 읽고 저장하는 첨부 파일 웹 서비스 전체 코드
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
			MessageFactory mFactory = MessageFactory.newInstance();
			MimeHeaders mimeHeaders = getHeaders(request);
			InputStream inStream = request.getInputStream();
			SOAPMessage reqSOAP = mFactory.createMessage(mimeHeaders, inStream);
		
			boolean result;
			result = saveAttachment(reqSOAP);
			
			SOAPMessage responseSOAPMsg = mFactory.createMessage();
			SOAPPart responseSOAPPart = responseSOAPMsg.getSOAPPart();
			SOAPEnvelope responseEnvelope = responseSOAPPart.getEnvelope();
			SOAPBody responseBody = responseEnvelope.getBody();
		
			SOAPFactory soapFactory = SOAPFactory.newInstance();
			if(!result) {
				SOAPFault fault = responseBody.addFault();
				Name faultName = soapFactory.createName("Client", "SOAP-ENV", null);
				fault.setFaultCode(faultName);
				fault.setFaultActor("http://localhost:8080");
				fault.setFaultString("No Attachment File..!!");
			}

			if (responseSOAPMsg.saveRequired()) {
				responseSOAPMsg.saveChanges();
			}

			if(responseBody.hasFault()) {
				int status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				response.setStatus(status);
			} else {
				int status = HttpServletResponse.SC_OK;
				response.setStatus(status);
			}
                	
			putHeaders(responseSOAPMsg.getMimeHeaders(), response);
			OutputStream os = response.getOutputStream();
			responseSOAPMsg.writeTo(os);
			os.flush();
        	} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	static MimeHeaders getHeaders(HttpServletRequest req) {
		Enumeration headerNames = req.getHeaderNames();
		MimeHeaders headers = new MimeHeaders();

		while (headerNames.hasMoreElements()) {
			String headerName = (String) headerNames.nextElement();
			String headerValue = req.getHeader(headerName);
			StringTokenizer values = new StringTokenizer(headerValue, ",");
			while (values.hasMoreTokens()) {
				headers.addHeader(headerName, values.nextToken().trim());
			}
		}

		return headers;
	}
	
	static void putHeaders(MimeHeaders headers, HttpServletResponse res) {
		Iterator it = headers.getAllHeaders();

		while(it.hasNext()) {
			MimeHeader header = (MimeHeader)it.next();
			String[] values = headers.getHeader(header.getName());

			if(values.length == 1) {
				res.setHeader(header.getName(), header.getValue());
			} else {
				StringBuffer concat = new StringBuffer();
				int i = 0;

				while(i < values.length) {
					if(i != 0) {
						concat.append(',');
					}
					concat.append(values[i++]);
				}
				res.setHeader(header.getName(), concat.toString());
			}
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