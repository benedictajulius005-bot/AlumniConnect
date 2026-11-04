package Alumni.Project.service;

import Alumni.Project.dto.DepartmentRequest;
import Alumni.Project.dto.response.DepartmentResponse;
import Alumni.Project.dto.update.DepartmentUpdateRequest;

import java.util.List;

public interface DepartmentService {

    DepartmentResponse createDepartment(
            DepartmentRequest request
    );

    List<DepartmentResponse> getAllDepartments();

    DepartmentResponse getDepartmentById(
            Long id
    );

    DepartmentResponse getDepartmentByName(
            String departmentName
    );

    DepartmentResponse updateDepartment(
            Long id,
            DepartmentUpdateRequest request
    );

    void deleteDepartment(
            Long id
    );
}