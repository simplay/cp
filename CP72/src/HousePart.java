
public class HousePart {
	private int ghostCount;
	
	public HousePart(){
		this.ghostCount = 0;
	}
	
	public synchronized void letInAGhost(){
		this.ghostCount++;
	}
	
	public synchronized int getGhostCount(){
		return this.ghostCount;
	}
}
