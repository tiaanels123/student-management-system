package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.CourseDTO;
import com.example.studentmanagement.dto.StudentCountDTO;
import com.example.studentmanagement.model.Course;
import com.example.studentmanagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return courses.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @PostMapping
    public CourseDTO addCourse(@RequestBody CourseDTO courseDTO) {
        Course course = new Course();
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        Course savedCourse = courseService.addCourse(course);
        return convertToDTO(savedCourse);
    }

    @PutMapping("/{id}")
    public CourseDTO updateCourse(@PathVariable("id") Long id, @RequestBody CourseDTO courseDTO) {
        Course course = new Course();
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        Course updatedCourse = courseService.updateCourse(id, course);
        return convertToDTO(updatedCourse);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteCourse(id);
    }

    private CourseDTO convertToDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setDescription(course.getDescription());
        courseDTO.setStudents(course.getStudents().stream()
                .map(student -> {
                    StudentCountDTO studentDTO = new StudentCountDTO();
                    studentDTO.setId(student.getId());
                    studentDTO.setName(student.getName());
                    studentDTO.setEmail(student.getEmail());
                    studentDTO.setCourseCount(student.getCourses().size());
                    return studentDTO;
                }).collect(Collectors.toList()));
        return courseDTO;
    }
}
