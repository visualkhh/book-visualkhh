/**
서비스 이용자가 공개한 웹 서비스의 인터페이스 파일
**/
package dynamicproxy;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloIF extends Remote {
	public String sayHello(String s) throws RemoteException;
}