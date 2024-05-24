package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Course;
import com.example.studentmanagement.model.User;
import com.example.studentmanagement.repository.CourseRepository;
import com.example.studentmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course updatedCourse) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            course.setName(updatedCourse.getName());
            course.setDescription(updatedCourse.getDescription());
            return courseRepository.save(course);
        } else {
            throw new RuntimeException("Course not found");
        }
    }

    @Transactional
    public void deleteCourse(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            if (course.getUsers() != null) {
                for (User user : course.getUsers()) {
                    user.getCourses().remove(course);
                    userRepository.save(user);
                }
            }
            courseRepository.delete(course);
        } else {
            throw new RuntimeException("Course not found");
        }
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}