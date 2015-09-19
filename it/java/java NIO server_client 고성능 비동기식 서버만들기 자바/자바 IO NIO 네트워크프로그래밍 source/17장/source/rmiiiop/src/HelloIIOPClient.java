import javax.naming.*;
import javax.rmi.*;

public class HelloIIOPClient {

	public static void main(String[] args) {
		try{
			java.util.Properties p = new java.util.Properties();
			p.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.cosnaming.CNCtxFactory");
			p.put(Context.PROVIDER_URL, "iiop://localhost");			
			Context ctx = new InitialContext(p);
			Object obj = ctx.lookup("helloiiop");
			HelloIIOP h = (HelloIIOP)PortableRemoteObject.narrow( obj,HelloIIOP.class);
			String msg = h.hello();
			System.out.println(h.hello());
		}catch(Exception ex){
			System.out.println(ex);
		}				
	}
}
