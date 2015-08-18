import org.w3c.dom.*;
import javax.xml.parsers.*;

public class Serialization {
	static String serializeString = new String();
	static String whitespace = "     ";
	
	public static void main(String[] args) {
		try {
			DocumentBuilderFactory dBuilderFactory = DocumentBuilderFactory.newInstance();
			dBuilderFactory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder dBuilder = dBuilderFactory.newDocumentBuilder();
	
			Document document = dBuilder.parse("DOMSample.xml");
			
			serializeNode(document, "");
			
			System.out.println(serializeString);
		} catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	public static void serializeNode(Node node, String indent) {
		switch (node.getNodeType()) {
			case Node.DOCUMENT_NODE:
				Document xmlDoc = (Document)node;
				
				serializeString = "<?xml version=\"" + xmlDoc.getXmlVersion() + "\" encoding=\"" + xmlDoc.getXmlEncoding() + "\" standalone=\"" + xmlDoc.getXmlStandalone() + "\"?>\n\n";
				
				NodeList nodeList = node.getChildNodes();
				
				if (nodeList != null) {
					for (int i = 0 ; i < nodeList.getLength() ; i++) {
						serializeNode(nodeList.item(i), indent);
					}
				}
				
				break;
                	
			case Node.PROCESSING_INSTRUCTION_NODE:
				serializeString += "<?" + ((ProcessingInstruction)node).getTarget() + " " + ((ProcessingInstruction)node).getData() + "?>\n\n";

				break;
        	        
			case Node.DOCUMENT_TYPE_NODE: 
				DocumentType docType = (DocumentType)node;
				
				serializeString += "<!DOCTYPE " + docType.getName();
				
				if ((docType.getPublicId() != null) && (docType.getSystemId() != null)) {
					if (docType.getPublicId() != null) {
						serializeString += " PUBLIC \"" + docType.getPublicId() + "\" ";
					} else {
						serializeString += " SYSTEM ";
					}
					
					serializeString += "\"" + docType.getSystemId() + "\">\n";
				} else {
					serializeString += "[ ... ]>\n";
				}
								
				break;
        	        
			case Node.COMMENT_NODE:
				serializeString += "<!-- " + node.getNodeValue() + " -->\n";
				
				break;
        	        
			case Node.ELEMENT_NODE:
				serializeString += "\n" + indent;
				
				serializeString += "<" + node.getNodeName();
	                
				NamedNodeMap attributes = node.getAttributes();
        	        
				if (attributes.getLength() != 0) {
					for (int i = 0 ; i < attributes.getLength() ; i++) {
						serializeNode(attributes.item(i), indent);
					}
				}
	                
				serializeString += ">";
	                
				NodeList elementNodeList = node.getChildNodes();
        	        
				if (elementNodeList != null) {
					for (int i = 0; i < elementNodeList.getLength() ; i++) {                        
						serializeNode(elementNodeList.item(i), indent + whitespace);
					}
				}
				
				if ((elementNodeList.item(0) != null) && elementNodeList.item(elementNodeList.getLength() - 1).getNodeType() == Node.ELEMENT_NODE) {
					serializeString += "\n" + indent;
				}
	                
				serializeString += "</" + node.getNodeName() + ">";
				
				break;
        	        
			case Node.ATTRIBUTE_NODE:
				serializeString += " " + node.getNodeName() + "=\"" + node.getNodeValue() + "\"";
				
				break;
        	        
			case Node.TEXT_NODE:
				String nodeValue = node.getNodeValue();
				
				if(nodeValue.trim().equals("") | nodeValue.trim().equals("\n")) {
				} else {
					serializeString += nodeValue;
				}
				
				break;
        	        
			case Node.CDATA_SECTION_NODE:
				serializeString += "<![CDATA[ " + node.getNodeValue() + "]]>";
				
				break;
        	        
			case Node.ENTITY_REFERENCE_NODE:
				serializeString += "&" + node.getNodeName() + ";";
				
				break;
		}
	}              
}
/***
<?xml version="1.0" encoding="euc-kr" standalone="false"?>

<!DOCTYPE booklist[ ... ]>
<!--  Book List XML Document  -->

<booklist>
     <book isbn="h0-596-00762-0">
          <name outpage="ProgrammingNETComponents.gif">Programming .NET Components, Second Edition</name>
          <authors>
               <author>Juval Lowy</author>
          </authors>
          <page>648</page>
          <publish month="06" year="2005"></publish>
          <price unit="won">51300</price>
     </book>
</booklist>
***/