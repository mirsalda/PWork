package dev.al.PWork.service;

import dev.al.PWork.entity.Application;

import java.util.List;
import java.util.Optional;

public interface ApplicationService {
    Application save(Application application);
    Optional<Application> findById(Long id);
    List<Application> findAll();
    void deleteById(Long id);
}
