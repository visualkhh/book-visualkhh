import java.io.*;			
import java.net.*;			
			
public class DaumSearch {			
	public static void main(String[] args){		
			
		if (args.length != 2) {	
			System.err.println("사용법: java DaumSearch keyword filename");
			System.exit(1);
		}	
		try{	
			String keyword = URLEncoder.encode(args[0]);
		    //http://search.daum.net/cgi-bin/nsp/search.cgi?w=tot&q=%BB%E7%B0%FA	
		    String query = "w=tot&q=" + keyword;	
		    String u = "http://search.daum.net/cgi-bin/nsp/search.cgi";	
		    System.out.println(u + query);	
			URL url = new URL(u);	
			URLConnection connection = url.openConnection();	
			HttpURLConnection hurlc = (HttpURLConnection)connection;	
			hurlc.setRequestMethod("GET");	
			hurlc.setDoOutput(true);	
			hurlc.setDoInput(true);	
			hurlc.setUseCaches(false);	
			hurlc.setDefaultUseCaches(false);	
			PrintWriter out = new PrintWriter(hurlc.getOutputStream());	
			out.println(query);	
			out.close();	
			BufferedReader in = new BufferedReader(new InputStreamReader(hurlc.getInputStream()));	
			PrintWriter pw = new PrintWriter(new FileWriter(args[1]));	
			String inputLine = null;	
			while ((inputLine = in.readLine()) != null){	
				pw.println(inputLine);
			}	
			in.close();	
			pw.close();	
			System.out.println("검색된 결과가 " + args[1] + " 파일에 저장되었습니다.");	
		}catch(Exception ex){		
			System.out.println(ex);	
		}		
	}			
}				
