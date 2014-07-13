package clasificador.clasificadores;

import clasificador.TrendingTopicClassification;
import clasificador.http.HttpConnection;
import clasificador.xmlParser.XmlParser;

public class OMDBClassifier extends APIClassifier{

	private final String method = "OMDB";
	private static final String omdbEndpoint = "http://www.omdbapi.com/?r=xml&t=";
	
	public OMDBClassifier(){
		super(omdbEndpoint);
	}
	
	@Override
	public TrendingTopicClassification tryClasify(String trendingTopic) {
		
		String data = this.getAPIdata(trendingTopic);
		
		XmlParser xmlParser = new XmlParser();
		String response = xmlParser.getResultFromOmdbXMLString(data);
		
		TrendingTopicClassification result = new TrendingTopicClassification(trendingTopic);
		result.setMethod(this.method);
		if(response.equals("True")){
			result.setResult(TrendingTopicClassification.TTStatus.FOUND);
			result.setCategory("MOVIE");
		}
		else{
			result.setResult(TrendingTopicClassification.TTStatus.NOT_FOUND);
		}
		return result;
	}

}