package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.CourseCountDTO;
import com.example.studentmanagement.dto.CourseDTO;
import com.example.studentmanagement.dto.UserCountDTO;
import com.example.studentmanagement.dto.UserDTO;
import com.example.studentmanagement.model.Course;
import com.example.studentmanagement.service.UserService;
import com.example.studentmanagement.security.AuthenticationRequest;
import com.example.studentmanagement.security.AuthenticationResponse;
import com.example.studentmanagement.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody UserDTO userDTO, @RequestParam String role) {
        return userService.register(userDTO, role);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return users;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        UserDTO userDTO = userService.getUserByEmail(currentUserName);
        if (userDTO != null) {
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.getUserById(id);
        if (userDTO != null) {
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or principal.id == #id")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/courses")
    @PreAuthorize("hasRole('USER') or principal.id == #id")
    public ResponseEntity<UserDTO> addCoursesToUser(@PathVariable Long id, @RequestBody List<CourseCountDTO> courses) {
        UserDTO updatedUser = userService.addCoursesToUser(id, courses);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/role/{roleName}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> getUsersByRole(@PathVariable String roleName) {
        List<UserDTO> users = userService.getUsersByRole(roleName);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/{userId}/courses/{courseId}")
    @PreAuthorize("hasRole('USER') or principal.id == #userId")
    public ResponseEntity<Void> enrollUserInCourse(@PathVariable Long userId, @PathVariable Long courseId) {
        userService.enrollUserInCourse(userId, courseId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/courses")
    @PreAuthorize("hasRole('USER') or principal.id == #userId")
    public ResponseEntity<List<CourseDTO>> getUserCourses(@PathVariable Long userId) {
        List<Course> courses = userService.getUserCourses(userId);
        List<CourseDTO> courseDTOs = courses.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(courseDTOs);
    }

    private CourseDTO convertToDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setDescription(course.getDescription());
        if (course.getUsers() != null) {
            courseDTO.setStudents(course.getUsers().stream()
                    .map(user -> {
                        UserCountDTO userDTO = new UserCountDTO();
                        userDTO.setId(user.getId());
                        userDTO.setName(user.getName());
                        userDTO.setEmail(user.getEmail());
                        userDTO.setCourseCount(user.getCourses() != null ? user.getCourses().size() : 0);
                        return userDTO;
                    }).collect(Collectors.toList()));
        }
        return courseDTO;
    }
}