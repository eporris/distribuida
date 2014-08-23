package actors.sum;

import java.util.concurrent.TimeUnit;

import scala.concurrent.duration.Duration;
import actors.counting.CountingActor;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class ChildAdderActor extends UntypedActor {
	
	
	@Override
	public void onReceive(Object message) throws Exception {
		if(message instanceof SumMessage){
			int start = ((SumMessage)message).getStart(); 
			int end = ((SumMessage)message).getEnd();
			System.out.println(getSelf().toString()+" - Start: " + start + " End: "+end);
			int sum=0;
			for(int i=start; i<= end; i++){
				sum += i;
			}
 			ActorSelection parent =getContext().actorSelection("/user/adder1");
 			parent.tell(new ResultSumMessage(sum), self());
		}
		
}


	
		
}
