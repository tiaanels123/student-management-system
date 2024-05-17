package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Course;
import com.example.studentmanagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for handling course-related requests.
 */
@RestController
@RequestMapping("/courses")
public class CourseController {

    /**
     * Service for managing courses.
     */
    @Autowired
    private CourseService courseService;

    /**
     * Retrieves all courses.
     * @return a list of all courses.
     */
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }
}