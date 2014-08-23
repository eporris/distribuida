package actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class HelloWorld extends UntypedActor {

	@Override
	public void preStart() {

		System.out.println("start remote!");
		ActorRef greeter = getContext().actorFor("akka.tcp://ServerNode@127.0.0.1:2553/user/greeter");

		System.out.println("remote is " + greeter);
// tell it to perform the greeting
		greeter.tell("GREET", getSelf());
	}

	@Override
	public void onReceive(Object msg) {
		if (msg.equals("DONE")) {
			// when the greeter is done, stop this actor and with it the
			// application
			System.out.println("done!");
		} else
			unhandled(msg);
	}
}