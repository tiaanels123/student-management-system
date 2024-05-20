package com.example.studentmanagement.dto;

/**
 * Data Transfer Object for Course with student count.
 */
public class CourseCountDTO {

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
     * The number of students enrolled in the course.
     */
    private int studentCount;

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
     * Gets the number of students enrolled in the course.
     *
     * @return the number of students enrolled in the course
     */
    public int getStudentCount() {
        return studentCount;
    }

    /**
     * Sets the number of students enrolled in the course.
     *
     * @param studentCount the number of students enrolled in the course
     */
    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }
}
