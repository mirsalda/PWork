package dev.al.PWork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavedJobDto {
    private Long id;
    private Long jobPostId;
    private String jobTitle;
    private String companyName;
    private String location;
    private LocalDate savedAt;
}
