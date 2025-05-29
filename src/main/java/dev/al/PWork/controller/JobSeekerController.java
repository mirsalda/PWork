package dev.al.PWork.controller;

import dev.al.PWork.entity.JobSeeker;
import dev.al.PWork.service.JobSeekerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobseekers")
public class JobSeekerController {

    private final JobSeekerService jobSeekerService;

    public JobSeekerController(JobSeekerService jobSeekerService) {
        this.jobSeekerService = jobSeekerService;
    }

    @PostMapping
    public ResponseEntity<JobSeeker> createJobSeeker(@RequestBody JobSeeker jobSeeker) {
        JobSeeker createdJobSeeker = jobSeekerService.createJobSeeker(jobSeeker);
        return ResponseEntity.ok(createdJobSeeker);
    }

    @GetMapping
    public ResponseEntity<List<JobSeeker>> getAllJobSeekers() {
        List<JobSeeker> jobSeekers = jobSeekerService.getAllJobSeekers();
        return ResponseEntity.ok(jobSeekers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobSeeker> getJobSeekerById(@PathVariable Long id) {
        Optional<JobSeeker> jobSeeker = jobSeekerService.getJobSeekerById(id);
        return jobSeeker.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobSeeker> updateJobSeeker(@PathVariable Long id, @RequestBody JobSeeker updatedJobSeeker) {
        try {
            JobSeeker jobSeeker = jobSeekerService.updateJobSeeker(id, updatedJobSeeker);
            return ResponseEntity.ok(jobSeeker);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobSeeker(@PathVariable Long id) {
        jobSeekerService.deleteJobSeeker(id);
        return ResponseEntity.noContent().build();
    }
}
