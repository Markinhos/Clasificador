package clasificador.clasificadores;

import java.io.UnsupportedEncodingException;

import clasificador.TTResult;
import clasificador.http.HttpConnection;
import clasificador.xmlParser.XmlParser;


public class ClasificadorDBPedia implements ClassifierMethod{

	@Override
	public TTResult tryClasify(String trendingTopic) {
		String params = "";
		try {
			params = java.net.URLEncoder.encode(trendingTopic, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpConnection conn = new HttpConnection();
		String r = conn.getData("http://lookup.dbpedia.org/api/search.asmx/KeywordSearch?QueryString=" + params);
		XmlParser xmlParser = new XmlParser();
		String label = xmlParser.getLabelFromDBPediaXMLString(r);
		TTResult result = new TTResult();
		result.setResult(TTResult.TTStatus.FOUND);
		result.setCategory(label);
		return result;
	}
}
