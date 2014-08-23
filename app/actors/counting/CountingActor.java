package actors.counting;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class CountingActor extends UntypedActor {

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof CountDown){
			int m=((CountDown) message).getN();
			if(m>0){
				System.out.println(getSelf().toString()+" : "+m);
				getSender().tell(new CountDown(m-1), getSelf());
			}
		}
		if (message instanceof KickOff){
			int m=((KickOff) message).getN();
			ActorRef actorRef = ((KickOff) message).getActor();
			System.out.println(getSelf().toString()+" : "+m);
			actorRef.tell(new CountDown(m-1), getSelf());
		}
		if (message instanceof ChildsCountDown){
			int m=((ChildsCountDown)message).getN();
			System.out.println("Starting childs to count to "+ m);
			ActorRef child1 = getContext().actorOf(Props.create(CountingActor.class), "child-1");
			ActorRef child2 = getContext().actorOf(Props.create(CountingActor.class), "child-2");
			child1.tell(new KickOff(child2,m),self());
		}
	}

}
