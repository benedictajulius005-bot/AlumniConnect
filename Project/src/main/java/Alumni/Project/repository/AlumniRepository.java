package Alumni.Project.repository;

import Alumni.Project.entity.Alumni;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlumniRepository extends JpaRepository<Alumni, Long> {

    boolean existsByAlumniId(String alumniId);

    boolean existsByMatricNumber(String matricNumber);

    Optional<Alumni> findByAlumniId(String alumniId);

    Optional<Alumni> findByMatricNumber(String matricNumber);

    Optional<Alumni> findByEmail(String email);
}