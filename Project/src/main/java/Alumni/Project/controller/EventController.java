package Alumni.Project.controller;

import Alumni.Project.dto.EventRequest;
import Alumni.Project.dto.response.EventResponse;
import Alumni.Project.dto.update.EventUpdateRequest;
import Alumni.Project.service.EventService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
@Tag(
        name = "Events",
        description = "Endpoints for managing university events and alumni event registration"
)
public class EventController {

    private final EventService eventService;


    // Create Event

    @Operation(
            summary = "Create an event",
            description = "Creates a new university event. This operation is restricted to administrators."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Event created successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid event data"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. Administrator privileges are required."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Administrator specified in the request was not found"
            )
    })
    @PostMapping
    public ResponseEntity<EventResponse>
    createEvent(

            @Valid @RequestBody
            EventRequest request
    ) {

        EventResponse response =
                eventService.createEvent(
                        request
                );


        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }


    // Get all Events

    @Operation(
            summary = "Get all events",
            description = "Returns all events registered in the system."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Events retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            )
    })
    @GetMapping
    public ResponseEntity
            <List<EventResponse>>
    getAllEvents() {

        return ResponseEntity.ok(
                eventService
                        .getAllEvents()
        );
    }


    // Get active Events

    @Operation(
            summary = "Get active events",
            description = "Returns events that are currently marked as active."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Active events retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            )
    })
    @GetMapping("/active")
    public ResponseEntity
            <List<EventResponse>>
    getActiveEvents() {

        return ResponseEntity.ok(
                eventService
                        .getActiveEvents()
        );
    }


    // Get Event by ID

    @Operation(
            summary = "Get event by ID",
            description = "Retrieves an event using its database ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Event retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Event with the specified ID was not found"
            )
    })
    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponse>
    getEventById(

            @Parameter(
                    description = "Database ID of the event",
                    example = "1",
                    required = true
            )
            @PathVariable
                    Long eventId
    ) {

        return ResponseEntity.ok(
                eventService
                        .getEventById(
                                eventId
                        )
        );
    }


    // Get Events created by an Admin

    @Operation(
            summary = "Get events created by an administrator",
            description = "Returns all events created by a specific administrator."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Events retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            )
    })
    @GetMapping(
            "/admin/{adminId}"
    )
    public ResponseEntity
            <List<EventResponse>>
    getEventsByAdmin(

            @Parameter(
                    description = "Database ID of the administrator",
                    example = "4",
                    required = true
            )
            @PathVariable
                    Long adminId
    ) {

        return ResponseEntity.ok(
                eventService
                        .getEventsByAdmin(
                                adminId
                        )
        );
    }


    // Update Event

    @Operation(
            summary = "Update an event",
            description = "Updates an existing event. This operation is restricted to administrators."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Event updated successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid event data"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. Administrator privileges are required."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Event with the specified ID was not found"
            )
    })
    @PutMapping("/{eventId}")
    public ResponseEntity<EventResponse>
    updateEvent(

            @Parameter(
                    description = "Database ID of the event",
                    example = "1",
                    required = true
            )
            @PathVariable
                    Long eventId,

            @Valid @RequestBody
            EventUpdateRequest request
    ) {

        return ResponseEntity.ok(
                eventService
                        .updateEvent(
                                eventId,
                                request
                        )
        );
    }


    // Register Alumni for Event

    @Operation(
            summary = "Register an alumnus for an event",
            description = "Registers an alumnus as a participant in an event. An authenticated alumnus can register themselves. The event must be active, in the future, and have available capacity."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Alumnus registered for the event successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Registration is not possible, for example because the event is inactive, already started, full, or the alumnus is already registered"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. An alumnus can only register themselves."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Event or alumnus was not found"
            )
    })
    @PostMapping(
            "/{eventId}/register/{alumniId}"
    )
    public ResponseEntity<EventResponse>
    registerAlumni(

            @Parameter(
                    description = "Database ID of the event",
                    example = "1",
                    required = true
            )
            @PathVariable
                    Long eventId,

            @Parameter(
                    description = "Database ID of the alumnus registering for the event",
                    example = "1",
                    required = true
            )
            @PathVariable
                    Long alumniId
    ) {

        return ResponseEntity.ok(
                eventService
                        .registerAlumni(
                                eventId,
                                alumniId
                        )
        );
    }


    // Remove Alumni from Event

    @Operation(
            summary = "Remove an alumnus from an event",
            description = "Removes an alumnus from an event. An authenticated alumnus can remove themselves from an event, while an administrator can remove participants."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Alumnus removed from the event successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication is required"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. An alumnus can only remove themselves."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Event or alumnus was not found, or the alumnus is not registered for the event"
            )
    })
    @DeleteMapping(
            "/{eventId}/participants/{alumniId}"
    )
    public ResponseEntity<EventResponse>
    removeAlumni(

            @Parameter(
                    description = "Database ID of the event",
                    example = "1",
                    required = true
            )
            @PathVariable
                    Long eventId,

            @Parameter(
                    description = "Database ID of the alumnus to remove",
                    example = "1",
                    required = true
            )
            @PathVariable
                    Long alumniId
    ) {

        return ResponseEntity.ok(
                eventService
                        .removeAlumni(
                                eventId,
                                alumniId
                        )
        );
    }


    // Delete Event

    @Operation(
            summary = "Delete an event",
            description = "Deletes an existing event. This operation is restricted to administrators."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Event deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied. Administrator privileges are required."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Event with the specified ID was not found"
            )
    })
    @DeleteMapping("/{eventId}")
    public ResponseEntity<String>
    deleteEvent(

            @Parameter(
                    description = "Database ID of the event",
                    example = "1",
                    required = true
            )
            @PathVariable
                    Long eventId
    ) {

        eventService.deleteEvent(
                eventId
        );


        return ResponseEntity.ok(
                "Event deleted successfully"
        );
    }
}