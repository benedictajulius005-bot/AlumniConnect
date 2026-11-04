package Alumni.Project.controller;

import Alumni.Project.dto.JobOpportunityRequest;
import Alumni.Project.dto.response.JobOpportunityResponse;
import Alumni.Project.dto.update.JobOpportunityUpdateRequest;
import Alumni.Project.service.JobOpportunityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        "/api/job-opportunities"
)
@RequiredArgsConstructor
@Tag(
        name = "Job Opportunities",
        description = "Endpoints for managing and searching job opportunities"
)
public class JobOpportunityController {

    private final JobOpportunityService
            jobOpportunityService;


    // Create Job Opportunity

    @Operation(
            summary = "Create a job opportunity",
            description = "Creates a new job opportunity. Administrators and authorized employers can create job opportunities."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Job opportunity created successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid job opportunity data"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. Administrator or employer privileges are required."
            )
    })
    @PostMapping
    public ResponseEntity
            <JobOpportunityResponse>
    createJobOpportunity(

            @Valid @RequestBody
            JobOpportunityRequest request
    ) {

        JobOpportunityResponse response =
                jobOpportunityService
                        .createJobOpportunity(
                                request
                        );


        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }


    // Get all Job Opportunities

    @Operation(
            summary = "Get all job opportunities",
            description = "Returns all job opportunities available in the system."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Job opportunities retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            )
    })
    @GetMapping
    public ResponseEntity
            <List<JobOpportunityResponse>>
    getAllJobOpportunities() {

        return ResponseEntity.ok(
                jobOpportunityService
                        .getAllJobOpportunities()
        );
    }


    // Get active Job Opportunities

    @Operation(
            summary = "Get active job opportunities",
            description = "Returns only job opportunities that are currently marked as active."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Active job opportunities retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            )
    })
    @GetMapping("/active")
    public ResponseEntity
            <List<JobOpportunityResponse>>
    getActiveJobOpportunities() {

        return ResponseEntity.ok(
                jobOpportunityService
                        .getActiveJobOpportunities()
        );
    }


    // Get Job Opportunity by ID

    @Operation(
            summary = "Get job opportunity by ID",
            description = "Retrieves a job opportunity using its database ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Job opportunity retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Job opportunity with the specified ID was not found"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity
            <JobOpportunityResponse>
    getJobOpportunityById(

            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                jobOpportunityService
                        .getJobOpportunityById(
                                id
                        )
        );
    }


    // Get Jobs by Department

    @Operation(
            summary = "Get jobs by department",
            description = "Returns job opportunities associated with a specific university department."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Job opportunities retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            )
    })
    @GetMapping(
            "/department/{departmentId}"
    )
    public ResponseEntity
            <List<JobOpportunityResponse>>
    getJobsByDepartment(

            @PathVariable
                    Long departmentId
    ) {

        return ResponseEntity.ok(
                jobOpportunityService
                        .getJobsByDepartment(
                                departmentId
                        )
        );
    }


    // Get Jobs by Company

    @Operation(
            summary = "Get jobs by company",
            description = "Returns job opportunities posted by the specified company."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Job opportunities retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            )
    })
    @GetMapping(
            "/company/{companyName}"
    )
    public ResponseEntity
            <List<JobOpportunityResponse>>
    getJobsByCompany(

            @PathVariable
                    String companyName
    ) {

        return ResponseEntity.ok(
                jobOpportunityService
                        .getJobsByCompany(
                                companyName
                        )
        );
    }


    // Get Jobs by Employment Type

    @Operation(
            summary = "Get jobs by employment type",
            description = "Returns job opportunities matching the specified employment type."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Job opportunities retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            )
    })
    @GetMapping(
            "/employment-type/{employmentType}"
    )
    public ResponseEntity
            <List<JobOpportunityResponse>>
    getJobsByEmploymentType(

            @PathVariable
                    String employmentType
    ) {

        return ResponseEntity.ok(
                jobOpportunityService
                        .getJobsByEmploymentType(
                                employmentType
                        )
        );
    }


    // Update Job Opportunity

    @Operation(
            summary = "Update a job opportunity",
            description = "Updates an existing job opportunity. This operation is currently restricted to administrators."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Job opportunity updated successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid job opportunity data"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. Administrator privileges are required."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Job opportunity with the specified ID was not found"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity
            <JobOpportunityResponse>
    updateJobOpportunity(

            @PathVariable Long id,

            @Valid @RequestBody
                    JobOpportunityUpdateRequest request
    ) {

        return ResponseEntity.ok(
                jobOpportunityService
                        .updateJobOpportunity(
                                id,
                                request
                        )
        );
    }


    // Delete Job Opportunity

    @Operation(
            summary = "Delete a job opportunity",
            description = "Deletes an existing job opportunity. This operation is currently restricted to administrators."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Job opportunity deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. Administrator privileges are required."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Job opportunity with the specified ID was not found"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    deleteJobOpportunity(

            @PathVariable Long id
    ) {

        jobOpportunityService
                .deleteJobOpportunity(
                        id
                );


        return ResponseEntity.ok(
                "Job opportunity deleted successfully"
        );
    }
}