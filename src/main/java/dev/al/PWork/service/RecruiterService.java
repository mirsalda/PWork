package dev.al.PWork.service;

import dev.al.PWork.entity.Recruiter;

import java.util.List;
import java.util.Optional;

public interface RecruiterService {
    Recruiter createRecruiter(Recruiter recruiter);
    List<Recruiter> getAllRecruiters();
    Optional<Recruiter> getRecruiterById(Long id);
    Recruiter updateRecruiter(Long id, Recruiter updatedRecruiter);
    void deleteRecruiter(Long id);

    Optional<Recruiter> findByUsername(String username);
}
