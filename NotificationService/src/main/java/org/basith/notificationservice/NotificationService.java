package org.basith.notificationservice;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

  public Notification sendNotification(Notification notification) {

    System.out.println(
      "Notification sent to student " +
        notification.getStudentId() +
        ": " +
        notification.getMessage()
    );

    return notification;
  }
}