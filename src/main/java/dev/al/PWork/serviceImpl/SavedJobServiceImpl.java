package dev.al.PWork.serviceImpl;

import dev.al.PWork.dto.SavedJobDto;
import dev.al.PWork.entity.SavedJob;
import dev.al.PWork.entity.JobPost;
import dev.al.PWork.entity.JobSeeker;
import dev.al.PWork.repository.SavedJobRepository;
import dev.al.PWork.service.SavedJobService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SavedJobServiceImpl implements SavedJobService {

    private final SavedJobRepository savedJobRepository;

    public SavedJobServiceImpl(SavedJobRepository savedJobRepository) {
        this.savedJobRepository = savedJobRepository;
    }

    @Override
    public SavedJob saveJob(JobSeeker jobSeeker, JobPost jobPost) {
        if (savedJobRepository.existsByJobSeekerAndJobPost(jobSeeker, jobPost)) {
            throw new RuntimeException("Kjo pune eshte ruajtur tashme.");
        }
        SavedJob savedJob = SavedJob.builder()
                .jobSeeker(jobSeeker)
                .jobPost(jobPost)
                .savedAt(LocalDate.now())
                .build();
        return savedJobRepository.save(savedJob);
    }

    @Override
    public List<SavedJobDto> getSavedJobsDto(JobSeeker jobSeeker) {
        List<SavedJob> savedJobs = savedJobRepository.findByJobSeeker(jobSeeker);
        return savedJobs.stream()
                .map(savedJob -> {
                    JobPost jobPost = savedJob.getJobPost();
                    return new SavedJobDto(
                            savedJob.getId(),
                            jobPost.getId(),
                            jobPost.getTitle(),
                            jobPost.getRecruiter().getCompanyName(),
                            jobPost.getLocation(),
                            savedJob.getSavedAt()
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSavedJob(Long savedJobId) {
        savedJobRepository.deleteById(savedJobId);
    }
}
