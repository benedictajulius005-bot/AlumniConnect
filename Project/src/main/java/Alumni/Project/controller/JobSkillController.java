package Alumni.Project.controller;

import Alumni.Project.dto.JobSkillRequest;
import Alumni.Project.dto.response.JobSkillResponse;
import Alumni.Project.dto.update.JobSkillUpdateRequest;
import Alumni.Project.service.JobSkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-skills")
@RequiredArgsConstructor
@Tag(
        name = "Job Skills",
        description = "Endpoints for managing the skills required by job opportunities"
)
public class JobSkillController {

    private final JobSkillService
            jobSkillService;


    // Add a Skill to a Job

    @Operation(
            summary = "Add a skill to a job",
            description = "Associates a skill with a job opportunity and specifies whether the skill is required."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Skill successfully added to the job opportunity"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid job skill data"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. Administrator privileges are required."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Job opportunity or skill was not found"
            )
    })
    @PostMapping
    public ResponseEntity<JobSkillResponse>
    createJobSkill(

            @Valid @RequestBody JobSkillRequest request
    ) {

        JobSkillResponse response =
                jobSkillService
                        .createJobSkill(
                                request
                        );


        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }


    // Get all Job Skills

    @Operation(
            summary = "Get all job skills",
            description = "Returns all skill associations between job opportunities and skills."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Job skills retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            )
    })
    @GetMapping
    public ResponseEntity
            <List<JobSkillResponse>>
    getAllJobSkills() {

        return ResponseEntity.ok(
                jobSkillService
                        .getAllJobSkills()
        );
    }


    // Get Job Skill by ID

    @Operation(
            summary = "Get job skill by ID",
            description = "Retrieves a job-skill association using its database ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Job skill retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Job skill with the specified ID was not found"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<JobSkillResponse>
    getJobSkillById(

            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                jobSkillService
                        .getJobSkillById(
                                id
                        )
        );
    }


    // Get Skills belonging to a Job

    @Operation(
            summary = "Get skills required by a job",
            description = "Returns the skills associated with a specific job opportunity."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Job skills retrieved successfully"
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
    @GetMapping(
            "/job/{jobOpportunityId}"
    )
    public ResponseEntity
            <List<JobSkillResponse>>
    getSkillsByJob(

            @PathVariable
                    Long jobOpportunityId
    ) {

        return ResponseEntity.ok(
                jobSkillService
                        .getSkillsByJob(
                                jobOpportunityId
                        )
        );
    }


    // Get Jobs using a particular Skill

    @Operation(
            summary = "Get jobs requiring a skill",
            description = "Returns the job opportunities that are associated with a specific skill."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Jobs requiring the skill retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Skill with the specified ID was not found"
            )
    })
    @GetMapping(
            "/skill/{skillId}"
    )
    public ResponseEntity
            <List<JobSkillResponse>>
    getJobsBySkill(

            @PathVariable
                    Long skillId
    ) {

        return ResponseEntity.ok(
                jobSkillService
                        .getJobsBySkill(
                                skillId
                        )
        );
    }


    // Update Required Status

    @Operation(
            summary = "Update job skill requirement status",
            description = "Updates whether a skill is required for a specific job opportunity."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Job skill updated successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid update data"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. Administrator privileges are required."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Job skill with the specified ID was not found"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<JobSkillResponse>
    updateJobSkill(

            @PathVariable Long id,

            @Valid @RequestBody
            JobSkillUpdateRequest request
    ) {

        return ResponseEntity.ok(
                jobSkillService
                        .updateJobSkill(
                                id,
                                request
                        )
        );
    }


    // Delete Job Skill

    @Operation(
            summary = "Remove a skill from a job",
            description = "Removes the association between a skill and a job opportunity."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Skill removed from job opportunity successfully"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. Administrator privileges are required."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Job skill with the specified ID was not found"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    deleteJobSkill(

            @PathVariable Long id
    ) {

        jobSkillService
                .deleteJobSkill(
                        id
                );


        return ResponseEntity.ok(
                "Skill removed from job opportunity successfully"
        );
    }
}