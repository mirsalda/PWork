package dev.al.PWork.repository;

import dev.al.PWork.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecruiterRepository extends JpaRepository<Recruiter, Long> {

    Optional<Recruiter> findByUserUsername(String username);

}
