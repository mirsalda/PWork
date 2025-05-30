package dev.al.PWork.repository;

import dev.al.PWork.entity.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {
    Optional<JobSeeker> findByUserId(Long userId);
    Optional<JobSeeker> findByUserUsername(String username);

}
