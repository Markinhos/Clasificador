package Clasificador;

public class Clasificador {
	public static String clasificarTT(String trendingTopic){
		TTResult result = null;
		result = tryHashtag(trendingTopic);
		if (result.getResult() == TTResult.TTStatus.FOUND){
			return trendingTopic + ", " + result.getCategory();
		}
		return trendingTopic;
	}
	
	private static TTResult tryHashtag(String trendingTopic){
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
}

class TTResult {

	public enum TTStatus { FOUND, NOT_FOUND };
	private TTStatus result;
	private String category;
	
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
}