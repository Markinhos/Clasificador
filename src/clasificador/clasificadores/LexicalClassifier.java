package clasificador.clasificadores;

import clasificador.TTResult;

public class LexicalClassifier implements ClassifierMethod{
	private TTResult tryHashtag(String trendingTopic){
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
	
	private TTResult tryFeeling(String trendingTopic){
		TTResult tt = new TTResult();
		if(trendingTopic.matches("(\\s+|^)(hate|love|happy)(\\s+|$)")){
			tt.setResult(TTResult.TTStatus.FOUND);
			tt.setCategory("SENTIMIENTOS");
		}
		else{
			tt.setResult(TTResult.TTStatus.NOT_FOUND);
		}
		return tt;
	}

	@Override
	public TTResult tryClasify(String trendingTopic) {
		TTResult result;
		result = this.tryFeeling(trendingTopic);
		return result;
	}
}
