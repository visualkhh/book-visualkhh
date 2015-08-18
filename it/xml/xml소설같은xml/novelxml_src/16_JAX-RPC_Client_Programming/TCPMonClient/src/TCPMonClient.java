/**
TCPMon의 포트로 웹 서비스를 호출하는 클라이언트 코드
**/
package staticstub;

import javax.xml.rpc.*;

public class TCPMonClient {
	public static void main(String[] args) {
		String endpointAddress = "http://localhost:7000/hello-jaxrpc/hello";
		String endpointProperty = javax.xml.rpc.Stub.ENDPOINT_ADDRESS_PROPERTY;
		
		try {
			HelloService_Impl helloService_Imple = new HelloService_Impl();
			Stub stub = (Stub)helloService_Imple.getHelloIFPort();
			stub._setProperty(endpointProperty, endpointAddress);

			HelloIF helloIF = (HelloIF)stub;

			System.out.println(helloIF.sayHello("Duke!"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}