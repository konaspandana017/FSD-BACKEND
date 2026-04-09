package com.pathwise.backend.controller;

import com.pathwise.backend.model.Appointment;
import com.pathwise.backend.repository.AppointmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    private final AppointmentRepository repo;

    public AppointmentController(AppointmentRepository repo) {
        this.repo = repo;
    }

    // ✅ BOOK SESSION
    @PostMapping
    public Appointment book(@RequestBody Appointment appointment) {
        return repo.save(appointment);
    }

    @GetMapping
    public List<Appointment> getAll() {
        return repo.findAll();
    }

    @GetMapping("/student/{email}")
    public List<Appointment> getByStudent(@PathVariable String email) {
        return repo.findByStudentEmail(email);
    }

    @GetMapping("/mentor/{name}")
    public List<Appointment> getByMentor(@PathVariable String name) {
        return repo.findByMentorName(name);
    }

    @PatchMapping("/{id}")
    public Appointment updateStatus(@PathVariable Long id, @RequestBody java.util.Map<String, String> body) {
        Appointment app = repo.findById(id).orElseThrow();
        if (body.containsKey("status")) {
            app.setStatus(body.get("status"));
        }
        if (body.containsKey("meetingLink")) {
            app.setMeetingLink(body.get("meetingLink"));
        }
        if (body.containsKey("date")) {
            app.setDate(body.get("date"));
        }
        if (body.containsKey("time")) {
            app.setTime(body.get("time"));
        }
        return repo.save(app);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        repo.deleteById(id);
    }

}