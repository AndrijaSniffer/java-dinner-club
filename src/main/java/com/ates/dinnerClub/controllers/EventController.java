package com.ates.dinnerClub.controllers;

import com.ates.dinnerClub.classes.dto.ErrorResponse;
import com.ates.dinnerClub.classes.dto.event.CreateEventDTO;
import com.ates.dinnerClub.classes.dto.event.EventDTO;
import com.ates.dinnerClub.classes.dto.event.UpdateEventDTO;
import com.ates.dinnerClub.classes.dto.guest.GuestInvitationRecord;
import com.ates.dinnerClub.classes.enums.EventStatus;
import com.ates.dinnerClub.services.IEventService;
import com.ates.dinnerClub.services.implementations.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("events")
public class EventController {
    private final IEventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping()
    @Operation(
            summary = "Get all events",
            description = "Returns all existing events."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Events retrieved successfully", content = @Content(schema = @Schema(implementation = EventDTO[].class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        return ResponseEntity.ok(this.eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get all events by id",
            description = "Returns all existing events by id."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Events retrieved successfully", content = @Content(schema = @Schema(implementation = EventDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Event not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<EventDTO> getEventById(@PathVariable @Min(1) long id) {
        return ResponseEntity.ok(this.eventService.getEventById(id));
    }

    @GetMapping("/status/{status}")
    @Operation(
            summary = "Get all events by status",
            description = "Returns all existing events by status."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Events retrieved successfully", content = @Content(schema = @Schema(implementation = EventDTO[].class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<List<EventDTO>> getEventsByStatus(@PathVariable @NotNull @NotBlank EventStatus status) {
        return ResponseEntity.ok(this.eventService.getEventsByStatus(status));
    }

    @GetMapping("/{id}/guests")
    @Operation(
            summary = "Get event by id",
            description = "Returns event by id."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Events retrieved successfully", content = @Content(schema = @Schema(implementation = EventDTO[].class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<List<GuestInvitationRecord>> getGuestsByEventId(@PathVariable @Min(1) long id) {
        return ResponseEntity.ok(this.eventService.getGuestsByEventId(id));
    }

    @GetMapping("/{id}/guests/withoutAttendance")
    @Operation(
            summary = "Get guests without attendance by event id",
            description = "Returns guests without attendance by event id."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Events retrieved successfully", content = @Content(schema = @Schema(implementation = EventDTO[].class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<List<GuestInvitationRecord>> getGuestsWithoutAttendanceByEventId(@PathVariable @Min(1) long id) {
        return ResponseEntity.ok(this.eventService.getGuestsWithoutAttendanceByEventId(id));
    }

    @PostMapping()
    @Operation(
            summary = "Create a new event",
            description = "Creates a new event."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Event created successfully", content = @Content(schema = @Schema(implementation = EventDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<EventDTO> createEvent(@RequestBody @Valid CreateEventDTO event) {
        event.setStatus(EventStatus.UPCOMING);

        LocalDate eventLocalDate = event.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (eventLocalDate.isBefore(LocalDate.now().plusDays(2))) {
            throw new IllegalArgumentException("Event can't be created less than 2 days in advance");
        }
        return ResponseEntity.ok(this.eventService.createEvent(event));
    }

    @PutMapping()
    @Operation(
            summary = "Update an event",
            description = "Updates an existing event."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Event updated successfully", content = @Content(schema = @Schema(implementation = EventDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Event not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<EventDTO> updateEvent(@RequestBody @Valid UpdateEventDTO event) {
        return ResponseEntity.ok(this.eventService.updateEvent(event));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete an event",
            description = "Deletes an existing event."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Always returns"),
    })
    public ResponseEntity<Void> deleteEvent(@PathVariable @Min(1) long id) {
        this.eventService.deleteEvent(id);

        return ResponseEntity.noContent().build();
    }
}
