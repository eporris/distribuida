package actors.sum;

public class SumKickOffMessage {
	
	private int n;
	
	public SumKickOffMessage (int n){
		this.n = n;
	}

	public int getN(){
		return n;
	}
}
