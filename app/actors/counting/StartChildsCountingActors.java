package actors.counting;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class StartChildsCountingActors {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("MySystem");
		ActorRef count1 = system.actorOf(Props.create(CountingActor.class), "counter1");		
		count1.tell(new ChildsCountDown(10) , ActorRef.noSender());
	}

}
