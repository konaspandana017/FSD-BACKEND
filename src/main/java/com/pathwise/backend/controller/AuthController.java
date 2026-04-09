package com.pathwise.backend.controller;

import com.pathwise.backend.model.User;
import com.pathwise.backend.model.Mentor;
import com.pathwise.backend.repository.UserRepository;
import com.pathwise.backend.repository.MentorRepository;
import com.pathwise.backend.service.JwtUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository repo;
    private final MentorRepository mentorRepo;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository repo, MentorRepository mentorRepo, JwtUtil jwtUtil) {
        this.repo = repo;
        this.mentorRepo = mentorRepo;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");
        String role = body.getOrDefault("role", "STUDENT");
        String name = body.get("name");

        if (repo.findByEmail(email).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error", "User already exists"));
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password); // In a real app, hash password!
        user.setRole(role);
        user.setName(name);

        repo.save(user);

        if ("MENTOR".equals(role)) {
            Mentor mentor = new Mentor();
            mentor.setName(name);
            mentor.setExpertise(body.getOrDefault("expertise", "General Mentor"));
            mentorRepo.save(mentor);
        }

        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        User user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(email);
        return ResponseEntity.ok(Map.of(
            "token", token,
            "role", user.getRole() != null ? user.getRole() : "STUDENT",
            "name", user.getName() != null ? user.getName() : email
        ));
    }
}