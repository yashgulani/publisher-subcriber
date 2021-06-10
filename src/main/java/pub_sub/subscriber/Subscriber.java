package pub_sub.subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import pub_sub.action.PublisherSubscriberService;
import pub_sub.dto.Message;
import pub_sub.exceptions.InvalidFilterException;
import pub_sub.messageFilter.MessageFilter;

public abstract class Subscriber {

  private String subscriberName;

  public Subscriber(String subscriberName) {
    this.subscriberName = subscriberName;
  }

  private List<Message> subscriberMessages = new ArrayList<Message>();
	private final List<MessageFilter> messageFilters = new ArrayList<>();

	public List<Message> getSubscriberMessages() {
		return subscriberMessages;
	}

	public List<MessageFilter> getMessageFilters() {
		return messageFilters;
	}

	public void setSubscriberMessages(List<Message> subscriberMessages) {
		this.subscriberMessages = subscriberMessages;
	}

	//Add subscriber with PubSubService for a topic
	public abstract void addSubscriber(String topic, PublisherSubscriberService pubSubService);

	//Unsubscribe subscriber with PubSubService for a topic
	public abstract void unSubscribe(String topic, PublisherSubscriberService pubSubService);

	//Request specifically for messages related to topic from PubSubService
	public abstract void getMessagesForSubscriberOfTopic(String topic,
		PublisherSubscriberService pubSubService);

	public void addMessageFilter(MessageFilter messageFilter) throws InvalidFilterException {
		if (Objects.nonNull(messageFilter)) {
			messageFilters.add(messageFilter);
		} else {
			throw new InvalidFilterException("Filter cannot be null");
		}
	}

	public void addMessageFilters(ArrayList<MessageFilter> messageFilter) {
		messageFilters
			.addAll(messageFilters.stream().filter(Objects::nonNull).collect(Collectors.toList()));
	}


	//Print all messages received by the subscriber
	public void printMessages() {
    System.out.println(String.format("Filters for %s",subscriberName));
    for (MessageFilter filter: messageFilters) {
      System.out.println(filter.getRegex());
    }
    System.out.println(String.format("Events Consumed by %s", subscriberName));
		for (Message message : subscriberMessages) {
			System.out.println(message.toString());
		}
    System.out.println();
	}

}
