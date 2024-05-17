package com.example.studentmanagement.dto;

import java.util.List;

/**
 * Data Transfer Object for Course.
 */
public class CourseDTO {

    /** The unique ID of the course. */
    private Long id;

    /** The name of the course. */
    private String name;

    /** The description of the course. */
    private String description;

    /** The list of student IDs enrolled in the course. */
    private List<Long> studentIds;

    /**
     * Gets the ID of the course.
     *
     * @return the course ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the course.
     *
     * @param id the new course ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the course.
     *
     * @return the course name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the course.
     *
     * @param name the new course name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the course.
     *
     * @return the course description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the course.
     *
     * @param description the new course description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the list of student IDs enrolled in the course.
     *
     * @return the list of student IDs
     */
    public List<Long> getStudentIds() {
        return studentIds;
    }

    /**
     * Sets the list of student IDs enrolled in the course.
     *
     * @param studentIds the new list of student IDs
     */
    public void setStudentIds(List<Long> studentIds) {
        this.studentIds = studentIds;
    }
}
