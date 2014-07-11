package clasificador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.HttpURLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;
import org.xml.sax.InputSource;

import clasificador.xmlParser.XmlParser;

public class Clasificador {
	public static String clasificarTT(String trendingTopic){
		TTResult result = null;
		result = tryHashtag(trendingTopic);
		if (result.getResult() == TTResult.TTStatus.FOUND){
			return trendingTopic + ", " + result.getCategory();
		}
		else{
			result = tryDBPedia(trendingTopic);			
		}
		return trendingTopic;
	}

	private static TTResult tryDBPedia(String trendingTopic){
		String params = "";
		try {
			params = java.net.URLEncoder.encode(trendingTopic, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String r = getData("http://lookup.dbpedia.org/api/search.asmx/KeywordSearch?QueryString=" + params);
		XmlParser xmlParser = new XmlParser();
		String label = xmlParser.getLabelFromDBPediaXMLString(r);
		TTResult result = new TTResult();
		result.setResult(TTResult.TTStatus.FOUND);
		result.setCategory(label);
		return result;
	}
		
	
	private static String getData(String urlToRead) {
	      URL url;
	      HttpURLConnection conn;
	      BufferedReader rd;
	      String line;
	      String result = "";
	      try {
	         url = new URL(urlToRead);
	         conn = (HttpURLConnection) url.openConnection();
	         conn.setRequestMethod("GET");
	         //conn.setRequestProperty("Accept", "application/json");
	         rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	         while ((line = rd.readLine()) != null) {
	            result += line;
	         }
	         rd.close();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return result;
	   }


	private static TTResult tryHashtag(String trendingTopic){
		TTResult ttResult = new TTResult();
		if(trendingTopic.startsWith("#")){
			ttResult.setCategory("HASHTAGS");
			ttResult.setResult(TTResult.TTStatus.FOUND);
		}
		else{
			ttResult.setResult(TTResult.TTStatus.NOT_FOUND);
		}
		return ttResult;
	}
}

class TTResult {

	public enum TTStatus { FOUND, NOT_FOUND };
	private TTStatus result;
	private String category;

	public void setResult(TTStatus result){
		this.result = result;
	}
	public void setCategory(String category){
		this.category = category;
	}

	public TTStatus getResult(){
		return this.result;
	}
	public String getCategory(){
		return this.category;
	}
}