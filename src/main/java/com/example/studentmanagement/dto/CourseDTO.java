package com.example.studentmanagement.dto;

import java.util.List;

/**
 * Data Transfer Object for Course.
 */
public class CourseDTO {

    /**
     * The ID of the course.
     */
    private Long id;

    /**
     * The name of the course.
     */
    private String name;

    /**
     * The description of the course.
     */
    private String description;

    /**
     * The list of students enrolled in the course.
     */
    private List<UserCountDTO> students;

    /**
     * Gets the ID of the course.
     *
     * @return the ID of the course
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the course.
     *
     * @param id the ID of the course
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the course.
     *
     * @return the name of the course
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the course.
     *
     * @param name the name of the course
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the course.
     *
     * @return the description of the course
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the course.
     *
     * @param description the description of the course
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the list of students enrolled in the course.
     *
     * @return the list of students enrolled in the course
     */
    public List<UserCountDTO> getStudents() {
        return students;
    }

    /**
     * Sets the list of students enrolled in the course.
     *
     * @param students the list of students enrolled in the course
     */
    public void setStudents(List<UserCountDTO> students) {
        this.students = students;
    }
}
