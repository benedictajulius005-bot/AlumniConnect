package Alumni.Project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobSkillRequest {

    @NotNull(message = "Job opportunity ID is required")
    private Long jobOpportunityId;

    @NotNull(message = "Skill ID is required")
    private Long skillId;

    private Boolean required;
}