package Alumni.Project.service;

import Alumni.Project.dto.JobOpportunityRequest;
import Alumni.Project.dto.response.JobOpportunityResponse;
import Alumni.Project.dto.update.JobOpportunityUpdateRequest;

import java.util.List;

public interface JobOpportunityService {

    JobOpportunityResponse createJobOpportunity(
            JobOpportunityRequest request
    );

    List<JobOpportunityResponse>
    getAllJobOpportunities();

    List<JobOpportunityResponse>
    getActiveJobOpportunities();

    JobOpportunityResponse
    getJobOpportunityById(
            Long id
    );

    List<JobOpportunityResponse>
    getJobsByDepartment(
            Long departmentId
    );

    List<JobOpportunityResponse>
    getJobsByCompany(
            String companyName
    );

    List<JobOpportunityResponse>
    getJobsByEmploymentType(
            String employmentType
    );

    JobOpportunityResponse
    updateJobOpportunity(
            Long id,
            JobOpportunityUpdateRequest request
    );

    void deleteJobOpportunity(
            Long id
    );
}