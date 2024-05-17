package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Student entities.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
