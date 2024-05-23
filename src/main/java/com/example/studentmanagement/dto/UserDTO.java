package com.example.studentmanagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL) // This will exclude fields that are null
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Ensure password is included only when not null
    private String password;
    private List<String> roles;
    private List<CourseCountDTO> courses;

    // Getters and setters

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<CourseCountDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseCountDTO> courses) {
        this.courses = courses;
    }
}
