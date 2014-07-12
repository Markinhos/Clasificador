package clasificador.clasificadores;

import clasificador.TTResult;

public interface ClassifierMethod {
	TTResult tryClasify(String trendingTopic);
}
