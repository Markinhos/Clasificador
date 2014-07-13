package clasificador.clasificadores;

import clasificador.TrendingTopicClassification;
import clasificador.http.HttpConnection;
import clasificador.xmlParser.XmlParser;


public class ClasificadorDBPedia extends APIClassifier{
	private final String method = "DBPEDIA";
	private static final String dbPediaEndpoint = "http://lookup.dbpedia.org/api/search.asmx/KeywordSearch?QueryString=";		

	public ClasificadorDBPedia(){
		super(ClasificadorDBPedia.dbPediaEndpoint);
	}
	@Override
	public TrendingTopicClassification tryClasify(String trendingTopic) {		

		String data = this.getAPIdata(trendingTopic);	

		XmlParser xmlParser = new XmlParser();
		String label = xmlParser.getLabelFromDBPediaXMLString(data);

		return this.buildResult(trendingTopic, label);
	}
	
	

	private TrendingTopicClassification buildResult(String trendingTopic, String label){
		TrendingTopicClassification result = new TrendingTopicClassification(trendingTopic);
		if(isFound(label)){
			result.setResult(TrendingTopicClassification.TTStatus.FOUND);
			result.setCategory(label);
			result.setMethod(this.method);
		}
		else{
			result.setResult(TrendingTopicClassification.TTStatus.NOT_FOUND);			
		}
		return result;
	}
	

	private Boolean isFound(String label){
		if(label == null || label.equals("")){
			return false;
		}
		return true;
	}

}
