package Alumni.Project.controller;

import Alumni.Project.dto.AdminRequest;
import jakarta.validation.Valid;
import Alumni.Project.dto.update.AdminUpdateRequest;
import Alumni.Project.dto.response.AdminResponse;
import Alumni.Project.service.AdminService;
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
@RequestMapping("/api/admins")
@RequiredArgsConstructor
@Tag(
        name = "Administrators",
        description = "Endpoints for managing administrator accounts"
)
public class AdminController {

    private final AdminService adminService;


    // Create Admin

    @Operation(
            summary = "Create an administrator",
            description = "Creates a new administrator account. This operation is restricted to administrators."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Administrator created successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid administrator data"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. Administrator privileges are required."
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "An administrator with the same identifier or email already exists"
            )
    })
    @PostMapping
    public ResponseEntity<AdminResponse>
    createAdmin(
            @Valid @RequestBody AdminRequest request
    ) {

        AdminResponse response =
                adminService.createAdmin(request);

        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }


    // Get all Admins

    @Operation(
            summary = "Get all administrators",
            description = "Returns a list of all administrator accounts in the system."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Administrators retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. Administrator privileges are required."
            )
    })
    @GetMapping
    public ResponseEntity<List<AdminResponse>>
    getAllAdmins() {

        return ResponseEntity.ok(
                adminService.getAllAdmins()
        );
    }


    // Get Admin by database ID

    @Operation(
            summary = "Get administrator by ID",
            description = "Retrieves an administrator using the database ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Administrator retrieved successfully"
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
                    description = "Administrator with the specified ID was not found"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<AdminResponse>
    getAdminById(

            @Parameter(
                    description = "Database ID of the administrator",
                    example = "4",
                    required = true
            )
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                adminService.getAdminById(id)
        );
    }


    // Get Admin by Admin ID

    @Operation(
            summary = "Get administrator by admin ID",
            description = "Retrieves an administrator using the administrator's unique admin ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Administrator retrieved successfully"
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
                    description = "Administrator with the specified admin ID was not found"
            )
    })
    @GetMapping("/admin-id/{adminId}")
    public ResponseEntity<AdminResponse>
    getAdminByAdminId(

            @Parameter(
                    description = "Unique administrator identifier",
                    example = "ADMIN001",
                    required = true
            )
            @PathVariable String adminId
    ) {

        return ResponseEntity.ok(
                adminService
                        .getAdminByAdminId(
                                adminId
                        )
        );
    }


    // Update Admin

    @Operation(
            summary = "Update an administrator",
            description = "Updates an existing administrator account. This operation is restricted to administrators."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Administrator updated successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid administrator data"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. Administrator privileges are required."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Administrator with the specified ID was not found"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "An administrator with the same identifier or email already exists"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<AdminResponse>
    updateAdmin(

            @Parameter(
                    description = "Database ID of the administrator",
                    example = "4",
                    required = true
            )
            @PathVariable Long id,

            @RequestBody
                    AdminUpdateRequest request
    ) {

        return ResponseEntity.ok(
                adminService.updateAdmin(
                        id,
                        request
                )
        );
    }


    // Delete Admin

    @Operation(
            summary = "Delete an administrator",
            description = "Deletes an existing administrator account. This operation is restricted to administrators."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Administrator deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. Administrator privileges are required."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Administrator with the specified ID was not found"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    deleteAdmin(

            @Parameter(
                    description = "Database ID of the administrator",
                    example = "4",
                    required = true
            )
            @PathVariable Long id
    ) {

        adminService.deleteAdmin(id);

        return ResponseEntity.ok(
                "Admin deleted successfully"
        );
    }
}