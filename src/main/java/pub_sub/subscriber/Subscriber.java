package pub_sub.subscriber;

import java.util.ArrayList;
import java.util.List;
import pub_sub.action.PublisherSubscriberService;
import pub_sub.dto.Message;

public abstract class Subscriber {

  private List<Message> subscriberMessages = new ArrayList<Message>();

  public List<Message> getSubscriberMessages() {
    return subscriberMessages;
  }
  public void setSubscriberMessages(List<Message> subscriberMessages) {
    this.subscriberMessages = subscriberMessages;
  }

  //Add subscriber with PubSubService for a topic
  public abstract void addSubscriber(String topic, PublisherSubscriberService pubSubService);

  //Unsubscribe subscriber with PubSubService for a topic
  public abstract void unSubscribe(String topic, PublisherSubscriberService pubSubService);

  //Request specifically for messages related to topic from PubSubService
  public abstract void getMessagesForSubscriberOfTopic(String topic, PublisherSubscriberService pubSubService);

  //Print all messages received by the subscriber
  public void printMessages(){
    for(Message message : subscriberMessages){
      System.out.println(message.toString());
    }
  }

}
