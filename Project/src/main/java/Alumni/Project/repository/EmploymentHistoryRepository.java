
package Alumni.Project.repository;

import Alumni.Project.entity.EmploymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmploymentHistoryRepository
        extends JpaRepository<EmploymentHistory, Long> {

    List<EmploymentHistory> findByAlumni_Id(Long alumniId);
}
