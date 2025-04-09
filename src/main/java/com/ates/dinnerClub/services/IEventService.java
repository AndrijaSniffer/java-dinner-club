package com.ates.dinnerClub.services;

import com.ates.dinnerClub.classes.dto.event.CreateEventDTO;
import com.ates.dinnerClub.classes.dto.event.EventDTO;
import com.ates.dinnerClub.classes.dto.event.UpdateEventDTO;
import com.ates.dinnerClub.classes.dto.guest.GuestInvitationDTO;
import com.ates.dinnerClub.classes.enums.EventStatus;
import com.ates.dinnerClub.entities.Event;

import java.util.List;

public interface IEventService {
    List<EventDTO> getAllEvents();

    EventDTO getEventById(int id);

    Event getEventByIdForCreation(int id);

    List<EventDTO> getEventsByStatus(EventStatus status);

    List<GuestInvitationDTO> getGuestsByEventId(int id);

    List<GuestInvitationDTO> getGuestsWithoutAttendanceByEventId(int id);

    EventDTO createEvent(CreateEventDTO event);

    EventDTO updateEvent(UpdateEventDTO event);

    void deleteEvent(int id);
}
