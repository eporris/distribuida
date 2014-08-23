package actors.sum;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class StartAdderActors {

	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("MySystem");
		ActorRef adder1 = system.actorOf(Props.create(AdderActor.class), "adder-1");
		adder1.tell(new SumKickOffMessage(10000), ActorRef.noSender());
	}
}
