package actors.factorial;

public class ResultFactorialMessage {
	
	private long result;
	
	
	public ResultFactorialMessage (long result){
		this.result = result;
	}

	public long getResult(){
		return result;
	}
	
}
