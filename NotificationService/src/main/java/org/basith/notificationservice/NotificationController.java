package org.basith.notificationservice;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

  private final NotificationService notificationService;

  public NotificationController(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @PostMapping
  public Notification sendNotification(
    @RequestBody Notification notification) {

    return notificationService.sendNotification(notification);
  }
}