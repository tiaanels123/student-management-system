package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Course;
import com.example.studentmanagement.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for managing courses.
 */
@Service
public class CourseService {

    /**
     * Repository for accessing course data.
     */
    @Autowired
    private CourseRepository courseRepository;

    /**
     * Retrieves all courses.
     * @return a list of all courses.
     */
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}