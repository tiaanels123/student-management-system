package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.CourseDTO;
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

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Student addStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());

        Set<Course> courses = new HashSet<>();
        for (CourseDTO courseDTO : studentDTO.getCourses()) {
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

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Other methods...
}
