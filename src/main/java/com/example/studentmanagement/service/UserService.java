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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        System.out.println("Fetching all users from the repository");
        List<User> users = userRepository.findAll();
        System.out.println("Users fetched: " + users);
        List<UserDTO> userDTOs = users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        System.out.println("User DTOs created: " + userDTOs);
        return userDTOs;
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

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

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        // Exclude the password field from the DTO
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
}
