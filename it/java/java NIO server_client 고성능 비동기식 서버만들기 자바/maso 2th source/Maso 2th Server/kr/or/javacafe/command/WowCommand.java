package kr.or.javacafe.command;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Vector;

import kr.or.javacafe.AdvancedNioServer;

/**
 * @(#)WowCommand.java
 * 
 * @author <a href="mailto:johnleen@hanmail.net">Song Ji-Hoon.</a>
 * @version 1.12, 02/10/25
 * 
 * WowCommand 클래스는 클라이언트가 보낸 요청에 대응하는 실제적 처리를 한다.
 * 현재는 단순히 기능 소개를 위해 클라이언트가 보낸 메세지 앞에 "Wow~! o(^^o)(o^^)o :: " 를 
 * 붙여서 브로드캐스트 하도록 구현해놨다. 
 */
public class WowCommand extends AbstractCommand {

	public WowCommand() {}
	
	public void execute(AdvancedNioServer server,
		                  ByteBuffer buffer,
		                  String msg)
		                  throws IOException {
		
		buffer.put(createResponse("Wow~! o(^^o)(o^^)o :: " + msg));
						  	
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
