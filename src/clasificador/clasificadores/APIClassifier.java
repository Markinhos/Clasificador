package clasificador.clasificadores;

import clasificador.http.HttpConnection;

public abstract class APIClassifier extends AbstractClassifier {
	private String endpoint;
	
	protected APIClassifier(String endpoint, String trendingTopic){
		super(trendingTopic);
		this.endpoint = endpoint;
	}
	
	protected String getAPIdata(String trendingTopic){
		HttpConnection conn = new HttpConnection();
		String params = conn.encodeParams(trendingTopic);
		String data = conn.getData( this.endpoint + params);
		return data;
	}
}
