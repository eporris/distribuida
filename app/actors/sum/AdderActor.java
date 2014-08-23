package actors.sum;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import actors.Timer;

public class AdderActor extends UntypedActor {
	
	private static final int unitsForActor=100;
	
	private int result=0;
	int numberOfChilds;
	int childrenFinished=0;
	Timer timer = new Timer();

	@Override
	public void onReceive(Object message) throws Exception {
		if(message instanceof SumKickOff){
			timer.start();			
			int n = ((SumKickOff)message).getN();
			numberOfChilds = n/unitsForActor;
			System.out.println("Starting "+numberOfChilds+ " childs");
			for(int i=1; i <= numberOfChilds; i++){
				ActorRef aChild = getContext().system().actorOf(Props.create(ChildAdderActor.class), "child-"+i);
				aChild.tell(new SumMessage(((i-1)*unitsForActor)+1, i*unitsForActor),self());
			}

		}
		if(message instanceof ResultSumMessage){
			int childSum = ((ResultSumMessage)message).getResult(); 
			result += childSum;
			childrenFinished++;
			if (childrenFinished==numberOfChilds){
				timer.end();
				System.out.println("Result: " +result);
				System.out.println("Tiempo utilizado en milisegundos: " +timer.elapsedTimeMilliseconds());
			}

	}

}


	
		
}
