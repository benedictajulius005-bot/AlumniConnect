package Alumni.Project.service.impl;

import Alumni.Project.dto.AlumniRequest;
import Alumni.Project.dto.update.AlumniUpdateRequest;
import Alumni.Project.dto.response.AlumniResponse;
import Alumni.Project.entity.Alumni;
import Alumni.Project.entity.Department;
import Alumni.Project.entity.Skill;
import Alumni.Project.enums.Role;
import Alumni.Project.exception.DuplicateResourceException;
import Alumni.Project.exception.ResourceNotFoundException;
import Alumni.Project.repository.AlumniRepository;
import Alumni.Project.repository.DepartmentRepository;
import Alumni.Project.repository.SkillRepository;
import Alumni.Project.repository.UserRepository;
import Alumni.Project.service.AlumniService;
import Alumni.Project.service.AlumniIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlumniServiceImpl implements AlumniService {

    private final AlumniRepository alumniRepository;

    private final UserRepository userRepository;

    private final DepartmentRepository departmentRepository;

    private final SkillRepository skillRepository;

    private final PasswordEncoder passwordEncoder;

    private final AlumniIdGenerator alumniIdGenerator;

    @Override
    @Transactional
    public AlumniResponse createAlumni(AlumniRequest request) {

        // Check whether email already exists
        if (userRepository.existsByEmail(request.getEmail())) {

            throw new DuplicateResourceException(
                    "A user with this email already exists"
            );
        }



        // Check whether matriculation number already exists
        if (alumniRepository.existsByMatricNumber(
                request.getMatricNumber()
        )) {

            throw new DuplicateResourceException(
                    "An alumni with this matric number already exists"
            );
        }

        // Find department
        Department department =
                departmentRepository
                        .findById(
                                request.getDepartmentId()
                        )
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Department not found"
                                        )
                        );

        // Find selected skills
        List<Skill> skills =
                findSkillsByIds(
                        request.getSkillIds()
                );

        // Create alumni
        Alumni alumni =
                new Alumni();

        // Set inherited User information
        alumni.setFirstName(
                request.getFirstName()
        );

        alumni.setLastName(
                request.getLastName()
        );

        alumni.setEmail(
                request.getEmail()
        );

        // Store only the BCrypt password hash.
        alumni.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        // Every alumni created through this service has ALUMNI role.
        alumni.setRole(
                Role.ALUMNI
        );

        // Set Alumni information
        alumni.setAlumniId(
                alumniIdGenerator.generateAlumniId()
        );

        alumni.setMatricNumber(
                request.getMatricNumber()
        );

        alumni.setPhoneNumber(
                request.getPhoneNumber()
        );

        alumni.setGraduationYear(
                request.getGraduationYear()
        );

        alumni.setDegree(
                request.getDegree()
        );

        alumni.setCurrentCompany(
                request.getCurrentCompany()
        );

        alumni.setCurrentPosition(
                request.getCurrentPosition()
        );

        alumni.setAddress(
                request.getAddress()
        );

        alumni.setDateOfBirth(
                request.getDateOfBirth()
        );

        alumni.setGender(
                request.getGender()
        );

        alumni.setProfilePicture(
                request.getProfilePicture()
        );

        alumni.setEmployed(
                request.getEmployed() != null
                        ? request.getEmployed()
                        : false
        );

        alumni.setDepartment(
                department
        );

        alumni.setSkills(
                skills
        );

        // Save Alumni
        Alumni savedAlumni =
                alumniRepository.save(
                        alumni
                );

        return convertToResponse(
                savedAlumni
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlumniResponse> getAllAlumni() {

        return alumniRepository
                .findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AlumniResponse getAlumniById(
            Long id
    ) {

        Alumni alumni =
                alumniRepository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Alumni not found with ID: "
                                                        + id
                                        )
                        );

        // Only the profile owner or an administrator can view full details.
        checkUpdatePermission(alumni);

        return convertToResponse(
                alumni
        );
    }

    @Override
    @Transactional(readOnly = true)
    public AlumniResponse getAlumniByAlumniId(
            String alumniId
    ) {

        Alumni alumni =
                alumniRepository
                        .findByAlumniId(
                                alumniId
                        )
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Alumni not found with alumni ID: "
                                                        + alumniId
                                        )
                        );

        return convertToResponse(
                alumni
        );
    }

    @Override
    @Transactional(readOnly = true)
    public AlumniResponse getAlumniByMatricNumber(
            String matricNumber
    ) {

        Alumni alumni =
                alumniRepository
                        .findByMatricNumber(
                                matricNumber
                        )
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Alumni not found with matric number: "
                                                        + matricNumber
                                        )
                        );

        return convertToResponse(
                alumni
        );
    }

    @Override
    @Transactional
    public AlumniResponse updateAlumni(
            Long id,
            AlumniUpdateRequest request
    ) {

        Alumni alumni =
                alumniRepository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Alumni not found with ID: "
                                                        + id
                                        )
                        );

        // Check whether the current user is allowed
        // to update this alumni.
        checkUpdatePermission(alumni);

        // Keep existing department unless a new one is supplied
        Department department = alumni.getDepartment();

        if (request.getDepartmentId() != null) {
            department = departmentRepository
                    .findById(request.getDepartmentId())
                    .orElseThrow(
                            () -> new ResourceNotFoundException(
                                    "Department not found"
                            )
                    );
        }

        // Keep existing skills unless a new skill list is supplied
        List<Skill> skills = alumni.getSkills();

        if (request.getSkillIds() != null) {
            skills = findSkillsByIds(
                    request.getSkillIds()
            );
        }

        // Update User information
        if (request.getFirstName() != null) {
            alumni.setFirstName(
                    request.getFirstName()
            );
        }

        if (request.getLastName() != null) {
            alumni.setLastName(
                    request.getLastName()
            );
        }

        // Update Alumni information
        if (request.getMatricNumber() != null) {

            String matricNumber = request.getMatricNumber().trim();

            if (matricNumber.isEmpty()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Matriculation number cannot be empty"
                );
            }

            boolean numberChanged =
                    !matricNumber.equals(alumni.getMatricNumber());

            if (numberChanged &&
                    alumniRepository.existsByMatricNumber(matricNumber)) {

                throw new DuplicateResourceException(
                        "This matriculation number is already registered"
                );
            }

            alumni.setMatricNumber(matricNumber);
        }
        if (request.getPhoneNumber() != null) {
            alumni.setPhoneNumber(request.getPhoneNumber());
        }

        if (request.getGraduationYear() != null) {
            alumni.setGraduationYear(request.getGraduationYear());
        }

        if (request.getDegree() != null) {
            alumni.setDegree(request.getDegree());
        }

        if (request.getAddress() != null) {
            alumni.setAddress(request.getAddress());
        }

        if (request.getDateOfBirth() != null) {
            alumni.setDateOfBirth(request.getDateOfBirth());
        }

        if (request.getGender() != null) {
            alumni.setGender(request.getGender());
        }

        if (request.getProfilePicture() != null) {
            alumni.setProfilePicture(request.getProfilePicture());
        }

        // Update employment status only when supplied
        if (request.getEmployed() != null) {
            alumni.setEmployed(request.getEmployed());

            // Clear employment details when marked unemployed
            if (!request.getEmployed()) {
                alumni.setCurrentCompany(null);
                alumni.setCurrentPosition(null);
            }
        }

