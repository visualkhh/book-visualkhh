package kr.or.javacafe.command;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Vector;

import kr.or.javacafe.AdvancedNioServer;

/**
 * @(#)MessageCommand.java
 * 
 * @author <a href="mailto:johnleen@hanmail.net">Song Ji-Hoon.</a>
 * @version 1.12, 02/10/25
 * 
 * MessageCommand 클래스는 클라이언트가 보낸 요청에 대응하는 실제적 처리를 한다.
 * 단순히 클라이언트가 보낸 메세지를 브로드캐스트 한다.
 */
public class MessageCommand extends AbstractCommand {
	
	public MessageCommand() {}
	
	public void execute(AdvancedNioServer server, 
						  ByteBuffer buffer, 
						  String msg) 
						  throws IOException {
		
		buffer.put(createResponse(msg));
						  	
		Vector users = server.getUsers();
		int size = users.size();
		for (int i = 0; i < size; i++) {
			buffer.flip();
			buffer.rewind();
			SocketChannel sc = (SocketChannel) users.get(i);
				
			sc.write(buffer);			
		}
	}

}
