/**
 * represents a ghost counting friend.
 * @author simplaY
 *
 */

public class GhostCounter implements Runnable{
	private HousePart housePart;
	private String name;
	private Walkietalkie walkietalkie;
	
	public GhostCounter(String name, HousePart part, Walkietalkie wt){
		this.name = name;
		this.housePart = part;
		this.walkietalkie = wt;
	}
	
	@Override
	public void run() {
		while(true){
			if(this.hasATenPercentProbability()){
				this.housePart.letInAGhost();
				System.out.println(this.name + " let in " + 
						this.housePart.getGhostCount() + " ghost(s)");
			}
			
			waitTenMS();
			
			if(this.hasReceivedStoppingMessage()){
				System.out.println(this.name + " stops letting in ghosts");
				this.StopLettingIn();
			}
		}
		
	}
	
	private boolean hasReceivedStoppingMessage(){
		boolean answer = false;
		boolean shouldIStop = this.walkietalkie.getMessage();
		if(shouldIStop) answer = true;
		return answer ;
	}
	
	private void StopLettingIn(){
		//synchronized(walkietalkie){
			this.walkietalkie.waitForOrders();
			System.out.println(this.name + " again is letting in ghosts");
		//}
	}
	
	private void waitTenMS(){
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {}
	}
	
	/**
	 * simulate the luck of ten percent by using 
	 * random numbers.
	 * whenever we have been lucky, answer with true.
	 * get a random number via Math.random()
	 * care: Math.random returns a value which is
	 * at least equals 0 BUT less than 1. in order
	 * to get a range [0,100] (i.e. 100 is a valid value 
	 * and therefore can be taken) we have to add 1
	 * after having out received random number multiplied by 100.
	 * @return returns if we have been lucky with a percentage of ten.
	 */
	private boolean hasATenPercentProbability(){
		boolean answer = false;
		int percentage = (int) (Math.random() * 100)+1;
		if(percentage <= 10) answer = true;
		return answer;
	}

}
