/**
데이터베이스의 필드를 엘리먼트에 맵핑시켜 XML 문서를 작성하는 예제
**/
import java.sql.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class DB2Element {
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
				Element bookElement = document.createElement("Book");
				rootElement.appendChild(bookElement);
				
				for(int i = 1 ; i <= columnCount ; i++) {
					Element element = document.createElement(rsMeta.getColumnName(i));
					Text text = document.createTextNode(rs.getString(i));
					element.appendChild(text);
					bookElement.appendChild(element);
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
<Book>
<id>W1861003323</id>
<part>XML</part>
<Title>Professional VB6 XML</Title>
<Author>James Britt, Teun Duynstee</Author>
<Publisher>Wrox Press, Ltd.</Publisher>
<PubDate>March 2000</PubDate>
<ISBN>1-861003-32-3</ISBN>
<Price>49.99</Price>
<CoverImage>3323.gif</CoverImage>
<Stock>4</Stock>
</Book>
<Book>
... 중간 생략 ...
</BookList>
***/