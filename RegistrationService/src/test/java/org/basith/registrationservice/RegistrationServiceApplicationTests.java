package org.basith.registrationservice;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JacksonJsonSerializer;
import tools.jackson.databind.json.JsonMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RegistrationServiceApplicationTests {

  @Autowired
  private KafkaTemplate<String, RegistrationEvent> kafkaTemplate;

  @Test
  void contextLoads() {
  }

  @Test
  void registrationEventsUseJsonSerialization() {
    assertEquals(JacksonJsonSerializer.class,
      kafkaTemplate.getProducerFactory().getConfigurationProperties()
        .get(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG));

    try (var serializer = new JacksonJsonSerializer<RegistrationEvent>()) {
      byte[] payload = serializer.serialize("student-registration",
        new RegistrationEvent(1L, "Java"));
      var json = JsonMapper.builder().build().readTree(payload);

      assertEquals(1L, json.get("studentId").asLong());
      assertEquals("Java", json.get("courseName").asString());
    }
  }

}
