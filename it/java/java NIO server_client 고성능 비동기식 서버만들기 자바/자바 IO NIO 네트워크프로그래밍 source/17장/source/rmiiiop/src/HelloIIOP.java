import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloIIOP extends Remote {
	public String hello() throws RemoteException;
}
