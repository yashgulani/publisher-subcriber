package pub_sub.publisher;

import pub_sub.action.PublisherSubscriberService;
import pub_sub.dto.Message;

public interface Publisher {

	void publish(Message event, PublisherSubscriberService publisherSubscriberService);

}
