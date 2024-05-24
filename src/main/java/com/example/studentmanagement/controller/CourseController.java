package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.CourseDTO;
import com.example.studentmanagement.dto.UserCountDTO;
import com.example.studentmanagement.model.Course;
import com.example.studentmanagement.service.CourseService;
import com.example.studentmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for handling course-related requests.
 */
@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO courseDTO) {
        Course course = new Course();
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        Course savedCourse = courseService.addCourse(course);
        return ResponseEntity.ok(convertToDTO(savedCourse));
    }

    @GetMapping
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return courses.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable("id") Long id, @RequestBody CourseDTO courseDTO) {
        Course course = new Course();
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        Course updatedCourse = courseService.updateCourse(id, course);
        return ResponseEntity.ok(convertToDTO(updatedCourse));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    private CourseDTO convertToDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setDescription(course.getDescription());
        if (course.getUsers() != null) {
            courseDTO.setStudents(course.getUsers().stream()
                    .map(user -> {
                        UserCountDTO userDTO = new UserCountDTO();
                        userDTO.setId(user.getId());
                        userDTO.setName(user.getName());
                        userDTO.setEmail(user.getEmail());
                        userDTO.setCourseCount(user.getCourses() != null ? user.getCourses().size() : 0);
                        return userDTO;
                    }).collect(Collectors.toList()));
        }
        return courseDTO;
    }
}