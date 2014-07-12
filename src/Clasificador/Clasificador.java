package clasificador;

import clasificador.clasificadores.ClasificadorDBPedia;
import clasificador.clasificadores.ClassifierMethod;
import clasificador.clasificadores.LexicalClassifier;

public class Clasificador {
	public static String clasificarTT(String trendingTopic){
		String parsedTrendingTopic = parseTrendingTopic(trendingTopic);
		TTResult result = null;
		ClassifierMethod clasificador = new LexicalClassifier();
		result = clasificador.tryClasify(parsedTrendingTopic);

		if (result.getResult() == TTResult.TTStatus.FOUND){
			return trendingTopic + ", " + result.getCategory();
		}
		else{
			clasificador = new ClasificadorDBPedia();
			result = clasificador.tryClasify(parsedTrendingTopic);
			return trendingTopic + ", " + result.getCategory();			
		}
	}

	private static String parseTrendingTopic(String trendingTopic){
		String parsedTrendingTopic = trendingTopic;
		if (trendingTopic.startsWith("#")){
			parsedTrendingTopic = trendingTopic.replaceFirst("#", "");
		}
		return splitCamelCase(parsedTrendingTopic);
	}
	
	//Takes a string an splits it in different words from every capital letter.
	private static String splitCamelCase(String s) {
		return s.replaceAll(
				String.format("%s|%s|%s",
						"(?<=[A-Z])(?=[A-Z][a-z])",
						"(?<=[^A-Z])(?=[A-Z])",
						"(?<=[A-Za-z])(?=[^A-Za-z])"
						),
						" "
				);
	}
}

