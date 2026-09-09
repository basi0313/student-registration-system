package org.basith.registrationservice;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationService {

  private final List<Registration> registrations = new ArrayList<>();

  private final RestClient restClient;
  private final RegistrationEventProducer eventProducer;

  public RegistrationService(RestClient restClient, RegistrationEventProducer eventProducer) {
    this.restClient = restClient;
    this.eventProducer = eventProducer;
  }

  public Registration register(Registration registration) {

    StudentResponse student = restClient.get()
      .uri("http://localhost:8081/students/{id}", registration.getStudentId())
      .retrieve()
      .body(StudentResponse.class);

    registrations.add(registration);

    RegistrationEvent event =
      new RegistrationEvent(
        registration.getStudentId(),
        registration.getCourseName()
      );
    eventProducer.sendRegistrationEvent(event);

    return registration;
  }
}