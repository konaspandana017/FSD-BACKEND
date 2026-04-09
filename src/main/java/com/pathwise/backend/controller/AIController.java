package com.pathwise.backend.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/ai")
@CrossOrigin(origins = "*")
public class AIController {

    @PostMapping("/chat")
    public String chat(@RequestBody Map<String, String> body) {

        String message = body.get("message");

        // 🔥 MOCK AI RESPONSE (NO API NEEDED)
        if (message.toLowerCase().contains("cse")) {
            return "For CSE students, top career paths include:\n\n"
                    + "1. Software Developer\n"
                    + "2. Data Scientist\n"
                    + "3. AI/ML Engineer\n"
                    + "4. Cybersecurity Specialist\n"
                    + "5. Cloud Engineer\n\n"
                    + "Focus on coding + projects + internships 🚀";
        }

        return "Based on your interests, you can explore tech, management, or creative fields. Try identifying your strengths first.";
    }
}