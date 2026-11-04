package Alumni.Project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobRecommendationResponse {

    private Long jobOpportunityId;

    private String jobTitle;

    private String companyName;

    private String location;

    private String employmentType;

    private Double matchScore;

    private Boolean recommended;

    private List<String> matchingSkills = new ArrayList<>();

    private List<String> missingSkills = new ArrayList<>();
}