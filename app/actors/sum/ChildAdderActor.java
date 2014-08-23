package actors.sum;

import akka.actor.ActorRef;
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
 			ActorRef parent =getContext().parent();
 			parent.tell(new ResultSumMessage(sum), self());
		}		
	}		
}
