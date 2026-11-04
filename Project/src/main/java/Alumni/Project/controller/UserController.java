package Alumni.Project.controller;

import Alumni.Project.dto.update.ChangePasswordRequest;
import Alumni.Project.dto.UserRequest;
import Alumni.Project.dto.response.UserResponse;
import Alumni.Project.enums.Role;
import jakarta.validation.Valid;
import Alumni.Project.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(
        name = "Users",
        description = "Endpoints for authenticated user self-service and administrator user management"
)
public class UserController {

    private final UserService userService;


    // Get Current User

    @Operation(
            summary = "Get current user",
            description = "Returns the profile information of the currently authenticated user."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Current user retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            )
    })
    @GetMapping("/me")
    public UserResponse getCurrentUser() {
        return userService.getCurrentUser();
    }


    // Change Current User Password

    @Operation(
            summary = "Change current user's password",
            description = "Changes the password of the currently authenticated user. The current password must be provided and verified before the new password is saved."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Password changed successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid password data or current password is incorrect"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            )
    })
    @PutMapping("/me/password")
    public String changePassword(
            @Valid @RequestBody ChangePasswordRequest request
    ){
        userService.changePassword(request);
        return "Password changed successfully";
    }


    // Get User by ID

    @Operation(
            summary = "Get user by ID",
            description = "Retrieves a user using the database ID. This operation is restricted to administrators."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User retrieved successfully"
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
                    description = "User with the specified ID was not found"
            )
    })
    @GetMapping("/{id}")
    public UserResponse getUserById(

            @Parameter(
                    description = "Database ID of the user",
                    example = "10",
                    required = true
            )
            @PathVariable Long id
    ) {
        return userService.getUserById(id);
    }


    // Get All Users

    @Operation(
            summary = "Get all users",
            description = "Returns all users registered in the system. This operation is restricted to administrators."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Users retrieved successfully"
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
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }


    // Get Users by Role

    @Operation(
            summary = "Get users by role",
            description = "Returns users assigned to the specified role. This operation is restricted to administrators."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Users retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid role value"
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
    @GetMapping("/role/{role}")
    public List<UserResponse> getUsersByRole(

            @Parameter(
                    description = "Role assigned to the users",
                    example = "ALUMNI",
                    required = true
            )
            @PathVariable Role role
    ) {
        return userService.getUsersByRole(role);
    }


    // Update User

    @Operation(
            summary = "Update a user",
            description = "Updates an existing user account. This operation is restricted to administrators."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User updated successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid user data"
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
                    description = "User with the specified ID was not found"
            )
    })
    @PutMapping("/{id}")
    public UserResponse updateUser(

            @Parameter(
                    description = "Database ID of the user",
                    example = "10",
                    required = true
            )
            @PathVariable Long id,

            @RequestBody UserRequest request
    ) {
        return userService.updateUser(id, request);
    }


    // Delete User

    @Operation(
            summary = "Delete a user",
            description = "Deletes an existing user account. This operation is restricted to administrators."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User deleted successfully"
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
                    description = "User with the specified ID was not found"
            )
    })
    @DeleteMapping("/{id}")
    public String deleteUser(

            @Parameter(
                    description = "Database ID of the user",
                    example = "10",
                    required = true
            )
            @PathVariable Long id
    ) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }
}