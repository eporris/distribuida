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

public class ChildCountingActor extends UntypedActor {
	

	

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof FwdCountMessage){			
			int m=((FwdCountMessage) message).getN();
			ActorRef actorFrom = ((FwdCountMessage) message).getFrom();
			ActorRef actorTo = ((FwdCountMessage) message).getTo();
			if(m==7){
				System.out.println(getSelf().toString()+" : "+m);
				System.out.println("Se lanza la Excepcion NullPointer");
				throw new NullPointerException("Lanzada por un hijo");
			}
			if(m>0){				
				System.out.println(getSelf().toString()+" : "+m);
				getContext().parent().tell(new FwdCountMessage((m-1), getSelf(), actorFrom),getSelf());
			}
			else{				
				getContext().system().shutdown();
			}
		}
	
	}
	
	@Override
	public void postRestart (Throwable t){
		System.out.println("postStop--- "+getSelf());
		getContext().parent().tell(new RestartMessage(getSelf()),self());
	}

}
