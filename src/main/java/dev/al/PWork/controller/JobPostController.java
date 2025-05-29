package dev.al.PWork.controller;

import dev.al.PWork.entity.JobPost;
import dev.al.PWork.entity.Recruiter;
import dev.al.PWork.service.JobPostService;
import dev.al.PWork.service.RecruiterService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasRole('RECRUITER')")
@RestController
@RequestMapping("/api/job-posts")
public class JobPostController {

    private final JobPostService jobPostService;
    private final RecruiterService recruiterService;

    public JobPostController(JobPostService jobPostService, RecruiterService recruiterService) {
        this.jobPostService = jobPostService;
        this.recruiterService = recruiterService;
    }

    @PostMapping
    public ResponseEntity<JobPost> createJobPost(@RequestBody JobPost jobPost) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Recruiter recruiter = recruiterService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));

        jobPost.setRecruiter(recruiter);
        JobPost createdJobPost = jobPostService.createJobPost(jobPost);
        return ResponseEntity.ok(createdJobPost);
    }

    @GetMapping
    public ResponseEntity<List<JobPost>> getAllJobPosts() {
        return ResponseEntity.ok(jobPostService.getAllJobPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPost> getJobPostById(@PathVariable Long id) {
        return jobPostService.getJobPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobPost> updateJobPost(@PathVariable Long id, @RequestBody JobPost updatedJobPost) {
        try {
            JobPost updated = jobPostService.updateJobPost(id, updatedJobPost);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobPost(@PathVariable Long id) {
        jobPostService.deleteJobPostById(id);
        return ResponseEntity.noContent().build();
    }
}
