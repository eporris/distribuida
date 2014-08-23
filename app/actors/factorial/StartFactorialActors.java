package actors.factorial;

import actors.counting.CountingActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class StartFactorialActors {

	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("MySystem");
		ActorRef factorial1 = system.actorOf(Props.create(FactorialActor.class), "factorial-1");
		factorial1.tell(new FactorialKickOff(20), ActorRef.noSender());
	}

}
