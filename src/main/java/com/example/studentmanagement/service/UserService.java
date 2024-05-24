package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.CourseCountDTO;
import com.example.studentmanagement.dto.UserDTO;
import com.example.studentmanagement.model.Course;
import com.example.studentmanagement.model.Role;
import com.example.studentmanagement.model.User;
import com.example.studentmanagement.repository.CourseRepository;
import com.example.studentmanagement.repository.RoleRepository;
import com.example.studentmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO register(UserDTO userDTO, String roleName) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            throw new RuntimeException("Role not found: " + roleName);
        }
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        Set<Course> courses = new HashSet<>();
        for (CourseCountDTO courseDTO : userDTO.getCourses()) {
            Course course = courseRepository.findByName(courseDTO.getName());
            if (course == null) {
                course = new Course();
                course.setName(courseDTO.getName());
                course.setDescription(courseDTO.getDescription());
                courseRepository.save(course);
            }
            course.getUsers().add(user);
            courses.add(course);
        }
        user.setCourses(new ArrayList<>(courses));

        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("Logged in user not found"));

        if (!currentUser.getRoles().stream().anyMatch(role -> role.getName().equals("ADMIN")) && !currentUser.getId().equals(id)) {
            throw new RuntimeException("You are not authorized to update this user");
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        if (userDTO.getCourses() != null) {
            Set<Course> courses = new HashSet<>();
            for (CourseCountDTO courseDTO : userDTO.getCourses()) {
                Course course = courseRepository.findByName(courseDTO.getName());
                if (course == null) {
                    course = new Course();
                    course.setName(courseDTO.getName());
                    course.setDescription(courseDTO.getDescription());
                    courseRepository.save(course);
                }
                course.getUsers().add(user);
                courses.add(course);
            }
            user.setCourses(new ArrayList<>(courses));
        }

        return convertToDTO(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO addCoursesToUser(Long userId, List<CourseCountDTO> courseDTOs) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Set<Course> courses = new HashSet<>();
        for (CourseCountDTO courseDTO : courseDTOs) {
            Course course = courseRepository.findByName(courseDTO.getName());
            if (course == null) {
                course = new Course();
                course.setName(courseDTO.getName());
                course.setDescription(courseDTO.getDescription());
                courseRepository.save(course);
            }
            course.getUsers().add(user);
            courses.add(course);
        }
        user.setCourses(new ArrayList<>(courses));
        return convertToDTO(userRepository.save(user));
    }

    public List<UserDTO> getUsersByRole(String roleName) {
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            throw new RuntimeException("Role not found: " + roleName);
        }
        return userRepository.findAll().stream()
                .filter(user -> user.getRoles().contains(role))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDTO(user);
    }

    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDTO(user);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(user.getRoles() != null ? user.getRoles().stream().map(Role::getName).collect(Collectors.toList()) : new ArrayList<>());
        userDTO.setCourses(user.getCourses() != null ? user.getCourses().stream().map(course -> {
            CourseCountDTO courseDTO = new CourseCountDTO();
            courseDTO.setId(course.getId());
            courseDTO.setName(course.getName());
            courseDTO.setDescription(course.getDescription());
            courseDTO.setStudentCount(course.getUsers() != null ? course.getUsers().size() : 0);
            return courseDTO;
        }).collect(Collectors.toList()) : new ArrayList<>());
        return userDTO;
    }

    @Transactional
    public void enrollUserInCourse(Long userId, Long courseId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if (user.getCourses().contains(course)) {
            throw new RuntimeException("User is already enrolled in this course");
        }

        user.getCourses().add(course);
        course.getUsers().add(user);

        userRepository.save(user);
        courseRepository.save(course);
    }

    public List<Course> getUserCourses(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getCourses();
    }
}