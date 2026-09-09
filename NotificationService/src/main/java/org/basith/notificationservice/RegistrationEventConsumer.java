package org.basith.notificationservice;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class RegistrationEventConsumer {

  @KafkaListener(
    topics = "student-registration",
    groupId = "notification-group"
  )
  public void consume(RegistrationEvent event) {

    System.out.println(
      "Notification received for student: "
        + event.getStudentId()
        + ", course: "
        + event.getCourseName()
    );
  }
}