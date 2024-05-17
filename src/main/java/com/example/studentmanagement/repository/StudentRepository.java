package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for students.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
}