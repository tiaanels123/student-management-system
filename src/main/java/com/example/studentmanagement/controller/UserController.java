package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.CourseCountDTO;
import com.example.studentmanagement.dto.UserDTO;
import com.example.studentmanagement.security.AuthenticationRequest;
import com.example.studentmanagement.security.AuthenticationResponse;
import com.example.studentmanagement.service.UserService;
import com.example.studentmanagement.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<UserDTO> getAllUsers() {
        System.out.println("GET /users called");
        List<UserDTO> users = userService.getAllUsers();
        System.out.println("Users returned: " + users);
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

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}/courses")
    public UserDTO addCoursesToUser(@PathVariable Long id, @RequestBody List<CourseCountDTO> courses) {
        return userService.addCoursesToUser(id, courses);
    }

    @GetMapping("/role/{roleName}")
    public List<UserDTO> getUsersByRole(@PathVariable String roleName) {
        return userService.getUsersByRole(roleName);
    }
}
