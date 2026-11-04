package Alumni.Project.repository;

import Alumni.Project.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillRepository
        extends JpaRepository<Skill, Long> {

    boolean existsBySkillNameIgnoreCase(
            String skillName
    );

    Optional<Skill> findBySkillNameIgnoreCase(
            String skillName
    );
}