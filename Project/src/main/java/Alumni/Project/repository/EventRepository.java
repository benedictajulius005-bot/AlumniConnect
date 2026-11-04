package Alumni.Project.repository;

import Alumni.Project.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository
        extends JpaRepository<Event, Long> {

    List<Event>
    findAllByOrderByEventDateAsc();

    List<Event>
    findByActiveTrueOrderByEventDateAsc();

    List<Event>
    findByCreatedById(
            Long adminId
    );
}