package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Course;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.CourseRepository;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing courses.
 */
@Service
public class CourseService {

    /**
     * Repository for managing courses.
     */
    @Autowired
    private CourseRepository courseRepository;

    /**
     * Repository for managing students.
     */
    @Autowired
    private StudentRepository studentRepository;

    /**
     * Adds a new course.
     *
     * @param course the course to add.
     * @return the added course.
     */
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    /**
     * Updates an existing course.
     *
     * @param id the ID of the course to update.
     * @param updatedCourse the updated course information.
     * @return the updated course.
     */
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

    /**
     * Deletes a course.
     *
     * @param id the ID of the course to delete.
     */
    @Transactional
    public void deleteCourse(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            for (Student student : course.getStudents()) {
                student.getCourses().remove(course);
                studentRepository.save(student);
            }
            courseRepository.delete(course);
        } else {
            throw new RuntimeException("Course not found");
        }
    }

    /**
     * Retrieves all courses.
     *
     * @return a list of all courses.
     */
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
