package pub_sub;


import pub_sub.action.PublisherSubscriberService;
import pub_sub.dto.Message;
import pub_sub.publisher.Publisher;
import pub_sub.publisher.PublisherImpl;
import pub_sub.subscriber.Subscriber;
import pub_sub.subscriber.SubscriberImpl;

public class ProducerConsumerApplication {

  public static void main(String[] args) {

    //Instantiate publishers, subscribers and PublisherSubscriberService 
    Publisher javaPublisher = new PublisherImpl();
    Publisher pythonPublisher = new PublisherImpl();

    Subscriber javaSubscriber = new SubscriberImpl();
    Subscriber allLanguagesSubscriber = new SubscriberImpl();
    Subscriber pythonSubscriber = new SubscriberImpl();

    PublisherSubscriberService pubSubService = new PublisherSubscriberService();

    //Declare Messages and Publish Messages to PublisherSubscriberService
    Message javaMsg1 = new Message("Java", "Core Java Concepts");
    Message javaMsg2 = new Message("Java", "Spring MVC : Dependency Injection and AOP");
    Message javaMsg3 = new Message("Java", "JPA & Hibernate");

    javaPublisher.publish(javaMsg1, pubSubService);
    javaPublisher.publish(javaMsg2, pubSubService);
    javaPublisher.publish(javaMsg3, pubSubService);

    Message pythonMsg1 = new Message("Python", "Easy and Powerful programming language");
    Message pythonMsg2 = new Message("Python", "Advanced Python message");

    pythonPublisher.publish(pythonMsg1, pubSubService);
    pythonPublisher.publish(pythonMsg2, pubSubService);

    //Declare Subscribers 
    javaSubscriber.addSubscriber("Java",pubSubService);		//Java subscriber only subscribes to Java topics
    pythonSubscriber.addSubscriber("Python",pubSubService);   //Python subscriber only subscribes to Python topics
    allLanguagesSubscriber.addSubscriber("Java", pubSubService);	//all subscriber, subscribes to both Java and Python
    allLanguagesSubscriber.addSubscriber("Python", pubSubService);

    //Trying unSubscribing a subscriber
    //pythonSubscriber.unSubscribe("Python", pubSubService);

    //Broadcast message to all subscribers. After broadcast, messageQueue will be empty in PublisherSubscriberService
    pubSubService.broadcast();

    //Print messages of each subscriber to see which messages they got
    System.out.println("Messages of Java Subscriber are: ");
    javaSubscriber.printMessages();

    System.out.println("\nMessages of Python Subscriber are: ");
    pythonSubscriber.printMessages();

    System.out.println("\nMessages of All Languages Subscriber are: ");
    allLanguagesSubscriber.printMessages();

    //After broadcast the messagesQueue will be empty, so publishing new messages to server
    System.out.println("\nPublishing 2 more Java Messages...");
    Message javaMsg4 = new Message("Java", "JSP and Servlets");
    Message javaMsg5 = new Message("Java", "Struts framework");

    javaPublisher.publish(javaMsg4, pubSubService);
    javaPublisher.publish(javaMsg5, pubSubService);

    javaSubscriber.getMessagesForSubscriberOfTopic("Java", pubSubService);
    System.out.println("\nMessages of Java Subscriber now are: ");
    javaSubscriber.printMessages();
  }
}
