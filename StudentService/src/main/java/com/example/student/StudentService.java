package com.example.student;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {

  private final Map<Long, Student> students = new HashMap<>();
  private long id = 1;

  public Student createStudent(Student student) {
    student.setId(id++);
    students.put(student.getId(), student);
    return student;
  }

  public Student getStudentById(Long id) {
    return students.get(id);
  }
}