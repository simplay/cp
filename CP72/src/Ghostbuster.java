/**
 * models the ghostbuster which has to kill the incoming ghosts
 * let in by his friends, the ghost counters. he has a certain success
 * probability to kill a ghost inside the house.
 * he also communicates with his friends to control the flow 
 * of letting new ghosts in or stop letting in new ghosts, depending 
 * on the amount of ghosts inside the building and his tolerance.
 * 
 * @author Michael Single, 08-917-445
 */
public class Ghostbuster implements Runnable{
	
	private String name;
	private HauntedHouse hauntedHouse;
	private Walkietalkie walkietalkie;
	private int maxGhostTolerance;
	private boolean givenStopOrder;
	private int currentGhostCounter;
	private int totalGhostCount;
	
	/**
	 * constructor for a new ghostbuster.
	 * @param name its name
	 * @param house the haunted house he is assigned to.
	 * @param walkietalkie communicator for him and his friends.
	 */
	public Ghostbuster(String name, HauntedHouse house, Walkietalkie walkietalkie){
		this.name = name;
		this.hauntedHouse = house;
		this.walkietalkie = walkietalkie;
		this.maxGhostTolerance = 10;
		this.givenStopOrder = false;
		this.currentGhostCounter = 0;
		this.totalGhostCount = 0;
	}
	
	@Override
	public void run() {
		while(true){
			this.perfromCheckEveryTwoSeconds();
			if(this.hasEnoughGhosts()) this.killGhost();		
			this.waitTenMS();
		}
	}
	
	/**
	 * do we still have enough ghosts available to kill
	 * relying on the count received from our friends.
	 * @return
	 */
	private boolean hasEnoughGhosts(){
		boolean answer = false;
		if(this.totalGhostCount > 0) answer = true;
		return answer;
	}
	
	/**
	 * every 2 seconds a ghostbuster has to perform the following 
	 * checks and also asked his friends for their current
	 * amount of ghosts they have counted to the moment they were asked.
	 * if there are to many ghosts let into the house within this 2 seconds,
	 * then notify our friends via walkietalkie to stop letting ghosts inside 
	 * the house and says that he has reached his maximum tolerance of ghosts.
	 * if there are too few ghosts inside the house a ghostbuster has to inform
	 * his friends via walkietalkie to let more ghosts into the house.
	 */
	private void perfromCheckEveryTwoSeconds(){
		if(TwoSecondsReached()){
			totalGhostCount = this.hauntedHouse.getTotalGhostCount();
			
			if(this.hasCountedTooManyGhosts() && !this.hasGivenStopOrder()){
				this.walkietalkie.sayStopMessage();
				this.givenStopOrder = true;
				System.out.println(name + " has reached its max tollerance " +
						"and tells his friends to stop" );
			}
			
			if(this.shouldTellFriendsWorkAgain()){
				this.walkietalkie.sayContinueMessage();
				this.givenStopOrder = false;
			}
		}
	}
	
	/**
	 * kill a ghost which is inside the house
	 * with a 40% success probability.
	 */
	private void killGhost(){
		if (this.hasAFortyPercentProbability()) {
			this.hauntedHouse.removeGhost();
			totalGhostCount--;
			System.err.println(this.name + " killed one ghost." 
					+ "left: " + totalGhostCount);
		}

		
	}
	
	/**
	 * check if two seconds have passed.
	 * @return
	 */
	private boolean TwoSecondsReached(){
		boolean answer = false;
		int secondsPassed = currentGhostCounter*200;
		if(secondsPassed == 2000){
			answer = true;
			this.currentGhostCounter = 0;
		}
		return answer;
	}
	
	/**
	 * simulate the luck of forty percent by using 
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
	private boolean hasAFortyPercentProbability(){
		boolean answer = false;
		int percentage = (int) (Math.random() * 100)+1;
		if(percentage <= 40) answer = true;
		return answer;
	}
	
	/**
	 * simulate a 10ms wait
	 */
	private void waitTenMS(){
		currentGhostCounter++;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {}
	}
	
	/**
	 * checks if there was any stop order given.
	 * @return
	 */
	private boolean hasGivenStopOrder(){
		return this.givenStopOrder;
	}
	
	/**
	 * should this ghostbuster notify his friends via 
	 * walkietalkie to start working again.
	 * this is the case when the order to stop 
	 * previously has been given AND the haunted house 
	 * itself has enough space available for new ghosts.
	 * @return
	 */
	private boolean shouldTellFriendsWorkAgain(){
		boolean answer = false;
		if(this.hasGivenStopOrder() 
				&& this.houseHasSpaceForGhosts())
			answer = true;
		return answer;
	}
	
	/**
	 * checks if the house has enough space available for new ghosts.
	 * @return
	 */
	private boolean houseHasSpaceForGhosts(){
		int totalGhostCount = hauntedHouse.getTotalGhostCount();
		return totalGhostCount < (this.maxGhostTolerance/2);
	}
	
	/**
	 * checks if there are currently too many ghosts in the house.
	 * this usually implies that the ghostbuster will order his friends 
	 * stop letting new ghosts inside the haunted house.
	 * @return
	 */
	private boolean hasCountedTooManyGhosts(){
		boolean answer = false;
		int totalGhostCount = hauntedHouse.getTotalGhostCount();
		if(totalGhostCount > this.maxGhostTolerance) answer = true;
		return answer;
	}

}
