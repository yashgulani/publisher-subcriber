package pub_sub.publisher;

import pub_sub.action.PublisherSubscriberService;
import pub_sub.dto.Message;

public class PublisherImpl implements Publisher {

  @Override
  public void publish(Message message, PublisherSubscriberService publisherSubscriberService) {
    publisherSubscriberService.addMessageToQueue(message);
  }
}
