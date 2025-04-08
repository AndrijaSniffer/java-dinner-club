package com.ates.dinnerClub.controllers;

import com.ates.dinnerClub.classes.dto.event.CreateEventDTO;
import com.ates.dinnerClub.classes.dto.event.EventDTO;
import com.ates.dinnerClub.classes.dto.event.UpdateEventDTO;
import com.ates.dinnerClub.classes.dto.guest.GuestDTO;
import com.ates.dinnerClub.classes.enums.EventStatus;
import com.ates.dinnerClub.services.IEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("events")
public class EventController {
    private final IEventService eventService;

    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping()
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        return ResponseEntity.ok(this.eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable int id) {
        return ResponseEntity.ok(this.eventService.getEventById(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<EventDTO>> getEventsByStatus(@PathVariable EventStatus status) {
        return ResponseEntity.ok(this.eventService.getEventsByStatus(status));
    }

    @GetMapping("/{id}/guests")
    public ResponseEntity<List<GuestDTO>> getGuestsByEventId(@PathVariable int id) {
        return ResponseEntity.ok(this.eventService.getGuestsByEventId(id));
    }

    @PostMapping()
    public ResponseEntity<EventDTO> createEvent(@RequestBody CreateEventDTO event) {
        event.setStatus(EventStatus.UPCOMING);

        LocalDate eventLocalDate = event.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (eventLocalDate.isBefore(LocalDate.now().plusDays(2))) {
            throw new IllegalArgumentException("Event can't be created less than 2 days in advance");
        }
        return ResponseEntity.ok(this.eventService.createEvent(event));
    }

    @PutMapping()
    public ResponseEntity<EventDTO> updateEvent(@RequestBody UpdateEventDTO event) {
        return ResponseEntity.ok(this.eventService.updateEvent(event));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable int id) {
        this.eventService.deleteEvent(id);

        return ResponseEntity.noContent().build();
    }
}
