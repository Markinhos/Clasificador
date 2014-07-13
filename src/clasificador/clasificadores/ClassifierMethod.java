package clasificador.clasificadores;

import clasificador.TrendingTopicClassification;

public interface ClassifierMethod {
	TrendingTopicClassification tryClasify(String trendingTopic);
}
