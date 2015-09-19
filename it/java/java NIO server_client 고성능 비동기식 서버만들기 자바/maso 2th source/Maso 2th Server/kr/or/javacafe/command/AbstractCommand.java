package kr.or.javacafe.command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import kr.or.javacafe.AdvancedNioServer;

/**
 * @(#)AbstractCommand.java
 * 
 * @author <a href="mailto:johnleen@hanmail.net">Song Ji-Hoon.</a>
 * @version 1.02, 02/10/25
 * 
 * AbstractCommand 클래스는 클라이언트의 명령을 처리하는 상위 타입의 추상화된 클래스이다.
 * 이 클래스에선 클라이언트의 응답 메세지를 만드는 메소드와 그 메세지를 만들기 위한 필드만 정의되어 있다.
 * 클라이언트로의 실제적인 응답처리는 이 클래스를 상속한 서브 클래스에서 한다.
 */
public abstract class AbstractCommand {
	// data setting..
	private static final String HttpResponseHeader = "HTTP/1.1 200 OK\r\n\r\n";
	private static final String XmlStart = "<?xml version='1.0' encoding='UTF-8'?><response><message>";
	private static final String XmlEnd = "</message></response>";
	
	public AbstractCommand() {}
	
	public byte[] createResponse(String msg) throws UnsupportedEncodingException {
		StringBuffer message = new StringBuffer();
		message.append(HttpResponseHeader);
		message.append(XmlStart);
		message.append(msg);
		message.append(XmlEnd);
		return message.toString().getBytes("UTF-8");
	}
	
	public abstract void execute(AdvancedNioServer server, 
								    ByteBuffer writeBuffer, 
								    String msg) 
								    throws IOException;
	 
}
