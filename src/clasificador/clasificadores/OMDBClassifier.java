package clasificador.clasificadores;

import java.io.UnsupportedEncodingException;

import clasificador.TTResult;
import clasificador.http.HttpConnection;
import clasificador.xmlParser.XmlParser;

public class OMDBClassifier implements ClassifierMethod{

	private final String method = "OMDB";
	
	@Override
	public TTResult tryClasify(String trendingTopic) {
		String params = "";
		try {
			params = java.net.URLEncoder.encode(trendingTopic, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		HttpConnection conn = new HttpConnection();
		String r = conn.getData("http://www.omdbapi.com/?r=xml&t=" + params);
		XmlParser xmlParser = new XmlParser();
		String response = xmlParser.getResultFromXMLString(r);
		
		TTResult result = new TTResult(trendingTopic);
		result.setMethod(this.method);
		if(response.equals("True")){
			result.setResult(TTResult.TTStatus.FOUND);
			result.setCategory("MOVIE");
		}
		else{
			result.setResult(TTResult.TTStatus.NOT_FOUND);
		}
		return result;
	}

}