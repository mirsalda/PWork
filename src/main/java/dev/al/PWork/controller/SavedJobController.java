package dev.al.PWork.controller;

import dev.al.PWork.dto.SavedJobDto;
import dev.al.PWork.entity.JobPost;
import dev.al.PWork.entity.JobSeeker;
import dev.al.PWork.service.SavedJobService;
import dev.al.PWork.service.JobPostService;
import dev.al.PWork.service.JobSeekerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/saved-jobs")
public class SavedJobController {

    private final SavedJobService savedJobService;
    private final JobSeekerService jobSeekerService;
    private final JobPostService jobPostService;

    public SavedJobController(SavedJobService savedJobService,
                              JobSeekerService jobSeekerService,
                              JobPostService jobPostService) {
        this.savedJobService = savedJobService;
        this.jobSeekerService = jobSeekerService;
        this.jobPostService = jobPostService;
    }

    @PostMapping("/{jobPostId}")
    public ResponseEntity<?> saveJob(@PathVariable Long jobPostId) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();

            JobSeeker jobSeeker = jobSeekerService.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Job seeker not found."));

            JobPost jobPost = jobPostService.getJobPostById(jobPostId)
                    .orElseThrow(() -> new RuntimeException("Job post not found."));

            // Ruaj saved job
            savedJobService.saveJob(jobSeeker, jobPost);
            return ResponseEntity.ok("Job successfully saved.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<SavedJobDto>> getSavedJobs() {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();

            JobSeeker jobSeeker = jobSeekerService.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Job seeker not found."));

            List<SavedJobDto> savedJobs = savedJobService.getSavedJobsDto(jobSeeker);
            return ResponseEntity.ok(savedJobs);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSavedJob(@PathVariable Long id) {
        try {
            savedJobService.deleteSavedJob(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error while deleting saved job.");
        }
    }
}
