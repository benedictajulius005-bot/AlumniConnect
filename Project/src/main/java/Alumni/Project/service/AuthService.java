package Alumni.Project.service;

import Alumni.Project.dto.LoginRequest;
import Alumni.Project.dto.response.LoginResponse;
import Alumni.Project.dto.RegisterRequest;
import Alumni.Project.dto.response.UserResponse;

public interface AuthService {

    UserResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}