package Alumni.Project.repository;

import Alumni.Project.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    boolean existsByAdminId(String adminId);

    Optional<Admin> findByAdminId(String adminId);

    Optional<Admin> findByEmail(String email);
}