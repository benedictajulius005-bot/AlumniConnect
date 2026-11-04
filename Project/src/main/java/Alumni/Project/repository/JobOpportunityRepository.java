package Alumni.Project.repository;

import Alumni.Project.entity.JobOpportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;

import java.util.List;

public interface JobOpportunityRepository
        extends JpaRepository<JobOpportunity, Long> {

    List<JobOpportunity> findByActiveTrueAndApplicationDeadlineGreaterThanEqual(
            LocalDate today
    );

    List<JobOpportunity> findByDepartmentId(
            Long departmentId
    );

    List<JobOpportunity>
    findByCompanyNameIgnoreCase(
            String companyName
    );

    List<JobOpportunity>
    findByEmploymentTypeIgnoreCase(
            String employmentType
    );
}