package dev.al.PWork.dto;



import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPostDTO {
    private String title;
    private String description;
    private String location;
    private LocalDate postedDate;
    private LocalDate expirationDate;
    private Boolean active;
    private String jobType;
    private Double salary;
    private String salaryCurrency;
    private String requirements;
    private String benefits;
    private Long recruiterId;
}
