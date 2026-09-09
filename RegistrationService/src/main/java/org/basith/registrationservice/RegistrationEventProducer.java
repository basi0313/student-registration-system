package org.basith.registrationservice;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class RegistrationEventProducer {

  private static final String TOPIC = "student-registration";

  private final KafkaTemplate<String, RegistrationEvent> kafkaTemplate;

  public RegistrationEventProducer(
    KafkaTemplate<String, RegistrationEvent> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendRegistrationEvent(RegistrationEvent event) {
    kafkaTemplate.send(TOPIC, event);
  }
}