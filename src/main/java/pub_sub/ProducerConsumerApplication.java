package pub_sub;


import pub_sub.action.PublisherSubscriberService;
import pub_sub.constants.FilterCondition;
import pub_sub.dto.Message;
import pub_sub.messageFilter.RegexFilter;
import pub_sub.publisher.Publisher;
import pub_sub.publisher.PublisherImpl;
import pub_sub.subscriber.Subscriber;
import pub_sub.subscriber.SubscriberImpl;

public class ProducerConsumerApplication {

	public static void main(String[] args) throws Exception {

		PublisherSubscriberService pubSubService = new PublisherSubscriberService();
		Publisher publisher = new PublisherImpl();
		Subscriber subscriber1 = new SubscriberImpl("Sub1");
		Subscriber subscriber2 = new SubscriberImpl("Sub2");
		Subscriber subscriber3 = new SubscriberImpl("Sub3");

		//Declare Messages and Publish Messages to PublisherSubscriberService
    Message msg1 = new Message("topic1","aEvent");
    Message msg2 = new Message("topic1", "bEvent");
    Message msg3 = new Message("topic1","cEvent");

    publisher.publish(msg1,pubSubService);
    publisher.publish(msg2,pubSubService);
    publisher.publish(msg3,pubSubService);

    subscriber1.addSubscriber("topic1", pubSubService);
    subscriber1.addMessageFilter(new RegexFilter("^a.*",FilterCondition.ALLOW_IF_MATCH));
    subscriber2.addSubscriber("topic1", pubSubService);
    subscriber2.addMessageFilter(new RegexFilter("^b.*", FilterCondition.DENY_IF_MATCH));
    subscriber3.addSubscriber("topic1", pubSubService);
    subscriber3.addMessageFilter(new RegexFilter(".*ent",FilterCondition.ALLOW_IF_MATCH));

    //Broadcast message to all subscribers. After broadcast, messageQueue will be empty in PubSubService
		pubSubService.broadcast();

		subscriber1.printMessages();
		subscriber2.printMessages();
		subscriber3.printMessages();
	}
}
