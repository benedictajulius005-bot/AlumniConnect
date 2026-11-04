
package Alumni.Project.service;

import Alumni.Project.dto.EmploymentHistoryRequest;
import Alumni.Project.dto.response.EmploymentHistoryResponse;

import java.util.List;

public interface EmploymentHistoryService {

    EmploymentHistoryResponse createEmploymentHistory(
            EmploymentHistoryRequest request
    );

    List<EmploymentHistoryResponse> getEmploymentHistoryByAlumniId(
            Long alumniId
    );

    EmploymentHistoryResponse getEmploymentHistoryById(Long id);

    EmploymentHistoryResponse updateEmploymentHistory(
            Long id,
            EmploymentHistoryRequest request
    );

    void deleteEmploymentHistory(Long id);
}
