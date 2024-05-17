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
 * Entity representing a student.
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Student {

    /**
     * The unique ID of the student.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToMany
    private List<Course> courses = new ArrayList<>();

    /**
     * Gets the ID of the student.
     * @return the student ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the student.
     * @param id the student ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the student.
     * @return the student name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the student.
     * @param name the student name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email of the student.
     * @return the student email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the student.
     * @param email the student email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the list of courses the student is enrolled in.
     * @return the list of courses.
     */
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * Sets the list of courses the student is enrolled in.
     * @param courses the list of courses.
     */
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
