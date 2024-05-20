package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.CourseCountDTO;
import com.example.studentmanagement.dto.StudentDTO;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.StudentService;
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
import java.util.stream.Collectors;

/**
 * Controller for managing students.
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
     *
     * @param studentDTO the student data transfer object
     * @return the added student
     */
    @PostMapping
    public StudentDTO addStudent(@RequestBody StudentDTO studentDTO) {
        Student student = studentService.addStudent(studentDTO);
        return convertToDTO(student);
    }

    /**
     * Retrieves all students.
     *
     * @return a list of all students
     */
    @GetMapping
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return students.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    /**
     * Updates an existing student.
     *
     * @param id the ID of the student to update
     * @param studentDTO the student data transfer object
     * @return the updated student
     */
    @PutMapping("/{id}")
    public StudentDTO updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        Student student = studentService.updateStudent(id, studentDTO);
        return convertToDTO(student);
    }

    /**
     * Deletes a student.
     *
     * @param id the ID of the student to delete
     */
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    /**
     * Converts a Student entity to a StudentDTO.
     *
     * @param student the student entity
     * @return the student data transfer object
     */
    private StudentDTO convertToDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setCourses(student.getCourses().stream()
                .map(course -> {
                    CourseCountDTO courseDTO = new CourseCountDTO();
                    courseDTO.setId(course.getId());
                    courseDTO.setName(course.getName());
                    courseDTO.setDescription(course.getDescription());
                    courseDTO.setStudentCount(course.getStudents().size());
                    return courseDTO;
                }).collect(Collectors.toList()));
        return studentDTO;
    }
}
