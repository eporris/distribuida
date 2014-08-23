package actors.factorial;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import actors.Timer;

public class FactorialActor extends UntypedActor {
	
	private static final int unitsForActor=5;
	
	private long result=1;
	int numberOfChilds;
	int childrenFinished=0;
	Timer timer = new Timer();

	@Override
	public void onReceive(Object message) throws Exception {
		if(message instanceof FactorialKickOff){
			timer.start();			
			int n = ((FactorialKickOff)message).getN();
			numberOfChilds = n/unitsForActor;
			System.out.println("Starting "+numberOfChilds+ " childs");
			for(int i=1; i <= numberOfChilds; i++){
				ActorRef aChild = getContext().system().actorOf(Props.create(ChildFactorialActor.class), "child-"+i);
				aChild.tell(new FactorialMessage(((i-1)*unitsForActor)+1, i*unitsForActor),self());
			}

		}
		if(message instanceof ResultFactorialMessage){
			long childFactorial = ((ResultFactorialMessage)message).getResult(); 
			System.out.println("Result: " +result);
			result = result * childFactorial;
			System.out.println("Result: " +result);
			childrenFinished++;
			if (childrenFinished==numberOfChilds){
				timer.end();
				System.out.println("Result: " +result);
				System.out.println("Tiempo utilizado en milisegundos: " +timer.elapsedTimeMilliseconds());
			}

	}

}


	
		
}
