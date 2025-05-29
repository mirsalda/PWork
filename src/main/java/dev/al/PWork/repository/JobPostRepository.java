package dev.al.PWork.repository;

import dev.al.PWork.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Long> {


    List<JobPost> findByIsActiveTrue();


    List<JobPost> findByLocationContainingIgnoreCase(String location);


}
