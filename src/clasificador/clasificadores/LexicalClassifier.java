package clasificador.clasificadores;

import clasificador.TrendingTopicClassification;

public class LexicalClassifier extends AbstractClassifier{

	private final String method = "LEXICAL";	
	
	public LexicalClassifier(String trendingTopic){
		super(trendingTopic);
	}
	
	private TrendingTopicClassification tryFeeling(){		
		TrendingTopicClassification tt = new TrendingTopicClassification(this.trendingTopic);
		tt.setMethod(this.method);
		if(this.getTrendingTopicFormatted().toLowerCase().matches("(.*\\s+|^)(hate|love|happy)(\\s+.*|$)")){
			tt.setResult(TrendingTopicClassification.TTStatus.FOUND);
			tt.setCategory("Feelings");
		}
		else{
			tt.setResult(TrendingTopicClassification.TTStatus.NOT_FOUND);
		}
		return tt;
	}
	
	private TrendingTopicClassification trySportEvent(){		
		TrendingTopicClassification tt = new TrendingTopicClassification(this.trendingTopic);
		tt.setMethod(this.method);
		if(this.getTrendingTopicFormatted().matches("(.+)[0-9]*(VS|vs|x|\\-)[0-9]*(.+)") 
				|| this.getTrendingTopicFormatted().toLowerCase().matches("(\\s+|^)(gol)(\\s+|$)")){
			tt.setResult(TrendingTopicClassification.TTStatus.FOUND);
			tt.setCategory("Sport Event");			
		}
		else{
			tt.setResult(TrendingTopicClassification.TTStatus.NOT_FOUND);		
		}
		return tt;
	}

	@Override
	public TrendingTopicClassification tryClasify() {
		TrendingTopicClassification result;
		result = this.tryFeeling();
		if(result.getResult() == TrendingTopicClassification.TTStatus.NOT_FOUND){
			result = this.trySportEvent();
		}
		return result;
	}
}
