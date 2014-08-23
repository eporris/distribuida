package actors.factorial;

public class FactorialMessage {
	
	private int start;
	private int end;
	
	public FactorialMessage (int start, int end){
		this.start = start;
		this.end = end;
	}

	public int getStart(){
		return start;
	}
	
	public int getEnd(){
		return end;
	}
}
