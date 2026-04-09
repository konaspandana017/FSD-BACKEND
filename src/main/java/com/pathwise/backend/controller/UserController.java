package com.pathwise.backend.controller;

import com.pathwise.backend.model.User;
import com.pathwise.backend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
    
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/students")
    public List<User> getStudents() {
        return repository.findAll().stream()
                .filter(u -> "STUDENT".equals(u.getRole()))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
