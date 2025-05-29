package dev.al.PWork.serviceImpl;

import dev.al.PWork.entity.JobPost;
import dev.al.PWork.repository.JobPostRepository;
import dev.al.PWork.service.JobPostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobPostServiceImpl implements JobPostService {

    private final JobPostRepository jobPostRepository;

    public JobPostServiceImpl(JobPostRepository jobPostRepository) {
        this.jobPostRepository = jobPostRepository;
    }

    @Override
    public JobPost createJobPost(JobPost jobPost) {
        return jobPostRepository.save(jobPost);
    }

    @Override
    public List<JobPost> getAllJobPosts() {
        return jobPostRepository.findAll();
    }

    @Override
    public Optional<JobPost> getJobPostById(Long id) {
        return jobPostRepository.findById(id);
    }

    @Override
    public JobPost updateJobPost(Long id, JobPost updatedJobPost) {
        return jobPostRepository.findById(id)
                .map(jobPost -> {
                    jobPost.setTitle(updatedJobPost.getTitle());
                    jobPost.setDescription(updatedJobPost.getDescription());
                    jobPost.setLocation(updatedJobPost.getLocation());

                    jobPost.setSalary(updatedJobPost.getSalary());
                    jobPost.setSalaryCurrency(updatedJobPost.getSalaryCurrency());

                    jobPost.setJobType(updatedJobPost.getJobType());


                    jobPost.setPostedDate(updatedJobPost.getPostedDate());
                    jobPost.setExpirationDate(updatedJobPost.getExpirationDate());
                    jobPost.setActive(updatedJobPost.getActive());

                    jobPost.setRecruiter(updatedJobPost.getRecruiter());
                    return jobPostRepository.save(jobPost);
                }).orElseThrow(() -> new RuntimeException("JobPost not found with id: " + id));
    }

    @Override
    public void deleteJobPostById(Long id) {
        jobPostRepository.deleteById(id);
    }

}
