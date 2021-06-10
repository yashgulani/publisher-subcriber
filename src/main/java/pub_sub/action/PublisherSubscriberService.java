package pub_sub.action;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.List;
import pub_sub.dto.Message;
import pub_sub.subscriber.Subscriber;

public class PublisherSubscriberService {

  Map<String, Set<Subscriber>> subscribersTopicMap = new HashMap<String, Set<Subscriber>>();

  Queue<Message> messagesQueue = new LinkedList<Message>();

  public void addMessageToQueue(Message message){
    messagesQueue.add(message);
  }

  //Add a new Subscriber for a topic
  public void addSubscriber(String topic, Subscriber subscriber){

    if(subscribersTopicMap.containsKey(topic)){
      Set<Subscriber> subscribers = subscribersTopicMap.get(topic);
      subscribers.add(subscriber);
      subscribersTopicMap.put(topic, subscribers);
    }else{
      Set<Subscriber> subscribers = new HashSet<Subscriber>();
      subscribers.add(subscriber);
      subscribersTopicMap.put(topic, subscribers);
    }
  }

  public void removeSubscriber(String topic, Subscriber subscriber){

    if(subscribersTopicMap.containsKey(topic)){
      Set<Subscriber> subscribers = subscribersTopicMap.get(topic);
      subscribers.remove(subscriber);
      subscribersTopicMap.put(topic, subscribers);
    }
  }

  public void broadcast(){
    if(messagesQueue.isEmpty()){
      System.out.println("No messages from publishers to display");
    }else{
      while(!messagesQueue.isEmpty()){
        Message message = messagesQueue.remove();
        String topic = message.getTopic();

        Set<Subscriber> subscribersOfTopic = subscribersTopicMap.get(topic);

        for(Subscriber subscriber : subscribersOfTopic){
          //add broadcasted message to subscribers message queue
          List<Message> subscriberMessages = subscriber.getSubscriberMessages();
          subscriberMessages.add(message);
          subscriber.setSubscriberMessages(subscriberMessages);
        }
      }
    }
  }

  public void getMessagesForSubscriberOfTopic(String topic, Subscriber subscriber) {
    if(messagesQueue.isEmpty()){
      System.out.println("No messages from publishers to display");
    }else{
      while(!messagesQueue.isEmpty()){
        Message message = messagesQueue.remove();

        if(message.getTopic().equalsIgnoreCase(topic)){

          Set<Subscriber> subscribersOfTopic = subscribersTopicMap.get(topic);

          for(Subscriber _subscriber : subscribersOfTopic){
            if(_subscriber.equals(subscriber)){
              //add broadcasted message to subscriber message queue
              List<Message> subscriberMessages = subscriber.getSubscriberMessages();
              subscriberMessages.add(message);
              subscriber.setSubscriberMessages(subscriberMessages);
            }
          }
        }
      }
    }
  }


}
