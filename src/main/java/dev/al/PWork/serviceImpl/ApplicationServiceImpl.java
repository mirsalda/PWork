package dev.al.PWork.serviceImpl;

import dev.al.PWork.entity.Application;
import dev.al.PWork.entity.JobPost;
import dev.al.PWork.entity.JobSeeker;
import dev.al.PWork.repository.ApplicationRepository;
import dev.al.PWork.repository.JobPostRepository;
import dev.al.PWork.repository.JobSeekerRepository;
import dev.al.PWork.service.ApplicationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobPostRepository jobPostRepository;
    private final JobSeekerRepository jobSeekerRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository,
                                  JobPostRepository jobPostRepository,
                                  JobSeekerRepository jobSeekerRepository) {
        this.applicationRepository = applicationRepository;
        this.jobPostRepository = jobPostRepository;
        this.jobSeekerRepository = jobSeekerRepository;
    }

    @Override
    public Application save(Application application) {
        JobPost job = jobPostRepository.findById(application.getJob().getId())
                .orElseThrow(() -> new RuntimeException("Job not found"));

        JobSeeker jobSeeker = jobSeekerRepository.findById(application.getJobSeeker().getId())
                .orElseThrow(() -> new RuntimeException("Job seeker not found"));

        application.setJob(job);
        application.setJobSeeker(jobSeeker);

        return applicationRepository.save(application);
    }

    @Override
    public Optional<Application> findById(Long id) {
        return applicationRepository.findById(id);
    }

    @Override
    public List<Application> findAll() {
        return applicationRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        applicationRepository.deleteById(id);
    }
}
