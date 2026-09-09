package org.basith.notificationservice;

public class RegistrationEvent {

    private Long studentId;
    private String courseName;

    public RegistrationEvent() {
    }

    public RegistrationEvent(Long studentId, String courseName) {
        this.studentId = studentId;
        this.courseName = courseName;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getCourseName() {
        return courseName;
    }
}