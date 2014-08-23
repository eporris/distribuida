package actors.sum;

public class SumMessage {
	
	private int start;
	private int end;
	
	public SumMessage (int start, int end){
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
