package Alumni.Project.controller;

import Alumni.Project.dto.response.JobRecommendationResponse;
import Alumni.Project.service.RecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
@Tag(
        name = "Job Recommendations",
        description = "Endpoints for recommending job opportunities to alumni based on their skills"
)
public class RecommendationController {

    private final RecommendationService recommendationService;


    // Get job recommendations for an alumni

    @Operation(
            summary = "Get job recommendations for an alumnus",
            description = """
                    Returns job opportunities recommended for a specific alumnus based on skill matching.
                    
                    The recommendation score is calculated as:
                    
                    (Number of matching required job skills / Total number of required job skills) × 100
                    
                    Skills are compared after trimming whitespace and ignoring letter case.
                    Recommendations are returned when the calculated score meets the configured recommendation threshold.
                    """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Job recommendations retrieved successfully"
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
    @GetMapping("/alumni/{alumniId}")
    public ResponseEntity<List<JobRecommendationResponse>>
    getRecommendationsForAlumni(

            @Parameter(
                    description = "Database ID of the alumnus",
                    example = "1",
                    required = true
            )
            @PathVariable Long alumniId
    ) {

        return ResponseEntity.ok(
                recommendationService
                        .getRecommendationsForAlumni(alumniId)
        );
    }
}