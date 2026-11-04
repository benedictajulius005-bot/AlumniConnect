package Alumni.Project.repository;

import Alumni.Project.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRepository
        extends JpaRepository<Announcement, Long> {

    List<Announcement> findByActiveTrue();

    List<Announcement> findByAdminId(
            Long adminId
    );

    List<Announcement>
    findAllByOrderByPostedDateDesc();

    List<Announcement>
    findByActiveTrueOrderByPostedDateDesc();
}