
package Alumni.Project.service.impl;

import Alumni.Project.dto.EmploymentHistoryRequest;
import Alumni.Project.dto.response.EmploymentHistoryResponse;
import Alumni.Project.entity.Alumni;
import Alumni.Project.entity.EmploymentHistory;
import Alumni.Project.repository.AlumniRepository;
import Alumni.Project.repository.EmploymentHistoryRepository;
import Alumni.Project.service.EmploymentHistoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmploymentHistoryServiceImpl
        implements EmploymentHistoryService {

    private final EmploymentHistoryRepository employmentRepository;
    private final AlumniRepository alumniRepository;

    private void checkAccess(Alumni alumni) {
        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()
                || "anonymousUser".equals(auth.getPrincipal())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Authentication required"
            );
        }

        boolean admin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (admin) return;

        // In this project, Alumni inherits User.id.
        // Verify that the authenticated user's database ID
        // is resolved by the application's UserRepository.
        String email = auth.getName();

        if (alumni.getEmail() == null
                || !alumni.getEmail().equalsIgnoreCase(email)) {
            throw new AccessDeniedException(
                    "You can only access your own employment records"
            );
        }
    }

    private Alumni findAlumni(Long alumniId) {
        return alumniRepository.findById(alumniId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Alumni not found"
                ));
    }

    private EmploymentHistory findRecord(Long id) {
        return employmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Employment history record not found"
                ));
    }

    private void validate(EmploymentHistoryRequest request) {
        if (request == null
                || request.getCompanyName() == null
                || request.getCompanyName().isBlank()
                || request.getJobPosition() == null
                || request.getJobPosition().isBlank()
                || request.getStartDate() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Company, position and start date are required"
            );
        }

        LocalDate end = request.getEndDate();

        if (end != null && end.isBefore(request.getStartDate())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "End date cannot be before start date"
            );
        }
    }

    private EmploymentHistoryResponse mapResponse(
            EmploymentHistory history
    ) {
        EmploymentHistoryResponse response =
                new EmploymentHistoryResponse();

        response.setId(history.getId());
        response.setAlumniId(history.getAlumni().getId());
        response.setCompanyName(history.getCompanyName());
        response.setJobPosition(history.getJobPosition());
        response.setStartDate(history.getStartDate());
        response.setEndDate(history.getEndDate());

        return response;
    }

    @Override
    public EmploymentHistoryResponse createEmploymentHistory(
            EmploymentHistoryRequest request
    ) {
        validate(request);

        if (request.getAlumniId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Alumni ID is required"
            );
        }

        Alumni alumni = findAlumni(request.getAlumniId());
        checkAccess(alumni);

        EmploymentHistory history = new EmploymentHistory();
        history.setAlumni(alumni);
        history.setCompanyName(request.getCompanyName().trim());
        history.setJobPosition(request.getJobPosition().trim());
        history.setStartDate(request.getStartDate());
        history.setEndDate(request.getEndDate());

        return mapResponse(employmentRepository.save(history));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmploymentHistoryResponse> getEmploymentHistoryByAlumniId(
            Long alumniId
    ) {
        Alumni alumni = findAlumni(alumniId);
        checkAccess(alumni);

        return employmentRepository.findByAlumni_Id(alumniId)
                .stream()
                .map(this::mapResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EmploymentHistoryResponse getEmploymentHistoryById(Long id) {
        EmploymentHistory history = findRecord(id);
        checkAccess(history.getAlumni());

        return mapResponse(history);
    }

    @Override
    public EmploymentHistoryResponse updateEmploymentHistory(
            Long id,
            EmploymentHistoryRequest request
    ) {
        validate(request);

        EmploymentHistory history = findRecord(id);
        checkAccess(history.getAlumni());

        if (request.getAlumniId() != null
                && !request.getAlumniId().equals(
                history.getAlumni().getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Cannot change the owner of an employment record"
            );
        }

        history.setCompanyName(request.getCompanyName().trim());
        history.setJobPosition(request.getJobPosition().trim());
        history.setStartDate(request.getStartDate());
        history.setEndDate(request.getEndDate());

        return mapResponse(employmentRepository.save(history));
    }

    @Override
    public void deleteEmploymentHistory(Long id) {
        EmploymentHistory history = findRecord(id);
        checkAccess(history.getAlumni());

        employmentRepository.delete(history);
    }
}
