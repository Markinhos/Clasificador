package clasificador.clasificadores;

public abstract class AbstractClassifier implements ClassifierMethod{
	protected String trendingTopic;
	public AbstractClassifier(String trendingTopic){
		this.trendingTopic = trendingTopic;
	}
	private String parseTrendingTopic(String trendingTopic){
		String parsedTrendingTopic = trendingTopic;
		if (trendingTopic.startsWith("#")){
			parsedTrendingTopic = trendingTopic.replaceFirst("#", "");
		}
		return splitCamelCase(parsedTrendingTopic);
	}
	
	//Takes a string an splits it in different words from every capital letter.
	private String splitCamelCase(String s) {
		return s.replaceAll(
				String.format("%s|%s|%s",
						"(?<=[A-Z])(?=[A-Z][a-z])",
						"(?<=[^A-Z])(?=[A-Z])",
						"(?<=[A-Za-z])(?=[^A-Za-z])"
						),
						" "
				);
	}
	
	private String removeExtraWhitespace(String s){
		return s.replaceAll(" +", " ");
	}
	
	protected String getTrendingTopicFormatted(){
		return removeExtraWhitespace(parseTrendingTopic(trendingTopic));
	}
}
