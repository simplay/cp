
public class Ghostbuster implements Runnable{
	
	private String name;
	private HauntedHouse hauntedHouse;
	private Walkietalkie walkietalkie;
	private int maxGhostTollerance = 10;
	private boolean givenStopOrder = false;
	
	public Ghostbuster(String name, HauntedHouse house, Walkietalkie wt){
		this.name = name;
		this.hauntedHouse = house;
		this.walkietalkie = wt;
	}
	
	@Override
	public void run() {
		while(true){
			if(this.hauntedHouse.getTotalGhostCount() > 0) killGhost();
			
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
			waitTenMS();
		}
	}
	
	private void killGhost(){
		if (this.hasAFortyPercentProbability()) {
			this.hauntedHouse.removeGhost();
			System.err.println(this.name + " killed one ghost." 
					+ "left: " + this.hauntedHouse.getTotalGhostCount());
		}

		
	}
	
	private boolean hasAFortyPercentProbability(){
		boolean answer = false;
		int percentage = (int) (Math.random() * 100)+1;
		if(percentage <= 40) answer = true;
		return answer;
	}
	
	private void waitTenMS(){
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {}
	}
	
	private boolean hasGivenStopOrder(){
		return this.givenStopOrder;
	}
	
	private boolean shouldTellFriendsWorkAgain(){
		boolean answer = false;
		if(this.hasGivenStopOrder() 
				&& this.houseHasSpaceForGhosts())
			answer = true;
		return answer;
	}
	
	private boolean houseHasSpaceForGhosts(){
		int totalGhostCount = hauntedHouse.getTotalGhostCount();
		return totalGhostCount < (this.maxGhostTollerance/2);
	}
	
	
	private boolean hasCountedTooManyGhosts(){
		boolean answer = false;
		int totalGhostCount = hauntedHouse.getTotalGhostCount();
		if(totalGhostCount > this.maxGhostTollerance) answer = true;
		
		return answer;
	}

}
