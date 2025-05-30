package dev.al.PWork.repository;

import dev.al.PWork.entity.JobPost;
import dev.al.PWork.entity.SavedJob;
import dev.al.PWork.entity.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SavedJobRepository extends JpaRepository<SavedJob, Long> {
    List<SavedJob> findByJobSeeker(JobSeeker jobSeeker);
    boolean existsByJobSeekerAndJobPost(JobSeeker jobSeeker, JobPost jobPost);
}
