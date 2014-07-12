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
	
	public String getResultFromXMLString(String xml){
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
			Element e = doc.getDocumentElement();
			
			Element ele = this.getFirstResult(e);
			
			Element klass = this.getFirstClass(ele);
			
			String label = this.getLabelFromClass(klass);
			
			label = this.parseLabel(label);
			return label;
		}	
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	private Element getFirstClass(Element element){
		NodeList nl = element.getElementsByTagName("Class");
		if (nl.getLength() == 0){
			return null;
		}
		else{
			return (Element)nl.item(0);
		}		
	}
	
	private Element getFirstResult(Element document){
		NodeList nl = document.getElementsByTagName("Result");
		if (nl.getLength() == 0){
			return null;
		}
		else{
			return (Element)nl.item(0);
		}
	}	
	
	private String getLabelFromClass(Element klass){
		Node n = klass.getElementsByTagName("Label").item(0);
		String s = n.getFirstChild().getNodeValue();
		return s;
	}
	
	private String parseLabel(String rawLabel){
		String label = rawLabel.replaceAll("http://.*/", "").trim();
		return label;
	}
	
	
}
