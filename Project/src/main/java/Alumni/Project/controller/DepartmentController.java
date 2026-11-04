package Alumni.Project.controller;

import Alumni.Project.dto.DepartmentRequest;
import Alumni.Project.dto.response.DepartmentResponse;
import Alumni.Project.dto.update.DepartmentUpdateRequest;
import Alumni.Project.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
@Tag(
        name = "Departments",
        description = "Endpoints for managing university departments"
)
public class DepartmentController {

    private final DepartmentService
            departmentService;


    // Create Department

    @Operation(
            summary = "Create a department",
            description = "Creates a new university department. This operation is restricted to administrators."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Department created successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid department data"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. Administrator privileges are required."
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "A department with the same name already exists"
            )
    })
    @PostMapping
    public ResponseEntity<DepartmentResponse>
    createDepartment(
            @RequestBody
                    DepartmentRequest request
    ) {

        DepartmentResponse response =
                departmentService
                        .createDepartment(
                                request
                        );


        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }


    // Get all Departments

    @Operation(
            summary = "Get all departments",
            description = "Returns a list of all university departments."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Departments retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            )
    })
    @GetMapping
    public ResponseEntity
            <List<DepartmentResponse>>
    getAllDepartments() {

        return ResponseEntity.ok(
                departmentService
                        .getAllDepartments()
        );
    }


    // Get Department by ID

    @Operation(
            summary = "Get department by ID",
            description = "Retrieves a department using its database ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Department retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Department with the specified ID was not found"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse>
    getDepartmentById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                departmentService
                        .getDepartmentById(
                                id
                        )
        );
    }


    // Get Department by name

    @Operation(
            summary = "Get department by name",
            description = "Retrieves a department using its department name."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Department retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Department with the specified name was not found"
            )
    })
    @GetMapping("/name/{departmentName}")
    public ResponseEntity<DepartmentResponse>
    getDepartmentByName(
            @PathVariable
                    String departmentName
    ) {

        return ResponseEntity.ok(
                departmentService
                        .getDepartmentByName(
                                departmentName
                        )
        );
    }


    // Update Department

    @Operation(
            summary = "Update a department",
            description = "Updates an existing university department. This operation is restricted to administrators."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Department updated successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid department data"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. Administrator privileges are required."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Department with the specified ID was not found"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "A department with the same name already exists"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse>
    updateDepartment(
            @PathVariable Long id,

            @RequestBody
                    DepartmentUpdateRequest request
    ) {

        return ResponseEntity.ok(
                departmentService
                        .updateDepartment(
                                id,
                                request
                        )
        );
    }


    // Delete Department

    @Operation(
            summary = "Delete a department",
            description = "Deletes an existing university department. This operation is restricted to administrators."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Department deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. Administrator privileges are required."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Department with the specified ID was not found"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    deleteDepartment(
            @PathVariable Long id
    ) {

        departmentService
                .deleteDepartment(
                        id
                );


        return ResponseEntity.ok(
                "Department deleted successfully"
        );
    }
}