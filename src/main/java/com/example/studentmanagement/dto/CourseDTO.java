package com.example.studentmanagement.dto;

/**
 * Data transfer object for courses.
 */
public class CourseDTO {

    /**
     * The name of the course.
     */
    private String name;

    /**
     * The description of the course.
     */
    private String description;

    // Getters and Setters

    /**
     * Gets the name of the course.
     * @return the course name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the course.
     * @param name the course name.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the description of the course.
     * @return the course description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the course.
     * @param description the course description.
     */
    public void setDescription(final String description) {
        this.description = description;
    }
}