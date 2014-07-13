package clasificador;

public class TrendingTopicClassification {

	public enum TTStatus { FOUND, NOT_FOUND };
	private TTStatus result;
	private String category;
	private String method;
	private String ttTopic;
	
	public TrendingTopicClassification(String trendingTopic){
		this.ttTopic = trendingTopic;
	}

	public void setResult(TTStatus result){
		this.result = result;
	}
	public void setCategory(String category){
		this.category = category;
	}

	public TTStatus getResult(){
		return this.result;
	}
	public String getCategory(){
		return this.category;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	public String toString(){
		if(this.result == TrendingTopicClassification.TTStatus.FOUND)
			return this.ttTopic + ", " + this.category + ", " + this.method;
		else
			return this.ttTopic + ", NOT FOUND"; 
	}
}
