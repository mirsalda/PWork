package dev.al.PWork.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Lidhja me Recruiter (shume oferta pune per nje recruiter)
    @ManyToOne
    @JoinColumn(name = "recruiter_id", nullable = false)
    private Recruiter recruiter;

    @NotBlank(message = "Job title is required")
    @Size(min = 3, max = 100, message = "Job title must be between 3 and 100 characters")
    private String title;

    @NotBlank(message = "Job description is required")
    @Size(min = 10, max = 2000, message = "Description must be between 10 and 2000 characters")
    @Column(length = 2000)
    private String description;

    @NotBlank(message = "Location is required")
    @Size(max = 255, message = "Location must be at most 255 characters")
    private String location;

    @NotNull(message = "Posted date is required")
    private LocalDate postedDate;

    @NotNull(message = "Expiration date is required")
    private LocalDate expirationDate;

    @NotNull
    private Boolean active = true;

    @Size(max = 100, message = "Job type must be at most 100 characters")
    private String jobType; // (Full-time, Part-time, Internship)

    @Min(value = 0, message = "Salary must be positive")
    private Double salary;

    @Size(max = 100, message = "Salary currency must be at most 100 characters")
    private String salaryCurrency; // p.sh. EUR, USD

    @Size(max = 255, message = "Requirements must be at most 255 characters")
    private String requirements;

    @Size(max = 255, message = "Benefits must be at most 255 characters")
    private String benefits;

    private boolean isActive;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }
}
