package com.example.studentmanagement.dto;

import java.util.List;

/**
 * Data Transfer Object for Student.
 */
public class StudentDTO {

    /**
     * The unique ID of the student.
     */
    private Long id;

    /**
     * The name of the student.
     */
    private String name;

    /**
     * The email of the student.
     */
    private String email;

    /**
     * The list of courses the student is enrolled in.
     */
    private List<CourseDTO> courses;

    /**
     * Gets the ID of the student.
     *
     * @return the student ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the student.
     *
     * @param id the student ID.
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the student.
     *
     * @return the student name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the student.
     *
     * @param name the student name.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the email of the student.
     *
     * @return the student email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the student.
     *
     * @param email the student email.
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Gets the list of courses the student is enrolled in.
     *
     * @return the list of courses.
     */
    public List<CourseDTO> getCourses() {
        return courses;
    }

    /**
     * Sets the list of courses the student is enrolled in.
     *
     * @param courses the list of courses.
     */
    public void setCourses(final List<CourseDTO> courses) {
        this.courses = courses;
    }
}
