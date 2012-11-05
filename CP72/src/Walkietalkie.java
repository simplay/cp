
public class Walkietalkie {
	private boolean messageShouldStop;
	public Walkietalkie(){
		this.messageShouldStop = false;
	}
	
	public synchronized void sayStopMessage(){
		this.messageShouldStop = true;
	}
	
	public synchronized void sayContinueMessage(){
		this.messageShouldStop = false;
		notifyAll();
	}
	
	public boolean getMessage(){
		return this.messageShouldStop;
	}
	
	
	public synchronized void waitForOrders(){
		while(messageShouldStop){
			try {
				wait();
			} catch (InterruptedException e) {}
		}
	}
}