// Update company and position only when employed
        if (Boolean.TRUE.equals(alumni.getEmployed())) {

            if (request.getCurrentCompany() != null) {
                alumni.setCurrentCompany(request.getCurrentCompany());
            }

            if (request.getCurrentPosition() != null) {
                alumni.setCurrentPosition(request.getCurrentPosition());
            }
        }

        alumni.setDepartment(
                department
        );

        alumni.setSkills(
                skills
        );

        Alumni updatedAlumni =
                alumniRepository.save(
                        alumni
                );

        return convertToResponse(
                updatedAlumni
        );
    }

    @Override
    @Transactional
    public void deleteAlumni(
            Long id
    ) {

        Alumni alumni =
                alumniRepository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Alumni not found with ID: "
                                                        + id
                                        )
                        );

        alumniRepository.delete(
                alumni
        );
    }

    /**
     * Checks whether the currently authenticated user
     * is allowed to update the selected alumni.
     *
     * ADMIN:
     * Can update any alumni.
     *
     * ALUMNI:
     * Can update only their own profile.
     */

    @Override
    @Transactional
    public AlumniResponse addSkillToAlumni(
            Long alumniId,
            Long skillId
    ) {

        Alumni alumni = alumniRepository
                .findById(alumniId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Alumni not found with ID: " + alumniId
                        )
                );

        // Make sure the logged-in alumni owns this profile
        checkUpdatePermission(alumni);

        Skill skill = skillRepository
                .findById(skillId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Skill not found with ID: " + skillId
                        )
                );

        // Prevent duplicate skills
        boolean alreadyAdded = alumni.getSkills()
                .stream()
                .anyMatch(existingSkill ->
                        existingSkill.getId().equals(skillId)
                );

        if (!alreadyAdded) {
            alumni.getSkills().add(skill);
        }

        Alumni savedAlumni =
                alumniRepository.save(alumni);

        return convertToResponse(savedAlumni);
    }


    @Override
    @Transactional
    public AlumniResponse removeSkillFromAlumni(
            Long alumniId,
            Long skillId
    ) {

        Alumni alumni = alumniRepository
                .findById(alumniId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Alumni not found with ID: " + alumniId
                        )
                );

        // Make sure the logged-in alumni owns this profile
        checkUpdatePermission(alumni);

        alumni.getSkills().removeIf(
                skill -> skill.getId().equals(skillId)
        );

        Alumni savedAlumni =
                alumniRepository.save(alumni);

        return convertToResponse(savedAlumni);
    }

    private void checkUpdatePermission(
            Alumni alumni
    ) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()) {

            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Authentication is required"
            );
        }

        String currentUserEmail =
                authentication.getName();

        // Check whether the current user is an ADMIN.
        boolean isAdmin =
                authentication
                        .getAuthorities()
                        .stream()
                        .anyMatch(
                                authority ->
                                        authority
                                                .getAuthority()
                                                .equals("ROLE_ADMIN")
                        );

        // ADMIN can update any alumni.
        if (isAdmin) {
            return;
        }

        // ALUMNI can update only their own profile.
        boolean isOwner =
                alumni.getEmail() != null
                        && alumni.getEmail()
                        .equalsIgnoreCase(
                                currentUserEmail
                        );

        if (!isOwner) {

            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You are not allowed to access this alumni profile"
            );
        }

        // Only ALUMNI users should reach this point.
        boolean isAlumni =
                authentication
                        .getAuthorities()
                        .stream()
                        .anyMatch(
                                authority ->
                                        authority
                                                .getAuthority()
                                                .equals("ROLE_ALUMNI")
                        );

        if (!isAlumni) {

            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Only an alumni or administrator can update this profile"
            );
    }
}

    private List<Skill> findSkillsByIds(
            List<Long> skillIds
    ) {

        if (skillIds == null
                || skillIds.isEmpty()) {

            return new ArrayList<>();
        }

        List<Skill> skills =
                skillRepository.findAllById(
                        skillIds
                );

        if (skills.size()
                != skillIds.size()) {

            throw new ResourceNotFoundException(
                    "One or more selected skills were not found"
            );
        }

        return skills;
    }

    private AlumniResponse convertToResponse(
            Alumni alumni
    ) {

        AlumniResponse response =
                new AlumniResponse();

        // User information
        response.setId(
                alumni.getId()
        );

        response.setFirstName(
                alumni.getFirstName()
        );

        response.setLastName(
                alumni.getLastName()
        );

        response.setEmail(
                alumni.getEmail()
        );

        response.setRole(
                alumni.getRole()
        );

        // Alumni information
        response.setAlumniId(
                alumni.getAlumniId()
        );

        response.setMatricNumber(
                alumni.getMatricNumber()
        );

        response.setPhoneNumber(
                alumni.getPhoneNumber()
        );

        response.setGraduationYear(
                alumni.getGraduationYear()
        );

        response.setDegree(
                alumni.getDegree()
        );

        response.setCurrentCompany(
                alumni.getCurrentCompany()
        );

        response.setCurrentPosition(
                alumni.getCurrentPosition()
        );

        response.setAddress(
                alumni.getAddress()
        );

        response.setDateOfBirth(
                alumni.getDateOfBirth()
        );

        response.setGender(
                alumni.getGender()
        );

        response.setProfilePicture(
                alumni.getProfilePicture()
        );

        response.setEmployed(
                alumni.getEmployed()
        );

        // Department
        if (alumni.getDepartment() != null) {

            response.setDepartmentId(
                    alumni.getDepartment()
                            .getId()
            );

            response.setDepartmentName(
                    alumni.getDepartment()
                            .getDepartmentName()
            );
        }

        // Skills
        if (alumni.getSkills() != null) {

            List<String> skillNames =
                    alumni.getSkills()
                            .stream()
                            .map(Skill::getSkillName)
                            .toList();

            response.setSkills(
                    skillNames
            );
        }

        return response;
    }
}