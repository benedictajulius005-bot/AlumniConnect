package Alumni.Project.controller;

import Alumni.Project.dto.AlumniRequest;
import Alumni.Project.dto.update.AlumniUpdateRequest;
import Alumni.Project.dto.response.AlumniResponse;
import Alumni.Project.service.AlumniService;
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
@RequestMapping("/api/alumni")
@RequiredArgsConstructor
@Tag(
        name = "Alumni",
        description = "Endpoints for creating, viewing, updating and deleting alumni profiles"
)
public class AlumniController {

    private final AlumniService alumniService;


    // Create Alumni

    @Operation(
            summary = "Create an alumni profile",
            description = "Creates a new alumni profile. This operation is restricted to administrators."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Alumni profile created successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid alumni data"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. Administrator privileges are required."
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Alumni information already exists"
            )
    })
    @PostMapping
    public ResponseEntity<AlumniResponse> createAlumni(
            @Valid @RequestBody AlumniRequest request
    ) {

        AlumniResponse response =
                alumniService.createAlumni(request);

        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }


    // Get all Alumni

    @Operation(
            summary = "Get all alumni",
            description = "Returns a list of all registered alumni profiles."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Alumni retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            )
    })
    @GetMapping
    public ResponseEntity<List<AlumniResponse>>
    getAllAlumni() {

        return ResponseEntity.ok(
                alumniService.getAllAlumni()
        );
    }


    // Get Alumni using database ID

    @Operation(
            summary = "Get alumni by database ID",
            description = "Retrieves an alumni profile using its database ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Alumni retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Alumni with the specified ID was not found"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<AlumniResponse>
    getAlumniById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                alumniService.getAlumniById(id)
        );
    }


    // Get Alumni using alumni ID

    @Operation(
            summary = "Get alumni by alumni ID",
            description = "Retrieves an alumni profile using the unique alumni ID assigned to the alumnus."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Alumni retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Alumni with the specified alumni ID was not found"
            )
    })
    @GetMapping("/alumni-id/{alumniId}")
    public ResponseEntity<AlumniResponse>
    getAlumniByAlumniId(
            @PathVariable String alumniId
    ) {

        return ResponseEntity.ok(
                alumniService
                        .getAlumniByAlumniId(alumniId)
        );
    }


    // Get Alumni using matriculation number

    @Operation(
            summary = "Get alumni by matriculation number",
            description = "Retrieves an alumni profile using the alumnus's matriculation number."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Alumni retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Alumni with the specified matriculation number was not found"
            )
    })
    @GetMapping("/matric-number/{matricNumber}")
    public ResponseEntity<AlumniResponse>
    getAlumniByMatricNumber(
            @PathVariable String matricNumber
    ) {

        return ResponseEntity.ok(
                alumniService
                        .getAlumniByMatricNumber(
                                matricNumber
                        )
        );
    }


    // Update Alumni

    @Operation(
            summary = "Update an alumni profile",
            description = "Updates the profile information of an alumnus. Administrators can update any alumni profile, while an authenticated alumnus can update their own profile."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Alumni profile updated successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid update data"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. The user is not allowed to update this profile."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Alumni with the specified ID was not found"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<AlumniResponse>
    updateAlumni(
            @PathVariable Long id,

            @RequestBody
                    AlumniUpdateRequest request
    ) {

        return ResponseEntity.ok(
                alumniService.updateAlumni(
                        id,
                        request
                )
        );
    }


    // Delete Alumni

    @Operation(
            summary = "Delete an alumni profile",
            description = "Deletes an alumni profile. This operation is restricted to administrators."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Alumni profile deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. Administrator privileges are required."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Alumni with the specified ID was not found"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    deleteAlumni(
            @PathVariable Long id
    ) {

        alumniService.deleteAlumni(id);

        return ResponseEntity.ok(
                "Alumni deleted successfully"
        );
    }

    @PostMapping("/{alumniId}/skills/{skillId}")
    public ResponseEntity<AlumniResponse> addSkillToAlumni(
            @PathVariable Long alumniId,
            @PathVariable Long skillId
    ) {

        return ResponseEntity.ok(
                alumniService.addSkillToAlumni(
                        alumniId,
                        skillId
                )
        );
    }


    @DeleteMapping("/{alumniId}/skills/{skillId}")
    public ResponseEntity<AlumniResponse> removeSkillFromAlumni(
            @PathVariable Long alumniId,
            @PathVariable Long skillId
    ) {

        return ResponseEntity.ok(
                alumniService.removeSkillFromAlumni(
                        alumniId,
                        skillId
                )
        );
    }
}