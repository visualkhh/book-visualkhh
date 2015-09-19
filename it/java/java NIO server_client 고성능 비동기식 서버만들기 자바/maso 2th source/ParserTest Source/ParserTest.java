import javax.xml.parsers.*;

class ParserTest 
{
	public static void main(String[] args) 
	{
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            System.out.println("DocumentBuilderFactory => " + factory.getClass().getName());

			SAXParserFactory factory2 = SAXParserFactory.newInstance();
            System.out.println("SAXParserFactory => " + factory2.getClass().getName());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
