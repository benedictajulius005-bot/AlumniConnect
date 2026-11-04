package Alumni.Project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobSkillResponse {

    private Long id;

    private Long jobOpportunityId;

    private String jobTitle;

    private Long skillId;

    private String skillName;

    private Boolean required;
}