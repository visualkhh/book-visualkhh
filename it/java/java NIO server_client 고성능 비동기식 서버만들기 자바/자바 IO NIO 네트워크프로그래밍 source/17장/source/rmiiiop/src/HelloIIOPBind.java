import javax.naming.*;

public class HelloIIOPBind {

	public static void main(String[] args) {
		try{
			HelloIIOPImpl obj = new HelloIIOPImpl();

			java.util.Properties p = new java.util.Properties();
			p.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.cosnaming.CNCtxFactory");
			p.put(Context.PROVIDER_URL, "iiop://localhost");			
			Context ctx = new InitialContext(p);
			
			ctx.rebind("helloiiop", obj);
			System.out.println("등록되었습니다.");
		}catch(Exception ex){
			System.out.println(ex);
		}
	} // main
} // class

