/**
데이터베이스의 필드를 어트리뷰트로 맵핑시켜 XML 문서를 작성하는 예제
**/
import java.sql.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class DB2Attribute {
	public static void main(String[] args) {
		try {
			String dbName = "jdbc:odbc:BookCatalog";
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection db = DriverManager.getConnection(dbName, "", "");
	
			Statement query = db.createStatement();
			ResultSet rs = query.executeQuery("SELECT * FROM BookList");
			ResultSetMetaData rsMeta = rs.getMetaData();
			int columnCount = rsMeta.getColumnCount();

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			Document document = dBuilder.newDocument();
			
			Element rootElement = document.createElement("BookList");
			document.appendChild(rootElement);
			
			while(rs.next()) {
				Element book = document.createElement("Book");
				rootElement.appendChild(book);
				
				for(int i = 1 ; i <= columnCount ; i++) {
					book.setAttribute(rsMeta.getColumnName(i), rs.getString(i));
				}
			}
			
			db.close();

			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			transformer.setOutputProperty("indent", "yes");
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult("BookList.xml");
			transformer.transform(domSource, streamResult);
		} catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}
}
/***
<?xml version="1.0" encoding="UTF-8"?>
<BookList>
<Book Author="James Britt, Teun Duynstee" CoverImage="3323.gif" ISBN="1-861003-32-3" Price="49.99" PubDate="March 2000" Publisher="Wrox Press, Ltd." Stock="4" Title="Professional VB6 XML" id="W1861003323" part="XML"/>
... 중간 생략 ...
</BookList>
***/