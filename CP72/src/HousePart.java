/**
 * models a part of the house where a ghost counter will be located 
 * in oder to count ghosts.
 * 
 * @author simplaY
 */

public class HousePart {
	private int ghostCount;
	
	/**
	 * constructor of a house part.
	 */
	public HousePart(){
		this.ghostCount = 0;
	}
	
	/**
	 * let a new ghost inside the house.
	 */
	public synchronized void letInAGhost(){
		this.ghostCount++;
	}
	
	/**
	 * get the amount of ghosts located at that part of the house.
	 * @return
	 */
	public synchronized int getGhostCount(){
		return this.ghostCount;
	}
}
