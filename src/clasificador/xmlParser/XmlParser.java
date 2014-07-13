package clasificador.xmlParser;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XmlParser {
	private Document loadXMLFromString(String xml) throws Exception
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		return builder.parse(is);
	}
	
	public String getResultFromOmdbXMLString(String xml){
		try{
			Document doc = loadXMLFromString(xml);
			Element e = doc.getDocumentElement();
			String response = e.getAttribute("response");
			return response;
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	public String getLabelFromDBPediaXMLString(String xml){
		try{
			Document doc = loadXMLFromString(xml);
			Element rootElement = doc.getDocumentElement();
			
			Element firstResult = this.getFirstElementFromOtherElementByTag(rootElement, "Result");
			
			Element firstClass = this.getFirstElementFromOtherElementByTag(firstResult, "Class");
			
			String label = this.getLabelFromClass(firstClass);
			
			label = this.parseLabel(label);
			return label;
		}	
		catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	private Element getFirstElementFromOtherElementByTag(Element element, String tagName){
		try{
			NodeList nl = element.getElementsByTagName(tagName);
			
			if (nl.getLength() == 0){
				return null;
			}
			else{
				return (Element)nl.item(0);
			}
		}
		catch(NullPointerException ex){
			//ex.printStackTrace();
			return null;
		}				
	}
	
	
	private String getLabelFromClass(Element klass){
		try{
			Node n = klass.getElementsByTagName("Label").item(0);
			String s = n.getFirstChild().getNodeValue();
			return s;
		}
		catch(NullPointerException ex){
			return "";
		}
	}
	
	private String parseLabel(String rawLabel){
		try{
			String label = rawLabel.replaceAll("http://.*/", "").trim();
			return label;		
		}
		catch(NullPointerException ex){
			return "";
		}
	}
	
	
}
