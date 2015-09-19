import java.io.*;			
import java.net.*;			
			
public class PostConnection {			
	public static void main(String[] args){		
			
		if (args.length != 2) {	
			System.err.println("»ç¿ë¹ý: java PostConnection id password");
			System.exit(1);
		}	
		try{	
			String id = URLEncoder.encode(args[0]);
			String passwd = URLEncoder.encode(args[1]);
		    //http://search.daum.net/cgi-bin/nsp/search.cgi?w=tot&q=%BB%E7%B0%FA	
		    String query = "id=" + id + "&passwd=" + passwd;	
		    String u = "http://sunny.sarang.net/jnet/post.php";	
		    System.out.println(u + query);		
			URL url = new URL(u);	
			URLConnection connection = url.openConnection();	
			HttpURLConnection hurlc = (HttpURLConnection)connection;	
			hurlc.setRequestMethod("POST");	
			hurlc.setDoOutput(true);	
			hurlc.setDoInput(true);	
			hurlc.setUseCaches(false);	
			hurlc.setDefaultUseCaches(false);	
			PrintWriter out = new PrintWriter(hurlc.getOutputStream());	
			out.println(query);	
			out.close();	
			BufferedReader in = new BufferedReader(new InputStreamReader(hurlc.getInputStream()));	
			String inputLine = null;	
			while ((inputLine = in.readLine()) != null){	
				System.out.println(inputLine);
			}	
			in.close();	
		}catch(Exception ex){		
			System.out.println(ex);	
		}		
	}			
}				
