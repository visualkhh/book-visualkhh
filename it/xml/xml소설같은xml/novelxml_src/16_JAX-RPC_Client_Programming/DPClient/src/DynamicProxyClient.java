/**
JAX-RPC를 이용한 동적 프록시 클라이언트의 구현 코드
**/
package dynamicproxy;

import java.net.URL;
import javax.xml.rpc.Stub;
import javax.xml.rpc.Service;
import javax.xml.rpc.JAXRPCException;
import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceFactory;

public class DynamicProxyClient {
	public static void main(String[] args) {
		try {
			String wsdlUrl = "http://localhost:8080/hello-jaxrpc/hello?WSDL";
			String nameSpaceUri = "http://localhost:8080";
			String serviceName = "HelloService";
			String portName = "HelloIFPort";

			ServiceFactory serviceFactory = ServiceFactory.newInstance();

			URL wsdlUrl = new URL(wsdlUrl);
			QName serviceQName = new QName(nameSpaceUri, serviceName);
			Service service = serviceFactory.createService(wsdlUrl, serviceQName);

			QName pQName = new QName(nameSpaceUri, portName);
			Stub stub = (Stub)service.getPort(pQName, dynamicproxy.HelloIF.class);
			HelloIF myProxy = (HelloIF)stub;

			System.out.println(myProxy.sayHello("Buzz"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}