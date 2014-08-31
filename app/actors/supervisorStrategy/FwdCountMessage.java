package actors.supervisorStrategy;

import akka.actor.ActorRef;

public class FwdCountMessage {
	private ActorRef from;
	private ActorRef to;
	private int n;
	
	public FwdCountMessage(int n,ActorRef from, ActorRef to){
		this.from=from;
		this.to=to;
		this.n=n;
	}

	public int getN() {
		return n;
	}

	public ActorRef getFrom() {
		return from;
	}

	public void setFrom(ActorRef from) {
		this.from = from;
	}

	public ActorRef getTo() {
		return to;
	}

	public void setTo(ActorRef to) {
		this.to = to;
	}	
	
}
