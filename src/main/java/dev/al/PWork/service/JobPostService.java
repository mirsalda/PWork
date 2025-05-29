package dev.al.PWork.service;

import dev.al.PWork.entity.JobPost;

import java.util.List;
import java.util.Optional;

public interface JobPostService {
    JobPost createJobPost(JobPost jobPost);
    List<JobPost> getAllJobPosts();
    Optional<JobPost> getJobPostById(Long id);
    JobPost updateJobPost(Long id, JobPost updatedJobPost);
    void deleteJobPostById(Long id);


}
