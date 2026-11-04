package Alumni.Project.service.impl;

import Alumni.Project.dto.EventRequest;
import Alumni.Project.dto.response.EventResponse;
import Alumni.Project.dto.update.EventUpdateRequest;
import Alumni.Project.entity.Admin;
import Alumni.Project.entity.Alumni;
import Alumni.Project.entity.Event;
import Alumni.Project.exception.ResourceNotFoundException;
import Alumni.Project.repository.AdminRepository;
import Alumni.Project.repository.AlumniRepository;
import Alumni.Project.repository.EventRepository;
import Alumni.Project.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final AdminRepository adminRepository;

    private final AlumniRepository alumniRepository;


    // =========================================================
    // CREATE EVENT
    // =========================================================

    @Override
    public EventResponse createEvent(
            EventRequest request
    ) {

        Admin admin =
                adminRepository
                        .findById(
                                request.getAdminId()
                        )
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Admin not found with ID: "
                                                        + request.getAdminId()
                                        )
                        );

        if (request.getEventDate() == null ||
                request.getEventDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "Event date must be today or a future date"
            );
        }

        if (request.getMaxParticipants() == null
                || request.getMaxParticipants() <= 0) {

            throw new IllegalArgumentException(
                    "Maximum participants must be greater than zero"
            );
        }


        Event event =
                new Event();


        event.setTitle(request.getTitle().trim());

        event.setDescription(
                request.getDescription()
        );

        event.setLocation(request.getLocation().trim());

        event.setEventDate(
                request.getEventDate()
        );

        event.setMaxParticipants(
                request.getMaxParticipants()
        );

        event.setActive(
                request.getActive() != null
                        ? request.getActive()
                        : true
        );

        event.setCreatedBy(
                admin
        );


        Event savedEvent =
                eventRepository.save(
                        event
                );


        return convertToResponse(
                savedEvent
        );
    }


    // =========================================================
    // GET ALL EVENTS
    // =========================================================

    @Override
    @Transactional(readOnly = true)
    public List<EventResponse> getAllEvents() {

        return eventRepository
                .findAllByOrderByEventDateAsc()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }


    // =========================================================
    // GET ACTIVE EVENTS
    // =========================================================

    @Override
    @Transactional(readOnly = true)
    public List<EventResponse> getActiveEvents() {

        return eventRepository
                .findByActiveTrueOrderByEventDateAsc()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }


    // =========================================================
    // GET EVENT BY ID
    // =========================================================

    @Override
    @Transactional(readOnly = true)
    public EventResponse getEventById(
            Long eventId
    ) {

        Event event =
                findEventById(
                        eventId
                );


        return convertToResponse(
                event
        );
    }


    // =========================================================
    // GET EVENTS BY ADMIN
    // =========================================================

    @Override
    @Transactional(readOnly = true)
    public List<EventResponse> getEventsByAdmin(
            Long adminId
    ) {

        if (!adminRepository.existsById(
                adminId
        )) {

            throw new ResourceNotFoundException(
                    "Admin not found with ID: "
                            + adminId
            );
        }


        return eventRepository
                .findByCreatedById(
                        adminId
                )
                .stream()
                .map(this::convertToResponse)
                .toList();
    }


    // =========================================================
    // UPDATE EVENT
    // =========================================================

    @Override
    public EventResponse updateEvent(
            Long eventId,
            EventUpdateRequest request
    ) {

        Event event =
                findEventById(
                        eventId
                );

        if (request.getEventDate() == null ||
                request.getEventDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "Event date must be today or a future date"
            );
        }
        if (request.getMaxParticipants() == null
                || request.getMaxParticipants() <= 0) {

            throw new IllegalArgumentException(
                    "Maximum participants must be greater than zero"
            );
        }


        if (request.getMaxParticipants()
                < event.getParticipants().size()) {

            throw new IllegalArgumentException(
                    "Maximum participants cannot be less than the number of registered participants"
            );
        }


        event.setTitle(request.getTitle().trim());

        event.setDescription(
                request.getDescription()
        );

        event.setLocation(request.getLocation().trim());

        event.setEventDate(
                request.getEventDate()
        );

        event.setMaxParticipants(
                request.getMaxParticipants()
        );

        event.setActive(
                request.getActive()
        );


        Event updatedEvent =
                eventRepository.save(
                        event
                );


        return convertToResponse(
                updatedEvent
        );
    }


    // =========================================================
    // REGISTER ALUMNI
    // =========================================================

    @Override
    public EventResponse registerAlumni(
            Long eventId,
            Long alumniId
    ) {

        Event event =
                findEventById(
                        eventId
                );


        Alumni alumni =
                alumniRepository
                        .findById(
                                alumniId
                        )
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Alumni not found with ID: "
                                                        + alumniId
                                        )
                        );


        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();


        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication.getName() == null) {

            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Authentication is required"
            );
        }


        String loggedInEmail =
                authentication.getName();


        if (!loggedInEmail.equalsIgnoreCase(
                alumni.getEmail()
        )) {

            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You can only register yourself for an event"
            );
        }


        if (!Boolean.TRUE.equals(
                event.getActive()
        )) {

            throw new IllegalArgumentException(
                    "Registration is closed because this event is inactive"
            );
        }


        if (event.getEventDate()
                .isBefore(LocalDate.now())) {

            throw new IllegalArgumentException(
                    "Registration is closed because this event has already taken place"
            );
        }


        boolean alreadyRegistered =
                event.getParticipants()
                        .stream()
                        .anyMatch(
                                participant ->
                                        participant.getId()
                                                .equals(alumniId)
                        );


        if (alreadyRegistered) {

            throw new IllegalArgumentException(
                    "Alumni is already registered for this event"
            );
        }


        if (event.getParticipants().size()
                >= event.getMaxParticipants()) {

            throw new IllegalArgumentException(
                    "Registration failed because the event is full"
            );
        }


        event.getParticipants()
                .add(alumni);


        Event updatedEvent =
                eventRepository.save(
                        event
                );


        return convertToResponse(
                updatedEvent
        );
    }


    // =========================================================
    // REMOVE ALUMNI FROM EVENT
    // =========================================================

    @Override
    public EventResponse removeAlumni(
            Long eventId,
            Long alumniId
    ) {

        Event event =
                findEventById(
                        eventId
                );


        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();


        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication.getName() == null) {

            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Authentication is required"
            );
        }


        String loggedInEmail =
                authentication.getName();


        // =====================================================
        // ADMIN CAN REMOVE ANY ALUMNI
        // =====================================================

        boolean isAdmin =
                authentication.getAuthorities()
                        .stream()
                        .anyMatch(
                                authority ->
                                        authority.getAuthority()
                                                .equals("ROLE_ADMIN")
                        );


        // =====================================================
        // ALUMNI CAN ONLY REMOVE THEMSELVES
        // =====================================================

        if (!isAdmin) {

            Alumni alumni =
                    alumniRepository
                            .findById(
                                    alumniId
                            )
                            .orElseThrow(
                                    () ->
                                            new ResourceNotFoundException(
                                                    "Alumni not found with ID: "
                                                            + alumniId
                                            )
                            );


            if (!loggedInEmail.equalsIgnoreCase(
                    alumni.getEmail()
            )) {

                throw new ResponseStatusException(
                        HttpStatus.FORBIDDEN,
                        "You can only remove yourself from an event"
                );
            }
        }


        // =====================================================
        // REMOVE PARTICIPANT
        // =====================================================

        boolean removed =
                event.getParticipants()
                        .removeIf(
                                participant ->
                                        participant.getId()
                                                .equals(alumniId)
                        );


        if (!removed) {

            throw new ResourceNotFoundException(
                    "This alumni is not registered for the event"
            );
        }


        Event updatedEvent =
                eventRepository.save(
                        event
                );


        return convertToResponse(
                updatedEvent
        );
    }


    // =========================================================
    // DELETE EVENT
    // =========================================================

    @Override
    public void deleteEvent(
            Long eventId
    ) {

        Event event =
                findEventById(
                        eventId
                );


        eventRepository.delete(
                event
        );
    }


    // =========================================================
    // FIND EVENT
    // =========================================================

    private Event findEventById(
            Long eventId
    ) {

        return eventRepository
                .findById(
                        eventId
                )
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException(
                                        "Event not found with ID: "
                                                + eventId
                                )
                );
    }


    // =========================================================
    // CONVERT TO RESPONSE
    // =========================================================

    private EventResponse convertToResponse(
            Event event
    ) {

        EventResponse response =
                new EventResponse();


        response.setEventId(
                event.getEventId()
        );

        response.setTitle(
                event.getTitle()
        );

        response.setDescription(
                event.getDescription()
        );

        response.setLocation(
                event.getLocation()
        );

        response.setEventDate(
                event.getEventDate()
        );

        response.setMaxParticipants(
                event.getMaxParticipants()
        );

        response.setActive(
                event.getActive()
        );


        if (event.getCreatedBy() != null) {

            response.setAdminId(
                    event.getCreatedBy()
                            .getId()
            );

            response.setAdminCode(
                    event.getCreatedBy()
                            .getAdminId()
            );

            response.setAdminName(
                    event.getCreatedBy()
                            .getFirstName()
                            + " "
                            + event.getCreatedBy()
                            .getLastName()
            );
        }


        if (event.getParticipants() != null) {

            response.setNumberOfParticipants(
                    event.getParticipants()
                            .size()
            );


            List<Long> participantIds =
                    event.getParticipants()
                            .stream()
                            .map(Alumni::getId)
                            .toList();


            response.setParticipantIds(
                    participantIds
            );


            List<String> participantNames =
                    event.getParticipants()
                            .stream()
                            .map(
                                    alumni ->
                                            alumni.getFirstName()
                                                    + " "
                                                    + alumni.getLastName()
                            )
                            .toList();


            response.setParticipantNames(
                    participantNames
            );
        }


        return response;
    }
}