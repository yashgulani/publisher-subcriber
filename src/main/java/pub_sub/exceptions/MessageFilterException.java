package pub_sub.exceptions;

public class MessageFilterException extends Exception {

	public MessageFilterException(String message, Throwable cause) {
		super(message, cause);
	}

	public MessageFilterException(String message) {
		super(message);
	}
}
