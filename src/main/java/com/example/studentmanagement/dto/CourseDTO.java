package com.example.studentmanagement.dto;

import java.util.List;

/**
 * Data Transfer Object for Course.
 */
public class CourseDTO {

    private Long id;
    private String name;
    private String description;
    private List<StudentCountDTO> students;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<StudentCountDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentCountDTO> students) {
        this.students = students;
    }
}