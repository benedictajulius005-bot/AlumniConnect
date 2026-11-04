package Alumni.Project.service.impl;

import Alumni.Project.dto.LoginRequest;
import Alumni.Project.dto.response.LoginResponse;
import Alumni.Project.dto.RegisterRequest;
import Alumni.Project.dto.response.UserResponse;
import Alumni.Project.entity.User;
import Alumni.Project.entity.Alumni;
import Alumni.Project.enums.Role;
import Alumni.Project.repository.AlumniRepository;
import org.springframework.transaction.annotation.Transactional;
import Alumni.Project.exception.DuplicateResourceException;
import Alumni.Project.exception.UnauthorizedException;
import Alumni.Project.mapper.UserMapper;
import Alumni.Project.repository.UserRepository;
import Alumni.Project.security.JwtService;
import Alumni.Project.service.AuthService;
import Alumni.Project.service.AlumniIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AlumniRepository alumniRepository;
    private final AlumniIdGenerator alumniIdGenerator;

    @Override
    @Transactional
    public UserResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException(
                    "A user with this email already exists"
            );
        }

        Alumni alumni = new Alumni();

        alumni.setFirstName(request.getFirstName());
        alumni.setLastName(request.getLastName());
        alumni.setEmail(request.getEmail());

        alumni.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        alumni.setRole(Role.ALUMNI);
        alumni.setEmployed(false);

// Automatically assign a unique Alumni ID
        alumni.setAlumniId(alumniIdGenerator.generateAlumniId());

        Alumni savedAlumni = alumniRepository.save(alumni);

        return UserMapper.toResponse(savedAlumni);
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new UnauthorizedException("Invalid email or password")
                );

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {
            throw new UnauthorizedException("Invalid email or password");
        }

        String token = jwtService.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        return new LoginResponse(
                token,
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole()
        );
    }
}