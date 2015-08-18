/**
JAX-RPC를 이용한 동적 호출 인터페이스 클라이언트의 구현 코드
**/
package dii;

import javax.xml.rpc.Call;
import javax.xml.rpc.Service;
import javax.xml.rpc.JAXRPCException;
import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceFactory;
import javax.xml.rpc.ParameterMode;

public class DIIClient {
	public static void main(String[] args) {
		try {
			String serviceName = "HelloService";
			String serviceURL = "http://localhost:8080/hello-jaxrpc/hello";
			String portName = "HelloIFPort";
			String BODY_NAMESPACE_VALUE = "http://localhost:8080";
			String NS_XSD = "http://www.w3.org/2001/XMLSchema";
			String URI_ENCODING = "http://schemas.xmlsoap.org/soap/encoding/";
			
			ServiceFactory factory = ServiceFactory.newInstance();

			QName serviceQName = new QName(serviceName);
			Service service = factory.createService(serviceQName);

			QName portQName = new QName(portName);
			Call call = service.createCall(portQName);

			call.setTargetEndpointAddress(serviceURL);
			call.setProperty(Call.ENCODINGSTYLE_URI_PROPERTY, URI_ENCODING);

			call.setOperationName(new QName(BODY_NAMESPACE_VALUE, "sayHello"));
			
			QName QNAME_TYPE_STRING = new QName(NS_XSD, "string");
			call.addParameter("String_1", QNAME_TYPE_STRING, ParameterMode.IN);

			call.setReturnType(QNAME_TYPE_STRING);

			Object[] params = { new String("Murph!") };

			String result = (String) call.invoke(params);
			System.out.println(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}