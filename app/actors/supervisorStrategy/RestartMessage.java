package actors.supervisorStrategy;

import akka.actor.ActorRef;

public class RestartMessage {
	private ActorRef actor;
	
	public RestartMessage(ActorRef actor){
		this.actor=actor;
	}

	public ActorRef getActor() {
		return actor;
	}

	
}
