/**
 * simulates the haunted house
 * where ghosts are counted,
 * let in by the ghost counters and killed 
 * by the ghost buster.
 * 
 * @author simplaY
 */

public class HauntedHouse {
	private HousePart bottomLeftPart;
	private HousePart bottomRightPart;
	private HousePart topLeftPart;
	private HousePart topRightPart;
	private int killedGhosts;
	
	public HauntedHouse(){
		this.bottomLeftPart = new HousePart();
		this.bottomRightPart = new HousePart();
		this.topLeftPart = new HousePart();
		this.topRightPart = new HousePart();
		this.killedGhosts = 0;
	}
	
	/**
	 * get the current total ghost count
	 * this is the sum of all ghosts counted by the ghost counters
	 * minus the amount of ghosts killed by the ghost buster.
	 * remark this method does not have to be synchronized
	 * but when we ask a friend about his ghost count 
	 * he can't do anything else meanwhile being asked
	 * then just telling us the count but afterwards,
	 * when he was asked, he continues doing his stuff
	 * he even does not wait until other friends answered.
	 * @return
	 */
	public int getTotalGhostCount(){
		int a = this.bottomLeftPart.getGhostCount();
		int b = this.bottomRightPart.getGhostCount();
		int c = this.topLeftPart.getGhostCount();
		int d = this.topRightPart.getGhostCount();
		return (a+b+c+d)-this.killedGhosts;
	}
	
	/**
	 * get bottom left part of the house.
	 * @return
	 */
	public HousePart getBottomLeftPart(){
		return this.bottomLeftPart;
	}
	
	/**
	 * get bottom right part of the house.
	 * @return
	 */
	public HousePart getBottomRightPart(){
		return this.bottomRightPart;
	}
	
	/**
	 * get top left part of the house.
	 * @return
	 */
	public HousePart getTopLeftPart(){
		return this.topLeftPart;
	}
	
	/**
	 * get top right part of the house.
	 * @return
	 */
	public HousePart getTopRightPart(){
		return this.topRightPart;
	}
	
	/**
	 * remove a ghost by incrementing the killed ghosts counter.
	 */
	public void removeGhost(){
		this.killedGhosts++;
	}
}
