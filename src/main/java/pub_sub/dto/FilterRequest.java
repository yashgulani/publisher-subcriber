package pub_sub.dto;

public class FilterRequest {

	// Defines body on which regex is to be applied.
	private final Message message;

	public FilterRequest(Message message) {
		this.message = message;
	}

	public Message getMessage() {
		return message;
	}
}
