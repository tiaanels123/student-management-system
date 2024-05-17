package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Course entities.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    /**
     * Finds a course by its name.
     *
     * @param name the name of the course.
     * @return the course with the specified name, or null if not found.
     */
    Course findByName(String name);
}
