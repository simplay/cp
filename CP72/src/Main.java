
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Walkietalkie wt = new Walkietalkie();
		HauntedHouse hauntedHouse = new HauntedHouse();
		Thread ghostbuster = new Thread(new Ghostbuster("ghostbuster", hauntedHouse, wt));
		Thread friend1 = new Thread(new GhostCounter("friend1", hauntedHouse.getBottomLeftPart(), wt));
		Thread friend2 = new Thread(new GhostCounter("friend2", hauntedHouse.getBottomRightPart(), wt));
		Thread friend3 = new Thread(new GhostCounter("friend3", hauntedHouse.getTopLeftPart(), wt));
		Thread friend4 = new Thread(new GhostCounter("friend4", hauntedHouse.getTopRightPart(), wt));
		
		ghostbuster.start();
		friend1.start();
		friend2.start();
		friend3.start();
		friend4.start();
	}

}
