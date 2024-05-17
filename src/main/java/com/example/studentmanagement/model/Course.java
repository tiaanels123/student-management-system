package com.example.studentmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a course.
 */
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Course {

    /**
     * The unique ID of the course.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

    // Getters and Setters

    /**
     * Gets the ID of the course.
     * @return the course ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the course.
     * @param id the course ID.
     */
    public void setId(final Long id) {
        this.id = id;
    }

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

    /**
     * Gets the list of students enrolled in the course.
     * @return the list of students.
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Sets the list of students enrolled in the course.
     * @param students the list of students.
     */
    public void setStudents(final List<Student> students) {
        this.students = students;
    }
}