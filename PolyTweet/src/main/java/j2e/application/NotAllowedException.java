package j2e.application;

public class NotAllowedException extends Exception {
	
	public NotAllowedException(String message){
		super(""+message);
	}
}
