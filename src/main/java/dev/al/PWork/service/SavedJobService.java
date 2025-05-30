package dev.al.PWork.service;

import dev.al.PWork.dto.SavedJobDto;
import dev.al.PWork.entity.JobPost;
import dev.al.PWork.entity.JobSeeker;
import dev.al.PWork.entity.SavedJob;

import java.util.List;

public interface SavedJobService {

    // Ruan një punë të ruajtur nga jobSeeker për jobPost të caktuar
    SavedJob saveJob(JobSeeker jobSeeker, JobPost jobPost);

    // Merr listën e SavedJobDto për jobSeeker-in e dhënë
    List<SavedJobDto> getSavedJobsDto(JobSeeker jobSeeker);

    // Fshin SavedJob bazuar në ID
    void deleteSavedJob(Long savedJobId);
}
