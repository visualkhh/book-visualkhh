/**
JAX-RPC를 이용한 정적 스텁 클라이언트의 구현
**/
package staticstub;

import javax.xml.rpc.*;

public class StaticStubClient {
	public static void main(String[] args) {
		try {
			HelloService_Impl helloService_Imple = new HelloService_Impl();
			Stub stub = (Stub)helloService_Imple.getHelloIFPort();

			HelloIF helloIF = (HelloIF)stub;

			System.out.println(helloIF.sayHello("Duke!"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}