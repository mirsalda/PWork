package dev.al.PWork.service.impl;

import dev.al.PWork.entity.Recruiter;
import dev.al.PWork.repository.RecruiterRepository;
import dev.al.PWork.service.RecruiterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecruiterServiceImpl implements RecruiterService {

    private final RecruiterRepository recruiterRepository;

    public RecruiterServiceImpl(RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
    }

    @Override
    public Recruiter createRecruiter(Recruiter recruiter) {
        return recruiterRepository.save(recruiter);
    }

    @Override
    public List<Recruiter> getAllRecruiters() {
        return recruiterRepository.findAll();
    }

    @Override
    public Optional<Recruiter> getRecruiterById(Long id) {
        return recruiterRepository.findById(id);
    }

    @Override
    public Recruiter updateRecruiter(Long id, Recruiter updatedRecruiter) {
        return recruiterRepository.findById(id)
                .map(recruiter -> {
                    recruiter.setCompanyName(updatedRecruiter.getCompanyName());
                    recruiter.setPosition(updatedRecruiter.getPosition());
                    recruiter.setUser(updatedRecruiter.getUser());

                    return recruiterRepository.save(recruiter);
                })
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));
    }

    @Override
    public void deleteRecruiter(Long id) {
        recruiterRepository.deleteById(id);
    }
}
