package Alumni.Project.service;

import Alumni.Project.dto.SkillRequest;
import Alumni.Project.dto.response.SkillResponse;
import Alumni.Project.dto.update.SkillUpdateRequest;

import java.util.List;

public interface SkillService {

    SkillResponse createSkill(
            SkillRequest request
    );

    List<SkillResponse> getAllSkills();

    SkillResponse getSkillById(
            Long id
    );

    SkillResponse getSkillByName(
            String skillName
    );

    SkillResponse updateSkill(
            Long id,
            SkillUpdateRequest request
    );

    void deleteSkill(
            Long id
    );
}