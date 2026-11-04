package Alumni.Project.controller;

import Alumni.Project.dto.AnnouncementRequest;
import Alumni.Project.dto.response.AnnouncementResponse;
import Alumni.Project.dto.update.AnnouncementUpdateRequest;
import Alumni.Project.service.AnnouncementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        "/api/announcements"
)
@RequiredArgsConstructor
@Tag(
        name = "Announcements",
        description = "Endpoints for managing university announcements"
)
public class AnnouncementController {

    private final AnnouncementService
            announcementService;


    // Create Announcement

    @Operation(
            summary = "Create an announcement",
            description = "Creates a new university announcement. This operation is restricted to administrators."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Announcement created successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid announcement data"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. Administrator privileges are required."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Administrator specified in the request was not found"
            )
    })
    @PostMapping
    public ResponseEntity
            <AnnouncementResponse>
    createAnnouncement(

            @RequestBody
                    AnnouncementRequest request
    ) {

        AnnouncementResponse response =
                announcementService
                        .createAnnouncement(
                                request
                        );


        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }


    // Get all Announcements

    @Operation(
            summary = "Get all announcements",
            description = "Returns all announcements registered in the system."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Announcements retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            )
    })
    @GetMapping
    public ResponseEntity
            <List<AnnouncementResponse>>
    getAllAnnouncements() {

        return ResponseEntity.ok(
                announcementService
                        .getAllAnnouncements()
        );
    }


    // Get active Announcements

    @Operation(
            summary = "Get active announcements",
            description = "Returns only announcements that are currently marked as active."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Active announcements retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            )
    })
    @GetMapping("/active")
    public ResponseEntity
            <List<AnnouncementResponse>>
    getActiveAnnouncements() {

        return ResponseEntity.ok(
                announcementService
                        .getActiveAnnouncements()
        );
    }


    // Get Announcement by ID

    @Operation(
            summary = "Get announcement by ID",
            description = "Retrieves an announcement using its database ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Announcement retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Announcement with the specified ID was not found"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity
            <AnnouncementResponse>
    getAnnouncementById(

            @Parameter(
                    description = "Database ID of the announcement",
                    example = "1",
                    required = true
            )
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                announcementService
                        .getAnnouncementById(
                                id
                        )
        );
    }


    // Get Announcements created by Admin

    @Operation(
            summary = "Get announcements created by an administrator",
            description = "Returns all announcements created by a specific administrator."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Announcements retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            )
    })
    @GetMapping(
            "/admin/{adminId}"
    )
    public ResponseEntity
            <List<AnnouncementResponse>>
    getAnnouncementsByAdmin(

            @Parameter(
                    description = "Database ID of the administrator",
                    example = "4",
                    required = true
            )
            @PathVariable
                    Long adminId
    ) {

        return ResponseEntity.ok(
                announcementService
                        .getAnnouncementsByAdmin(
                                adminId
                        )
        );
    }


    // Update Announcement

    @Operation(
            summary = "Update an announcement",
            description = "Updates an existing announcement. This operation is restricted to administrators."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Announcement updated successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid announcement data"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. Administrator privileges are required."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Announcement with the specified ID was not found"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity
            <AnnouncementResponse>
    updateAnnouncement(

            @Parameter(
                    description = "Database ID of the announcement",
                    example = "1",
                    required = true
            )
            @PathVariable Long id,

            @RequestBody
                    AnnouncementUpdateRequest request
    ) {

        return ResponseEntity.ok(
                announcementService
                        .updateAnnouncement(
                                id,
                                request
                        )
        );
    }


    // Delete Announcement

    @Operation(
            summary = "Delete an announcement",
            description = "Deletes an existing announcement. This operation is restricted to administrators."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Announcement deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. Administrator privileges are required."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Announcement with the specified ID was not found"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    deleteAnnouncement(

            @Parameter(
                    description = "Database ID of the announcement",
                    example = "1",
                    required = true
            )
            @PathVariable Long id
    ) {

        announcementService
                .deleteAnnouncement(
                        id
                );


        return ResponseEntity.ok(
                "Announcement deleted successfully"
        );
    }
}