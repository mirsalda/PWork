package dev.al.PWork.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @NotNull(message = "Job must be provided")
    private JobPost job;

    @ManyToOne(optional = false)
    @NotNull(message = "JobSeeker must be provided")
    private JobSeeker jobSeeker;

    @NotBlank(message = "CV file name is required")
    @Size(max = 255)
    private String cvFileName;

    @NotBlank(message = "CV file path is required")
    @Size(max = 500)
    private String cvFilePath;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Application status is required")
    private ApplicationStatus status;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @NotNull(message = "Applied date/time is required")
    private LocalDateTime appliedAt;

    @Size(max = 1000)
    private String coverLetter;

    @Email
    @Size(max = 100)
    private String contactEmail;

    @Size(max = 20)
    private String contactPhone;


    public static enum ApplicationStatus {
        PENDING,
        ACCEPTED,
        REJECTED
    }
}
