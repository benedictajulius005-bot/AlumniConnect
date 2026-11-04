package Alumni.Project.service;

import Alumni.Project.dto.JobSkillRequest;
import Alumni.Project.dto.response.JobSkillResponse;
import Alumni.Project.dto.update.JobSkillUpdateRequest;

import java.util.List;

public interface JobSkillService {

    JobSkillResponse createJobSkill(
            JobSkillRequest request
    );

    List<JobSkillResponse> getAllJobSkills();

    JobSkillResponse getJobSkillById(
            Long id
    );

    List<JobSkillResponse> getSkillsByJob(
            Long jobOpportunityId
    );

    List<JobSkillResponse> getJobsBySkill(
            Long skillId
    );

    JobSkillResponse updateJobSkill(
            Long id,
            JobSkillUpdateRequest request
    );

    void deleteJobSkill(
            Long id
    );
}