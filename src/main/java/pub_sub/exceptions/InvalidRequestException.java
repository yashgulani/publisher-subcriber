package pub_sub.exceptions;

public class InvalidRequestException extends MessageFilterException {

	public InvalidRequestException(String message) {
		super(message);
	}
}
