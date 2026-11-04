package Alumni.Project.repository;

import Alumni.Project.entity.JobSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobSkillRepository
        extends JpaRepository<JobSkill, Long> {

    List<JobSkill>
    findByJobOpportunityJobOpportunityId(
            Long jobOpportunityId
    );

    List<JobSkill>
    findBySkillId(
            Long skillId
    );

    boolean existsByJobOpportunityJobOpportunityIdAndSkillId(
            Long jobOpportunityId,
            Long skillId
    );
}