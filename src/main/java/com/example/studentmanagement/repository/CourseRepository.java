package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for courses.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    /**
     * Finds a course by its name.
     *
     * @param name the name of the course
     * @return the course with the given name
     */
    Course findByName(String name);
}