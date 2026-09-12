package org.basith.notificationservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class RegistrationEventConsumer {

  private static final Logger log = LoggerFactory.getLogger(RegistrationEventConsumer.class);

  @KafkaListener(
    topics = "student-registration",
    groupId = "notification-group"
  )
  public void consume(RegistrationEvent event) {

    log.info(
      "Notification received for student: {}, course: {}",
      event.getStudentId(),
      event.getCourseName()
    );
  }
}