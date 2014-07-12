package clasificador;

import clasificador.clasificadores.ClasificadorDBPedia;
import clasificador.clasificadores.ClassifierMethod;
import clasificador.clasificadores.LexicalClassifier;

public class Clasificador {
	public static String clasificarTT(String trendingTopic){
		TTResult result = null;
		ClassifierMethod clasificador = new LexicalClassifier();
		result = clasificador.tryClasify(trendingTopic);
		
		if (result.getResult() == TTResult.TTStatus.FOUND){
			return trendingTopic + ", " + result.getCategory();
		}
		else{
			clasificador = new ClasificadorDBPedia();
			result = clasificador.tryClasify(trendingTopic);
			return trendingTopic + ", " + result.getCategory();			
		}
	}
}

