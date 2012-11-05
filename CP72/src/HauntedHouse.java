
public class HauntedHouse {
	private HousePart bottomLeftPart;
	private HousePart bottomRightPart;
	private HousePart topLeftPart;
	private HousePart topRightPart;
	private int killedGhosts = 0;
//	private int totalGhostCount;
	
	public HauntedHouse(){
		this.bottomLeftPart = new HousePart();
		this.bottomRightPart = new HousePart();
		this.topLeftPart = new HousePart();
		this.topRightPart = new HousePart();
		

//		this.totalGhostCount = 0;
	}
	
	/**
	 * get the current total ghost count
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
	
	public HousePart getBottomLeftPart(){
		return this.bottomLeftPart;
	}
	
	public HousePart getBottomRightPart(){
		return this.bottomRightPart;
	}
	
	public HousePart getTopLeftPart(){
		return this.topLeftPart;
	}
	
	public HousePart getTopRightPart(){
		return this.topRightPart;
	}
	
	public void removeGhost(){
		this.killedGhosts++;
	}
}
