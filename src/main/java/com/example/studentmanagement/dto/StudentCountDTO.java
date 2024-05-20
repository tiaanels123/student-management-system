package com.example.studentmanagement.dto;

/**
 * Data Transfer Object for Student with course count.
 */
public class StudentCountDTO {

    /**
     * The ID of the student.
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
     * The number of courses the student is enrolled in.
     */
    private int courseCount;

    /**
     * Gets the ID of the student.
     *
     * @return the ID of the student
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the student.
     *
     * @param id the ID of the student
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the student.
     *
     * @return the name of the student
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the student.
     *
     * @param name the name of the student
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email of the student.
     *
     * @return the email of the student
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the student.
     *
     * @param email the email of the student
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the number of courses the student is enrolled in.
     *
     * @return the number of courses the student is enrolled in
     */
    public int getCourseCount() {
        return courseCount;
    }

    /**
     * Sets the number of courses the student is enrolled in.
     *
     * @param courseCount the number of courses the student is enrolled in
     */
    public void setCourseCount(int courseCount) {
        this.courseCount = courseCount;
    }
}
