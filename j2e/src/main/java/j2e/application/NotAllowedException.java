package j2e.application;

public class NotAllowedException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NotAllowedException(String message){
		super(message);
	}
}
