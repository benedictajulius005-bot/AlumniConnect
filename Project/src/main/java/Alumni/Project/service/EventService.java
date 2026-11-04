package Alumni.Project.service;

import Alumni.Project.dto.EventRequest;
import Alumni.Project.dto.response.EventResponse;
import Alumni.Project.dto.update.EventUpdateRequest;

import java.util.List;

public interface EventService {

    EventResponse createEvent(
            EventRequest request
    );

    List<EventResponse> getAllEvents();

    List<EventResponse> getActiveEvents();

    EventResponse getEventById(
            Long eventId
    );

    List<EventResponse> getEventsByAdmin(
            Long adminId
    );

    EventResponse updateEvent(
            Long eventId,
            EventUpdateRequest request
    );

    EventResponse registerAlumni(
            Long eventId,
            Long alumniId
    );

    EventResponse removeAlumni(
            Long eventId,
            Long alumniId
    );

    void deleteEvent(
            Long eventId
    );
}