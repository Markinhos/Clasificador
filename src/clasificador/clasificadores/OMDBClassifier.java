package clasificador.clasificadores;

import clasificador.TrendingTopicClassification;
import clasificador.xmlParser.XmlParser;

public class OMDBClassifier extends APIClassifier{

	private final String method = "OMDB";
	private static final String omdbEndpoint = "http://www.omdbapi.com/?r=xml&t=";
	
	public OMDBClassifier(String trendingTopic){
		super(omdbEndpoint, trendingTopic);
	}
	
	@Override
	public TrendingTopicClassification tryClasify() {
		
		
		
		String data = this.getAPIdata(this.getTrendingTopicFormatted());
		
		XmlParser xmlParser = new XmlParser();
		String response = xmlParser.getResultFromOmdbXMLString(data);
		
		return this.buildResult(trendingTopic, response);
	}
	
	private Boolean isFound(String response){
		if(response == null || response.equals("")) return false;
		return response.equals("True");
	}
	
	private TrendingTopicClassification buildResult(String trendingTopic, String response){
		TrendingTopicClassification ttresult = new TrendingTopicClassification(trendingTopic);
		ttresult.setMethod(this.method);
		if(isFound(response)){
			ttresult.setResult(TrendingTopicClassification.TTStatus.FOUND);
			ttresult.setCategory("Film");
		}
		else{
			ttresult.setResult(TrendingTopicClassification.TTStatus.NOT_FOUND);
		}
		return ttresult;
	}

}