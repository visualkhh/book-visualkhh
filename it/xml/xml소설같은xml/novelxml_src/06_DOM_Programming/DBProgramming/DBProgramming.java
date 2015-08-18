/**
데이터베이스에 저장된 데이터를 출력하는 프로그램 예제
**/
import java.sql.*;

public class DBProgramming {
	public static void main(String[] args) {
		try {
			String dbName = "jdbc:odbc:BookCatalog";
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection db = DriverManager.getConnection(dbName, "", "");
	
			Statement query = db.createStatement();
			ResultSet rs = query.executeQuery("SELECT * FROM BookList");
			ResultSetMetaData rsMetaData = rs.getMetaData();
			int columnCount = rsMetaData.getColumnCount();
			
			while(rs.next()) {
				for(int i = 1 ; i <= columnCount ; i++) {
					System.out.print(rs.getRow() + "th Line ");
					System.out.print(rsMetaData.getColumnName(i) + " : \n");
					System.out.println("	" + rs.getString(i).trim());
				}
			}
			
			db.close();
		} catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}
}
/***
1th Line id :
        W1861003323
1th Line part :
        XML
1th Line Title :
        Professional VB6 XML
1th Line Author :
        James Britt, Teun Duynstee
1th Line Publisher :
        Wrox Press, Ltd.
1th Line PubDate :
        March 2000
1th Line ISBN :
        1-861003-32-3
1th Line Price :
        49.99
1th Line CoverImage :
        3323.gif
1th Line Stock :
        4
... 이하 생략 ...
***/