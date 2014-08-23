package actors.sum;

public class ResultSumMessage {
	
	private int result;
	
	
	public ResultSumMessage (int result){
		this.result = result;
	}

	public int getResult(){
		return result;
	}
	
}
