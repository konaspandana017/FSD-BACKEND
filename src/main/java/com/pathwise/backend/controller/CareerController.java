package com.pathwise.backend.controller;

import com.pathwise.backend.model.Career;
import com.pathwise.backend.repository.CareerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/careers")
@CrossOrigin(origins = "*")
public class CareerController {

    private final CareerRepository repository;

    public CareerController(CareerRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Career> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Career create(@RequestBody Career career) {
        return repository.save(career);
    }

    @DeleteMapping("/{id}")
    public void deleteCareer(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PatchMapping("/{id}")
    public Career updateCareer(@PathVariable Long id, @RequestBody java.util.Map<String, String> body) {
        Career c = repository.findById(id).orElseThrow();
        if(body.containsKey("title")) c.setTitle(body.get("title"));
        if(body.containsKey("description")) c.setDescription(body.get("description"));
        return repository.save(c);
    }
}