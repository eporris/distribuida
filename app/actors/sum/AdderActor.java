package actors.sum;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class AdderActor extends UntypedActor {
	
	private static final int unitsOneActor=1000;
	
	private int result=0;
	int numberOfChilds;
	int childrenFinished=0;	

	@Override
	public void onReceive(Object message) throws Exception {
		if(message instanceof SumKickOffMessage){						
			int n = ((SumKickOffMessage)message).getN();
			numberOfChilds = n/unitsOneActor;
			System.out.println("Starting "+numberOfChilds+ " childs");
			for(int i=1; i <= numberOfChilds; i++){
				ActorRef aChild = getContext().actorOf(Props.create(ChildAdderActor.class), "child-"+i);
				aChild.tell(new SumMessage(((i-1)*unitsOneActor)+1, i*unitsOneActor),self());
			}

		}
		if(message instanceof ResultSumMessage){
			int childSum = ((ResultSumMessage)message).getResult(); 
			result += childSum;
			childrenFinished++;
			if (childrenFinished==numberOfChilds){				
				System.out.println(self() + "Result: " +result);				
			}
	}
}		
}
