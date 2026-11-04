package Alumni.Project.service.impl;

import Alumni.Project.dto.DepartmentRequest;
import Alumni.Project.dto.response.DepartmentResponse;
import Alumni.Project.dto.update.DepartmentUpdateRequest;
import Alumni.Project.entity.Department;
import Alumni.Project.exception.DuplicateResourceException;
import Alumni.Project.exception.ResourceNotFoundException;
import Alumni.Project.repository.DepartmentRepository;
import Alumni.Project.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl
        implements DepartmentService {

    private final DepartmentRepository departmentRepository;


    @Override
    public DepartmentResponse createDepartment(
            DepartmentRequest request
    ) {

        if (departmentRepository
                .existsByDepartmentNameIgnoreCase(
                        request.getDepartmentName()
                )) {

            throw new DuplicateResourceException(
                    "Department already exists"
            );
        }


        Department department =
                new Department();

        department.setDepartmentName(
                request.getDepartmentName()
        );

        department.setFaculty(
                request.getFaculty()
        );


        Department savedDepartment =
                departmentRepository.save(
                        department
                );


        return convertToResponse(
                savedDepartment
        );
    }


    @Override
    public List<DepartmentResponse>
    getAllDepartments() {

        return departmentRepository
                .findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }


    @Override
    public DepartmentResponse
    getDepartmentById(
            Long id
    ) {

        Department department =
                departmentRepository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Department not found with ID: "
                                                        + id
                                        )
                        );


        return convertToResponse(
                department
        );
    }


    @Override
    public DepartmentResponse
    getDepartmentByName(
            String departmentName
    ) {

        Department department =
                departmentRepository
                        .findByDepartmentNameIgnoreCase(
                                departmentName
                        )
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Department not found with name: "
                                                        + departmentName
                                        )
                        );


        return convertToResponse(
                department
        );
    }


    @Override
    public DepartmentResponse
    updateDepartment(
            Long id,
            DepartmentUpdateRequest request
    ) {

        Department department =
                departmentRepository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Department not found with ID: "
                                                        + id
                                        )
                        );


        boolean departmentNameChanged =
                !department
                        .getDepartmentName()
                        .equalsIgnoreCase(
                                request.getDepartmentName()
                        );


        if (departmentNameChanged
                && departmentRepository
                .existsByDepartmentNameIgnoreCase(
                        request.getDepartmentName()
                )) {

            throw new DuplicateResourceException(
                    "Another department with this name already exists"
            );
        }


        department.setDepartmentName(
                request.getDepartmentName()
        );

        department.setFaculty(
                request.getFaculty()
        );


        Department updatedDepartment =
                departmentRepository.save(
                        department
                );


        return convertToResponse(
                updatedDepartment
        );
    }


    @Override
    public void deleteDepartment(
            Long id
    ) {

        Department department =
                departmentRepository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Department not found with ID: "
                                                        + id
                                        )
                        );


        departmentRepository.delete(
                department
        );
    }


    private DepartmentResponse
    convertToResponse(
            Department department
    ) {

        DepartmentResponse response =
                new DepartmentResponse();


        response.setId(
                department.getId()
        );

        response.setDepartmentName(
                department.getDepartmentName()
        );

        response.setFaculty(
                department.getFaculty()
        );


        return response;
    }
}