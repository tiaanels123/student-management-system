package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.CourseDTO;
import com.example.studentmanagement.model.Course;
import com.example.studentmanagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
     *
     * @return a list of all courses.
     */
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    /**
     * Adds a new course.
     *
     * @param courseDTO the course data transfer object.
     * @return the added course.
     */
    @PostMapping
    public Course addCourse(@RequestBody CourseDTO courseDTO) {
        Course course = new Course();
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        return courseService.addCourse(course);
    }

    /**
     * Updates an existing course.
     *
     * @param id         the ID of the course to update.
     * @param courseDTO the course data transfer object.
     * @return the updated course.
     */
    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable("id") Long id, @RequestBody CourseDTO courseDTO) {
        Course course = new Course();
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        return courseService.updateCourse(id, course);
    }

    /**
     * Deletes a course.
     *
     * @param id the ID of the course to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteCourse(id);
    }
}
