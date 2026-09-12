package com.example.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

  private static final Logger log = LoggerFactory.getLogger(StudentController.class);

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @PostMapping
  public Student createStudent(@RequestBody Student student) {
    log.info("Received request to create a student");
    return studentService.createStudent(student);
  }

  @GetMapping("/{id}")
  public Student getStudent(@PathVariable Long id) {
    log.info("Received request to get student with id {}", id);
    return studentService.getStudentById(id);
  }
}