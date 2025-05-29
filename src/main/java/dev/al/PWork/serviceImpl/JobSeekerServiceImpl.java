package dev.al.PWork.serviceImpl;

import dev.al.PWork.entity.JobSeeker;
import dev.al.PWork.entity.User;
import dev.al.PWork.repository.JobSeekerRepository;
import dev.al.PWork.repository.UserRepository;
import dev.al.PWork.service.JobSeekerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobSeekerServiceImpl implements JobSeekerService {

    private final JobSeekerRepository jobSeekerRepository;
    private final UserRepository userRepository;

    public JobSeekerServiceImpl(JobSeekerRepository jobSeekerRepository, UserRepository userRepository) {
        this.jobSeekerRepository = jobSeekerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public JobSeeker createJobSeeker(JobSeeker jobSeeker) {
        Long userId = jobSeeker.getUser().getId();
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        jobSeeker.setUser(existingUser);
        return jobSeekerRepository.save(jobSeeker);
    }

    @Override
    public List<JobSeeker> getAllJobSeekers() {
        return jobSeekerRepository.findAll();
    }

    @Override
    public Optional<JobSeeker> getJobSeekerById(Long id) {
        return jobSeekerRepository.findById(id);
    }

    @Override
    public JobSeeker updateJobSeeker(Long id, JobSeeker updatedJobSeeker) {
        return jobSeekerRepository.findById(id)
                .map(jobSeeker -> {
                    jobSeeker.setFullName(updatedJobSeeker.getFullName());
                    jobSeeker.setEmail(updatedJobSeeker.getEmail());
                    jobSeeker.setPhoneNumber(updatedJobSeeker.getPhoneNumber());
                    jobSeeker.setAddress(updatedJobSeeker.getAddress());
                    jobSeeker.setSkills(updatedJobSeeker.getSkills());
                    jobSeeker.setExperience(updatedJobSeeker.getExperience());
                    jobSeeker.setLinkedInProfile(updatedJobSeeker.getLinkedInProfile());
                    jobSeeker.setGithubProfile(updatedJobSeeker.getGithubProfile());
                    jobSeeker.setCvFilePath(updatedJobSeeker.getCvFilePath());

                    Long userId = updatedJobSeeker.getUser().getId();
                    User existingUser = userRepository.findById(userId)
                            .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
                    jobSeeker.setUser(existingUser);

                    return jobSeekerRepository.save(jobSeeker);
                }).orElseThrow(() -> new RuntimeException("JobSeeker not found with id: " + id));
    }

    @Override
    public void deleteJobSeeker(Long id) {
        jobSeekerRepository.deleteById(id);
    }
}
