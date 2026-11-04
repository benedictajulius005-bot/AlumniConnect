package Alumni.Project.controller;

import Alumni.Project.dto.SkillRequest;
import Alumni.Project.dto.response.SkillResponse;
import Alumni.Project.dto.update.SkillUpdateRequest;
import Alumni.Project.service.SkillService;
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
@RequestMapping("/api/skills")
@RequiredArgsConstructor
@Tag(
        name = "Skills",
        description = "Endpoints for managing skills used by alumni and job opportunities"
)
public class SkillController {

    private final SkillService skillService;


    // Create Skill

    @Operation(
            summary = "Create a skill",
            description = "Creates a new skill. This operation is restricted to administrators."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Skill created successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid skill data"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. Administrator privileges are required."
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "A skill with the same name already exists"
            )
    })
    @PostMapping
    public ResponseEntity<SkillResponse>
    createSkill(
            @Valid @RequestBody SkillRequest request
    ) {

        SkillResponse response =
                skillService.createSkill(
                        request
                );


        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }


    // Get all Skills

    @Operation(
            summary = "Get all skills",
            description = "Returns a list of all skills available in the system."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Skills retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            )
    })
    @GetMapping
    public ResponseEntity<List<SkillResponse>>
    getAllSkills() {

        return ResponseEntity.ok(
                skillService.getAllSkills()
        );
    }


    // Get Skill by ID

    @Operation(
            summary = "Get skill by ID",
            description = "Retrieves a skill using its database ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Skill retrieved successfully"
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
    @GetMapping("/{id}")
    public ResponseEntity<SkillResponse>
    getSkillById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                skillService.getSkillById(
                        id
                )
        );
    }


    // Get Skill by name

    @Operation(
            summary = "Get skill by name",
            description = "Retrieves a skill using its skill name."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Skill retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Skill with the specified name was not found"
            )
    })
    @GetMapping("/name/{skillName}")
    public ResponseEntity<SkillResponse>
    getSkillByName(
            @PathVariable String skillName
    ) {

        return ResponseEntity.ok(
                skillService.getSkillByName(
                        skillName
                )
        );
    }


    // Update Skill

    @Operation(
            summary = "Update a skill",
            description = "Updates an existing skill. This operation is restricted to administrators."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Skill updated successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid skill data"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. Administrator privileges are required."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Skill with the specified ID was not found"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "A skill with the same name already exists"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<SkillResponse>
    updateSkill(
            @PathVariable Long id,

            @Valid @RequestBody
            SkillUpdateRequest request
    ) {

        return ResponseEntity.ok(
                skillService.updateSkill(
                        id,
                        request
                )
        );
    }


    // Delete Skill

    @Operation(
            summary = "Delete a skill",
            description = "Deletes an existing skill. This operation is restricted to administrators."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Skill deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. Administrator privileges are required."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Skill with the specified ID was not found"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    deleteSkill(
            @PathVariable Long id
    ) {

        skillService.deleteSkill(
                id
        );


        return ResponseEntity.ok(
                "Skill deleted successfully"
        );
    }
}