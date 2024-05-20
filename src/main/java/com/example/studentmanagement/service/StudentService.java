package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.CourseCountDTO;
import com.example.studentmanagement.dto.StudentDTO;
import com.example.studentmanagement.model.Course;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.CourseRepository;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Service for managing students.
 */
@Service
public class StudentService {

    /**
     * Repository for managing student data.
     */
    @Autowired
    private StudentRepository studentRepository;

    /**
     * Repository for managing course data.
     */
    @Autowired
    private CourseRepository courseRepository;

    /**
     * Adds a new student.
     *
     * @param studentDTO the student data transfer object.
     * @return the added student.
     */
    public Student addStudent(final StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());

        Set<Course> courses = new HashSet<>();
        for (CourseCountDTO courseDTO : studentDTO.getCourses()) {
            Course course = courseRepository.findByName(courseDTO.getName());
            if (course == null) {
                course = new Course();
                course.setName(courseDTO.getName());
                course.setDescription(courseDTO.getDescription());
                courseRepository.save(course);
            }
            course.getStudents().add(student);
            courses.add(course);
        }
        student.setCourses(new ArrayList<>(courses));
        return studentRepository.save(student);
    }

    /**
     * Retrieves all students.
     *
     * @return a list of all students.
     */
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Updates an existing student.
     *
     * @param id the ID of the student to update.
     * @param studentDTO the student data transfer object.
     * @return the updated student.
     */
    public Student updateStudent(final Long id, final StudentDTO studentDTO) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());

        // Only update courses if they are included in the DTO
        if (studentDTO.getCourses() != null) {
            Set<Course> courses = new HashSet<>();
            for (CourseCountDTO courseDTO : studentDTO.getCourses()) {
                Course course = courseRepository.findByName(courseDTO.getName());
                if (course == null) {
                    course = new Course();
                    course.setName(courseDTO.getName());
                    course.setDescription(courseDTO.getDescription());
                    courseRepository.save(course);
                }
                course.getStudents().add(student);
                courses.add(course);
            }
            student.setCourses(new ArrayList<>(courses));
        } else {
            // Retain the existing courses if not provided in the update request
            student.setCourses(student.getCourses());
        }

        return studentRepository.save(student);
    }

    /**
     * Deletes a student.
     *
     * @param id the ID of the student to delete.
     */
    public void deleteStudent(final Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        studentRepository.delete(student);
    }
}
