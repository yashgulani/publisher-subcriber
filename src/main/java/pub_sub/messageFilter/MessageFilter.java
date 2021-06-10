package pub_sub.messageFilter;

import pub_sub.dto.FilterRequest;
import pub_sub.exceptions.MessageFilterException;

public interface MessageFilter {

	// Accepts a filter request, defining message on which filter is to be applied. This should
	// return true if message is to be discarded before being published to subscriber.
	boolean shouldFilterMessage(FilterRequest filterRequest) throws MessageFilterException;

	String getRegex();
}
