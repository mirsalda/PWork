package dev.al.PWork.service;

import dev.al.PWork.entity.JobSeeker;

import java.util.List;
import java.util.Optional;

public interface JobSeekerService {
    JobSeeker createJobSeeker(JobSeeker jobSeeker);
    List<JobSeeker> getAllJobSeekers();
    Optional<JobSeeker> getJobSeekerById(Long id);
    JobSeeker updateJobSeeker(Long id, JobSeeker updatedJobSeeker);
    void deleteJobSeeker(Long id);
}
