package dev.al.PWork.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobSearchCriteriaDTO {
    private String location;
    private String jobType; //(full time, part-time , etc)
    private Double minSalary;
    private Double maxSalary;
    private LocalDate postedAfter; //kerko pune te postuara pas kesaj date
private Boolean active; //nese eshte aktive apo jo
}
//Kjo Dto sherben per kerkimin e puneve (filtrimin e tyre ne baze te fushave te caktuara)