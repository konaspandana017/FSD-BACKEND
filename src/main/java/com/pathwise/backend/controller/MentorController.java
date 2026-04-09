package com.pathwise.backend.controller;

import com.pathwise.backend.model.Mentor;
import com.pathwise.backend.repository.MentorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mentors")
@CrossOrigin(origins = "*")
public class MentorController {

    private final MentorRepository mentorRepository;

    public MentorController(MentorRepository mentorRepository) {
        this.mentorRepository = mentorRepository;
    }

    @GetMapping
    public List<Mentor> getAllMentors() {
        return mentorRepository.findAll();
    }

    @PostMapping
    public Mentor addMentor(@RequestBody Mentor mentor) {
        return mentorRepository.save(mentor);
    }

    @DeleteMapping("/{id}")
    public void deleteMentor(@PathVariable Long id) {
        mentorRepository.deleteById(id);
    }

    @PatchMapping("/{id}")
    public Mentor updateMentor(@PathVariable Long id, @RequestBody java.util.Map<String, String> body) {
        Mentor m = mentorRepository.findById(id).orElseThrow();
        if(body.containsKey("name")) m.setName(body.get("name"));
        if(body.containsKey("expertise")) m.setExpertise(body.get("expertise"));
        return mentorRepository.save(m);
    }
}