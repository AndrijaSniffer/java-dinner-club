package com.ates.dinnerClub.services;

import com.ates.dinnerClub.classes.dto.event.CreateEventDTO;
import com.ates.dinnerClub.classes.dto.event.EventDTO;
import com.ates.dinnerClub.classes.dto.event.UpdateEventDTO;
import com.ates.dinnerClub.classes.dto.guest.GuestInvitationRecord;
import com.ates.dinnerClub.classes.enums.EventStatus;
import com.ates.dinnerClub.entities.Event;

import java.time.OffsetDateTime;
import java.util.List;

public interface IEventService {
    List<EventDTO> getAllEvents();

    EventDTO getEventById(long id);

    Event getEventByIdForCreation(long id);

    List<EventDTO> getEventsByStatus(EventStatus status);

    List<GuestInvitationRecord> getGuestsByEventId(long id);

    List<GuestInvitationRecord> getGuestsWithoutAttendanceByEventId(long id);

    List<EventDTO> getUpcomingEventsInTimeWindow(OffsetDateTime start, OffsetDateTime end);

    EventDTO createEvent(CreateEventDTO event);

    EventDTO updateEvent(UpdateEventDTO event);

    void deleteEvent(long id);
}
