package Alumni.Project.service.impl;

import Alumni.Project.dto.AdminRequest;
import Alumni.Project.dto.update.AdminUpdateRequest;
import Alumni.Project.dto.response.AdminResponse;
import Alumni.Project.entity.Admin;
import Alumni.Project.enums.Role;
import Alumni.Project.exception.DuplicateResourceException;
import Alumni.Project.exception.ResourceNotFoundException;
import Alumni.Project.repository.AdminRepository;
import Alumni.Project.repository.UserRepository;
import Alumni.Project.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public AdminResponse createAdmin(
            AdminRequest request
    ) {

        // Check whether email already exists
        if (userRepository.existsByEmail(
                request.getEmail()
        )) {

            throw new DuplicateResourceException(
                    "A user with this email already exists"
            );
        }

        // Check whether admin ID already exists
        if (adminRepository.existsByAdminId(
                request.getAdminId()
        )) {

            throw new DuplicateResourceException(
                    "An admin with this admin ID already exists"
            );
        }

        // Create Admin object
        Admin admin = new Admin();

        // Set inherited User fields
        admin.setFirstName(
                request.getFirstName()
        );

        admin.setLastName(
                request.getLastName()
        );

        admin.setEmail(
                request.getEmail()
        );

        // Validate password length
        if (request.getPassword() == null ||
                request.getPassword().length() < 12) {
            throw new IllegalArgumentException(
                    "Password must contain at least 12 characters"
            );
        }
        // Store only the BCrypt password hash.
        admin.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        // Every admin created through this service has ADMIN role.
        admin.setRole(
                Role.ADMIN
        );

        // Set Admin fields
        admin.setAdminId(
                request.getAdminId()
        );

        admin.setPosition(
                request.getPosition()
        );

        // Save Admin
        Admin savedAdmin =
                adminRepository.save(admin);

        return convertToResponse(
                savedAdmin
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdminResponse> getAllAdmins() {

        return adminRepository
                .findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AdminResponse getAdminById(
            Long id
    ) {

        Admin admin = adminRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Admin not found with ID: "
                                        + id
                        )
                );

        return convertToResponse(admin);
    }

    @Override
    @Transactional(readOnly = true)
    public AdminResponse getAdminByAdminId(
            String adminId
    ) {

        Admin admin = adminRepository
                .findByAdminId(adminId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Admin not found with admin ID: "
                                        + adminId
                        )
                );

        return convertToResponse(admin);
    }

    @Override
    @Transactional
    public AdminResponse updateAdmin(
            Long id,
            AdminUpdateRequest request
    ) {

        Admin admin = adminRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Admin not found with ID: "
                                        + id
                        )
                );

        // Update inherited User fields
        admin.setFirstName(
                request.getFirstName()
        );

        admin.setLastName(
                request.getLastName()
        );

        // Update Admin field
        admin.setPosition(
                request.getPosition()
        );

        Admin updatedAdmin =
                adminRepository.save(admin);

        return convertToResponse(
                updatedAdmin
        );
    }

    @Override
    @Transactional
    public void deleteAdmin(
            Long id
    ) {

        Admin admin = adminRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Admin not found with ID: "
                                        + id
                        )
                );

        adminRepository.delete(admin);
    }

    private AdminResponse convertToResponse(
            Admin admin
    ) {

        AdminResponse response =
                new AdminResponse();

        response.setId(
                admin.getId()
        );

        response.setFirstName(
                admin.getFirstName()
        );

        response.setLastName(
                admin.getLastName()
        );

        response.setEmail(
                admin.getEmail()
        );

        response.setRole(
                admin.getRole()
        );

        response.setAdminId(
                admin.getAdminId()
        );

        response.setPosition(
                admin.getPosition()
        );

        return response;
    }
}