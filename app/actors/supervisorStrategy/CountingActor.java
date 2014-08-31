package actors.supervisorStrategy;

import akka.actor.ActorRef;
import akka.actor.AllForOneStrategy;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.UntypedActor;
import akka.actor.SupervisorStrategy.Directive;
import akka.japi.Function;
import scala.concurrent.duration.Duration;
import static akka.actor.SupervisorStrategy.resume;
import static akka.actor.SupervisorStrategy.restart;
import static akka.actor.SupervisorStrategy.stop;
import static akka.actor.SupervisorStrategy.escalate;

public class CountingActor extends UntypedActor {
	
	private static SupervisorStrategy strategy =
		    new OneForOneStrategy(10, Duration.create("1 minute"), 
		      new Function<Throwable, SupervisorStrategy.Directive>() {
		        @Override
		        public Directive apply(Throwable t) {
		          if (t instanceof ArithmeticException) {
		            return resume();
		          } else if (t instanceof NullPointerException) {
		        	  System.out.println("supervisor null pointer---------");
		        	  
		            return restart();
		          } else if (t instanceof IllegalArgumentException) {
		            return stop();
		          } else {
		            return escalate();
		          }
		        }
		      }
	);
	
	private static int n;
	private ActorRef actorFrom;
	private ActorRef actorTo;
		 
	@Override
	public SupervisorStrategy supervisorStrategy() {
	  return strategy;
	}
	
	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof FwdCountMessage){			
			n =((FwdCountMessage) message).getN();
			actorFrom = ((FwdCountMessage) message).getFrom();
			actorTo = ((FwdCountMessage) message).getTo();
			System.out.println(getSelf().toString()+" N : "+ n + " FROM: "+actorFrom + " TO: "+ actorTo);
			actorTo.forward(message, getContext());
		}
		if (message instanceof ChildsCountDown){			
			n =((ChildsCountDown)message).getN();
			System.out.println("Starting childs to count to "+ n);
			ActorRef child1 = getContext().actorOf(Props.create(ChildCountingActor.class), "child-1");
			ActorRef child2 = getContext().actorOf(Props.create(ChildCountingActor.class), "child-2");
			child1.tell(new FwdCountMessage( n, child2 ,child1), self());
		}
		if (message instanceof RestartMessage){
			ActorRef actorAux= actorFrom;
			actorFrom = actorTo;
			actorTo = actorAux;
			System.out.println(getSelf().toString()+" N : "+ n + " FROM: "+actorFrom + " TO: "+ actorTo);
			actorTo.tell(new FwdCountMessage(n-1, actorFrom, actorTo), actorFrom);
		}
			
	}
	

}
