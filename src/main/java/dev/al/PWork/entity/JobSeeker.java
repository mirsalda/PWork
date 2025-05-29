package dev.al.PWork.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobSeeker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Lidhja me User (1:1)
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotBlank(message = "Full name is required")
    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must be at most 100 characters")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[+]?\\d{8,15}$", message = "Invalid phone number format")
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address must be at most 255 characters")
    private String address;

    @NotBlank(message = "Skills are required")
    @Size(max = 500, message = "Skills must be at most 500 characters")
    private String skills;

    @NotBlank(message = "Experience is required")
    @Size(max = 1000, message = "Experience must be at most 1000 characters")
    private String experience;

    @Size(max = 255, message = "LinkedIn profile must be at most 255 characters")
    @Pattern(regexp = "^(https?://)?(www\\.)?linkedin\\.com/.*$", message = "Invalid LinkedIn profile URL")
    private String linkedInProfile;

    @Size(max = 255, message = "GitHub profile must be at most 255 characters")
    @Pattern(regexp = "^(https?://)?(www\\.)?github\\.com/.*$", message = "Invalid GitHub profile URL")
    private String githubProfile;

    @Size(max = 255, message = "CV file path must be at most 255 characters")
    private String cvFilePath;
}
