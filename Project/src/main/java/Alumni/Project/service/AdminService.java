package Alumni.Project.service;

import Alumni.Project.dto.AdminRequest;
import Alumni.Project.dto.update.AdminUpdateRequest;
import Alumni.Project.dto.response.AdminResponse;

import java.util.List;

public interface AdminService {

    AdminResponse createAdmin(AdminRequest request);

    List<AdminResponse> getAllAdmins();

    AdminResponse getAdminById(Long id);

    AdminResponse getAdminByAdminId(String adminId);

    AdminResponse updateAdmin(
            Long id,
            AdminUpdateRequest request
    );

    void deleteAdmin(Long id);
}