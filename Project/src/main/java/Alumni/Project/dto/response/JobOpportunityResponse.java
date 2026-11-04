package Alumni.Project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobOpportunityResponse {

    private Long jobOpportunityId;

    private String jobTitle;

    private String jobDescription;

    private String companyName;

    private String location;

    private String employmentType;

    private Double salary;

    private LocalDate applicationDeadline;

    private LocalDate postedDate;

    private Boolean active;

    private Long departmentId;

    private String departmentName;

    private List<String> requiredSkills = new ArrayList<>();

    private List<Long> requiredSkillIds = new ArrayList<>();

}