package clasificador.clasificadores;

import clasificador.TrendingTopicClassification;

public class LexicalClassifier implements ClassifierMethod{

	private final String method = "LEXICAL";	
	
	private TrendingTopicClassification tryFeeling(String trendingTopic){
		TrendingTopicClassification tt = new TrendingTopicClassification(trendingTopic);
		tt.setMethod(this.method);
		if(trendingTopic.matches("(\\s+|^)(hate|love|happy)(\\s+|$)")){
			tt.setResult(TrendingTopicClassification.TTStatus.FOUND);
			tt.setCategory("SENTIMIENTOS");
		}
		else{
			tt.setResult(TrendingTopicClassification.TTStatus.NOT_FOUND);
		}
		return tt;
	}

	@Override
	public TrendingTopicClassification tryClasify(String trendingTopic) {
		TrendingTopicClassification result;
		result = this.tryFeeling(trendingTopic);
		return result;
	}
}
