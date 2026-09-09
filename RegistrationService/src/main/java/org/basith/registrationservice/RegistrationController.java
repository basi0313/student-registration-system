package org.basith.registrationservice;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

  private final RegistrationService registrationService;

  public RegistrationController(RegistrationService registrationService) {
    this.registrationService = registrationService;
  }

  @PostMapping
  public Registration register(@RequestBody Registration registration) {
    return registrationService.register(registration);
  }
}