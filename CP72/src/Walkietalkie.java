/**
 * models the communication device between 
 * the ghost-buster and his friends the ghost-counters.
 * 
 * @author Michael Single, 08-917-445
 */

public class Walkietalkie {
	private boolean messageShouldStop;
	
	/**
	 * walkietalkie constructor.
	 */
	public Walkietalkie(){
		this.messageShouldStop = false;
	}
	
	/**
	 * tell our worker to stop working
	 */
	public synchronized void sayStopMessage(){
		this.messageShouldStop = true;
	}
	
	/**
	 * tell our workers again to continue to work.
	 */
	public synchronized void sayContinueMessage(){
		this.messageShouldStop = false;
		notifyAll();
	}
	
	/**
	 * get the current message flag:
	 * if true we should stop working.
	 * @return
	 */
	public boolean getMessage(){
		return this.messageShouldStop;
	}
	
	/**
	 * lets a caller wait until he gets 
	 * notified and the order to stop working 
	 * has been changed to do work.
	 */
	public synchronized void waitForOrders(){
		while(messageShouldStop){
			try {
				wait();
			} catch (InterruptedException e) {}
		}
	}
}
