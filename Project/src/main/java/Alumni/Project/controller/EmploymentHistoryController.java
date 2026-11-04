
package Alumni.Project.controller;

import Alumni.Project.dto.EmploymentHistoryRequest;
import Alumni.Project.dto.response.EmploymentHistoryResponse;
import Alumni.Project.service.EmploymentHistoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employment-history")
@RequiredArgsConstructor
@Tag(
        name = "Employment History",
        description = "Manage alumni employment records"
)
public class EmploymentHistoryController {

    private final EmploymentHistoryService employmentService;

    @Operation(summary = "Add employment history")
    @PostMapping
    public ResponseEntity<EmploymentHistoryResponse> create(
            @RequestBody EmploymentHistoryRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employmentService.createEmploymentHistory(request));
    }

    @Operation(summary = "Get employment history by alumni ID")
    @GetMapping("/alumni/{alumniId}")
    public ResponseEntity<List<EmploymentHistoryResponse>> getByAlumni(
            @PathVariable Long alumniId
    ) {
        return ResponseEntity.ok(
                employmentService.getEmploymentHistoryByAlumniId(alumniId)
        );
    }

    @Operation(summary = "Get employment history by record ID")
    @GetMapping("/{id}")
    public ResponseEntity<EmploymentHistoryResponse> getById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                employmentService.getEmploymentHistoryById(id)
        );
    }

    @Operation(summary = "Update employment history")
    @PutMapping("/{id}")
    public ResponseEntity<EmploymentHistoryResponse> update(
            @PathVariable Long id,
            @RequestBody EmploymentHistoryRequest request
    ) {
        return ResponseEntity.ok(
                employmentService.updateEmploymentHistory(id, request)
        );
    }

    @Operation(summary = "Delete employment history")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employmentService.deleteEmploymentHistory(id);
        return ResponseEntity.noContent().build();
    }
}
