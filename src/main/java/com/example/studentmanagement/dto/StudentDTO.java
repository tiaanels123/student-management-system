package com.example.studentmanagement.dto;

import java.util.List;

/**
 * Data Transfer Object for Student.
 */
public class StudentDTO {

    private Long id;
    private String name;
    private String email;
    private List<CourseCountDTO> courses;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CourseCountDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseCountDTO> courses) {
        this.courses = courses;
    }
}
