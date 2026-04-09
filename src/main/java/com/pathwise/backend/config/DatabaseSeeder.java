package com.pathwise.backend.config;

import com.pathwise.backend.model.Career;
import com.pathwise.backend.model.Mentor;
import com.pathwise.backend.model.Resource;
import com.pathwise.backend.repository.CareerRepository;
import com.pathwise.backend.repository.MentorRepository;
import com.pathwise.backend.repository.ResourceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DatabaseSeeder {

    @Bean
    public CommandLineRunner seedDatabase(MentorRepository mentorRepo, CareerRepository careerRepo, ResourceRepository resourceRepo) {
        return args -> {
            // Natively Wipe Existing Mentors and Careers
            mentorRepo.deleteAll();
            careerRepo.deleteAll();
            resourceRepo.deleteAll();

            // Re-Seed Native Telugu & Hindi Mentors Globally
            Mentor m1 = new Mentor(); m1.setName("Karthik Reddy"); m1.setExpertise("Senior SDE @ Google");
            Mentor m2 = new Mentor(); m2.setName("Ananya Sharma"); m2.setExpertise("ML Engineer @ Amazon");
            Mentor m3 = new Mentor(); m3.setName("Rajesh Varma"); m3.setExpertise("Product Lead @ Flipkart");
            Mentor m4 = new Mentor(); m4.setName("Rakesh Naidu"); m4.setExpertise("Cybersec Specialist @ TCS");
            mentorRepo.saveAll(Arrays.asList(m1, m2, m3, m4));

            // Re-Seed Expansive Career Vectors
            Career c1 = new Career(); c1.setTitle("Software Engineering"); c1.setDescription("Build scalable software systems.");
            Career c2 = new Career(); c2.setTitle("AI / Machine Learning"); c2.setDescription("Design intelligent systems.");
            Career c3 = new Career(); c3.setTitle("Data Science & Analytics"); c3.setDescription("Turn raw data into business insights.");
            Career c4 = new Career(); c4.setTitle("UI/UX Design"); c4.setDescription("Craft beautiful digital experiences.");
            Career c5 = new Career(); c5.setTitle("Cyber Security Analyst"); c5.setDescription("Protect organizations from cyber threats.");
            Career c6 = new Career(); c6.setTitle("Cloud Architect"); c6.setDescription("Design large scale cloud infrastructure.");
            Career c7 = new Career(); c7.setTitle("Product Manager"); c7.setDescription("Lead cross-functional teams.");
            Career c8 = new Career(); c8.setTitle("Game Developer"); c8.setDescription("Develop interactive gaming experiences.");
            careerRepo.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8));

            // Seed Correct Premium Resources for UI Testing
            Resource r1 = new Resource(); r1.setTitle("Complete Web Development Bootcamp"); r1.setType("Course"); r1.setUrl("https://www.udemy.com");
            Resource r2 = new Resource(); r2.setTitle("Data Structures in Java"); r2.setType("Video Playlist"); r2.setUrl("https://www.youtube.com");
            Resource r3 = new Resource(); r3.setTitle("AWS Cloud Practitioner Guide"); r3.setType("Documentation"); r3.setUrl("https://aws.amazon.com");
            Resource r4 = new Resource(); r4.setTitle("Figma UI/UX Masterclass"); r4.setType("Tutorial"); r4.setUrl("https://www.figma.com");
            Resource r5 = new Resource(); r5.setTitle("Crack the PM Interview"); r5.setType("Article"); r5.setUrl("https://www.productschool.com");
            Resource r6 = new Resource(); r6.setTitle("Intro to Ethical Hacking"); r6.setType("Video"); r6.setUrl("https://www.youtube.com");
            resourceRepo.saveAll(Arrays.asList(r1, r2, r3, r4, r5, r6));

            System.out.println("✅ DATABASE PERFECTLY WIPED AND RE-SEEDED NATIVELY.");
        };
    }
}
