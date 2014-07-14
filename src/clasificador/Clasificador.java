package clasificador;

import java.util.concurrent.Callable;

import clasificador.clasificadores.ClasificadorDBPedia;
import clasificador.clasificadores.ClassifierMethod;
import clasificador.clasificadores.LexicalClassifier;
import clasificador.clasificadores.OMDBClassifier;

public class Clasificador implements Callable<String>{
	private String trendingTopic;
	
	public Clasificador(String trendingTopic){
		this.trendingTopic = trendingTopic;
	}
	
	public String clasificarTT(){
		TrendingTopicClassification result = null;
		ClassifierMethod clasificador = new LexicalClassifier(this.trendingTopic);
		result = clasificador.tryClasify();

		if (result.getResult() == TrendingTopicClassification.TTStatus.FOUND){
			return result.toString();
		}
		else{
			clasificador = new ClasificadorDBPedia(this.trendingTopic);
			result = clasificador.tryClasify();
			if(result.getResult() == TrendingTopicClassification.TTStatus.FOUND){
				return result.toString();			
			}
			else{
				clasificador = new OMDBClassifier(this.trendingTopic);
				result = clasificador.tryClasify();
				return result.toString();
			}		
		}
	}
	

	


	@Override
	public String call() throws Exception {
		System.out.println("Running classification for " + this.trendingTopic);
		String result = this.clasificarTT();
		System.out.println("Finished classification for " + this.trendingTopic);
		return result;
	}
}

