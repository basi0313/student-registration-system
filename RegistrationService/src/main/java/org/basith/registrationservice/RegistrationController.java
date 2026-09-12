package org.basith.registrationservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

  private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

  private final RegistrationService registrationService;

  public RegistrationController(RegistrationService registrationService) {
    this.registrationService = registrationService;
  }

  @PostMapping
  public Registration register(@RequestBody Registration registration) {
    log.info("Received registration request");
    // generate random number between 0 to 10
    int randomNumber = (int) (Math.random() * 11);
    if (randomNumber < 5) {
      log.warn("Random failure occurred during registration");
      throw new RuntimeException("Random failure occurred during registration");
    }
    Registration result = registrationService.register(registration);
    log.info("Registration completed successfullys");
    return result;
  }
}