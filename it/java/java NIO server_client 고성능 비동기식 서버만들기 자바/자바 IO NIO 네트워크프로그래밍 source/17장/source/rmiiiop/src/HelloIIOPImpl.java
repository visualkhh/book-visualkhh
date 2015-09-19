import java.rmi.RemoteException;

import javax.rmi.PortableRemoteObject;


public class HelloIIOPImpl extends PortableRemoteObject implements HelloIIOP {

	public HelloIIOPImpl() throws RemoteException{}
	

	public String hello() throws RemoteException {
		return "Java World";
	}

}
