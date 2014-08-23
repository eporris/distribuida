package actors.counting;

import akka.actor.ActorRef;

public class KickOff {
	private ActorRef actor;
	private int n;
	
	public KickOff(ActorRef actor, int n){
		this.actor=actor;
		this.n=n;
	}

	public int getN() {
		return n;
	}	

	public ActorRef getActor() {
		return actor;
	}

	
}
