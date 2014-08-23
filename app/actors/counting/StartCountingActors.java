package actors.counting;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class StartCountingActors {

	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("MySystem");
		ActorRef count1 = system.actorOf(Props.create(CountingActor.class), "counter1");
		ActorRef count2 = system.actorOf(Props.create(CountingActor.class), "counter2");
		count1.tell(new KickOff(count1, 10), ActorRef.noSender());
	}
}
