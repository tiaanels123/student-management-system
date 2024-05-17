package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.StudentDTO;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for handling student-related requests.
 */
@RestController
@RequestMapping("/students")
public class StudentController {

    /**
     * Service for managing students.
     */
    @Autowired
    private StudentService studentService;

    /**
     * Adds a new student.
     * @param studentDTO the student data transfer object.
     * @return the added student.
     */
    @PostMapping
    public Student addStudent(final @RequestBody StudentDTO studentDTO) {
        return studentService.addStudent(studentDTO);
    }

    /**
     * Retrieves all students.
     * @return a list of all students.
     */
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Other methods...
}