package helloservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloIF extends Remote {
	public String sayHello(String s) throws RemoteException;
}