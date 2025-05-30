package dev.al.PWork.specification;

import dev.al.PWork.entity.JobPost;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class JobPostSpecification {

    public static Specification<JobPost> hasLocation(String location) {
        return (root, query, cb) ->
                location == null ? null : cb.like(cb.lower(root.get("location")), "%" + location.toLowerCase() + "%");
    }

    public static Specification<JobPost> hasJobType(String jobType) {
        return (root, query, cb) ->
                jobType == null ? null : cb.equal(root.get("jobType"), jobType);
    }

    public static Specification<JobPost> hasSalaryGreaterThanOrEqual(Double salaryMin) {
        return (root, query, cb) ->
                salaryMin == null ? null : cb.greaterThanOrEqualTo(root.get("salary"), salaryMin);
    }

    public static Specification<JobPost> hasSalaryLessThanOrEqual(Double salaryMax) {
        return (root, query, cb) ->
                salaryMax == null ? null : cb.lessThanOrEqualTo(root.get("salary"), salaryMax);
    }

    public static Specification<JobPost> isActive(Boolean active) {
        return (root, query, cb) ->
                active == null ? null : cb.equal(root.get("active"), active);
    }

    public static Specification<JobPost> postedAfter(LocalDate dateFrom) {
        return (root, query, cb) ->
                dateFrom == null ? null : cb.greaterThanOrEqualTo(root.get("postedDate"), dateFrom);
    }

    public static Specification<JobPost> postedBefore(LocalDate dateTo) {
        return (root, query, cb) ->
                dateTo == null ? null : cb.lessThanOrEqualTo(root.get("postedDate"), dateTo);
    }

}
