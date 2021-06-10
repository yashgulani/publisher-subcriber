package pub_sub.subscriber;

import pub_sub.action.PublisherSubscriberService;

public class SubscriberImpl extends Subscriber{
  //Add subscriber with PublisherSubscriberService for a topic
  public void addSubscriber(String topic, PublisherSubscriberService PublisherSubscriberService){
    PublisherSubscriberService.addSubscriber(topic, this);
  }

  //Unsubscribe subscriber with PublisherSubscriberService for a topic
  public void unSubscribe(String topic, PublisherSubscriberService PublisherSubscriberService){
    PublisherSubscriberService.removeSubscriber(topic, this);
  }

  //Request specifically for messages related to topic from PublisherSubscriberService
  public void getMessagesForSubscriberOfTopic(String topic, PublisherSubscriberService PublisherSubscriberService) {
    PublisherSubscriberService.getMessagesForSubscriberOfTopic(topic, this);
  }
}
