package dev.al.PWork.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "saved_jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavedJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Job seeker must not be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_seeker_id")
    private JobSeeker jobSeeker;

    @NotNull(message = "Job post must not be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_post_id")
    private JobPost jobPost;

    @NotNull(message = "Saved date must not be null")
    private LocalDate savedAt;
}
