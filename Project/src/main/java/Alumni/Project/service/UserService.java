package Alumni.Project.service;

import Alumni.Project.dto.update.ChangePasswordRequest;
import Alumni.Project.dto.UserRequest;
import Alumni.Project.dto.response.UserResponse;
import Alumni.Project.enums.Role;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest request);

    UserResponse getCurrentUser();

    void changePassword(ChangePasswordRequest request);

    UserResponse getUserById(Long id);

    List<UserResponse> getAllUsers();

    List<UserResponse> getUsersByRole(Role role);

    UserResponse updateUser(Long id, UserRequest request);

    void deleteUser(Long id);
}