package pub_sub.dto;


import java.time.LocalDateTime;
import java.util.UUID;

public class Message {

  private UUID eventId;
  private String eventName;
  private String topic;
  private LocalDateTime eventTime;
  private String eventDescription;

  public Message(String topic, String eventDescription) {
    this.topic = topic;
    this.eventDescription = eventDescription;
  }

  public Message(UUID eventId, String eventName, String topic, LocalDateTime eventTime,
      String eventDescription) {
    this.eventId = eventId;
    this.eventName = eventName;
    this.topic = topic;
    this.eventTime = eventTime;
    this.eventDescription = eventDescription;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }

  public void setEventTime(LocalDateTime eventTime) {
    this.eventTime = eventTime;
  }

  public void setEventDescription(String eventDescription) {
    this.eventDescription = eventDescription;
  }

  public void setEventId(UUID eventId) {
    this.eventId = eventId;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getEventName() {
    return eventName;
  }

  public LocalDateTime getEventTime() {
    return eventTime;
  }

  public String getEventDescription() {
    return eventDescription;
  }

  public UUID getEventId() {
    return eventId;
  }

  public String getTopic() {
    return topic;
  }

  @Override
  public String toString() {
    return "Event [id=" + eventId + ", name=" + eventName + ",topicName="+ topic +", description=" + eventDescription + "]";
  }
}
