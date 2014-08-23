package actors.factorial;

import java.util.concurrent.TimeUnit;

import scala.concurrent.duration.Duration;
import actors.counting.CountingActor;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class ChildFactorialActor extends UntypedActor {
	
	
	@Override
	public void onReceive(Object message) throws Exception {
		if(message instanceof FactorialMessage){
			int start = ((FactorialMessage)message).getStart(); 
			int end = ((FactorialMessage)message).getEnd();
			System.out.println(getSelf().toString()+" - Start: " + start + " End: "+end);
			long factorial=1;
			for(int i=start; i<= end; i++){
				factorial = factorial* i;
			}
 			ActorSelection parent =getContext().actorSelection("/user/factorial-1");
 			parent.tell(new ResultFactorialMessage(factorial), self());
		}
		
}


	
		
}
