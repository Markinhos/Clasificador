package clasificador;

public class TTResult {

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
