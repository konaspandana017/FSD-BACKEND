package com.pathwise.backend.controller;

import com.pathwise.backend.model.Resource;
import com.pathwise.backend.repository.ResourceRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/resources")
@CrossOrigin(origins = "*")
public class ResourceController {

    private final ResourceRepository repository;

    public ResourceController(ResourceRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Resource> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Resource create(@RequestBody Resource resource) {
        return repository.save(resource);
    }

    @DeleteMapping("/{id}")
    public void deleteResource(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
