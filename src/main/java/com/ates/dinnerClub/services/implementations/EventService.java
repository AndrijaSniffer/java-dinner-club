package com.ates.dinnerClub.services.implementations;

import com.ates.dinnerClub.classes.dto.event.CreateEventDTO;
import com.ates.dinnerClub.classes.dto.event.EventDTO;
import com.ates.dinnerClub.classes.dto.event.UpdateEventDTO;
import com.ates.dinnerClub.classes.dto.guest.GuestInvitationDTO;
import com.ates.dinnerClub.classes.enums.EventStatus;
import com.ates.dinnerClub.entities.Event;
import com.ates.dinnerClub.entities.Guest;
import com.ates.dinnerClub.repositories.IEventRepo;
import com.ates.dinnerClub.services.IEventService;
import com.ates.dinnerClub.services.IThemeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class EventService implements IEventService {
    private final IEventRepo eventRepository;
    private final IThemeService themeService;

    public EventService(IEventRepo eventRepository, ThemeService themeService) {
        this.eventRepository = eventRepository;
        this.themeService = themeService;
    }

    @Override
    public List<EventDTO> getAllEvents() {
        return this.eventRepository.findAll().stream().map(EventDTO::new).toList();
    }

    @Override
    public EventDTO getEventById(int id) {
        if (id > 0) {
            return this.eventRepository.findById(id).map(EventDTO::new).orElseThrow();
        }
        return null;
    }

    @Override
    public Event getEventByIdForCreation(int id) {
        if (id > 0) {
            return this.eventRepository.findById(id).orElseThrow();
        }
        return null;
    }

    @Override
    public List<EventDTO> getEventsByStatus(EventStatus status) {
        return this.eventRepository.findAllByStatus(status).stream().map(EventDTO::new).toList();
    }

    @Override
    public List<GuestInvitationDTO> getGuestsByEventId(int id) {
        return this.eventRepository.findAllGuestsByEventId(id).stream().map(GuestInvitationDTO::new).toList();
    }

    @Override
    public List<GuestInvitationDTO> getGuestsWithoutAttendanceByEventId(int id) {
        return this.eventRepository.findAllGuestsWithoutAttendanceByEventId(id).stream().map(GuestInvitationDTO::new).toList();
    }

    @Override
    public EventDTO createEvent(CreateEventDTO event) {
        if (event == null || (event.getLocation() == null || event.getLocation().isEmpty() || event.getThemeId() <= 0)) {
            throw new IllegalArgumentException("Event data is invalid");
        } else {
            Event eventEntity = new Event();
            eventEntity.setLocation(event.getLocation());
            eventEntity.setStatus(EventStatus.UPCOMING);
            eventEntity.setDate(event.getDate());
            eventEntity.setTheme(this.themeService.getThemeById(event.getThemeId()));
            return new EventDTO(this.eventRepository.save(eventEntity));
        }
    }

    @Override
    public EventDTO updateEvent(UpdateEventDTO event) {
        if (event == null || (event.getId() <= 0 || event.getLocation() == null || event.getLocation().isEmpty()
                || event.getStatus() == null || event.getTheme() <= 0)) {
            throw new IllegalArgumentException("Event data is invalid");
        } else {
            boolean isCanceledOrUpdated = false;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
            String oldLocation = null;
            Date oldDate = null;

            Event eventEntity = this.eventRepository.findById(event.getId()).orElseThrow();

            if (event.getStatus().equals(EventStatus.CANCELED) && eventEntity.getStatus().equals(EventStatus.UPCOMING)) {
                eventEntity.setStatus(event.getStatus());
                eventEntity.setTheme(this.themeService.getThemeById(event.getTheme()));

                isCanceledOrUpdated = true;

            } else if (event.getStatus().equals(eventEntity.getStatus()) && eventEntity.getStatus().equals(EventStatus.UPCOMING)
                    && !event.compareToEntity(eventEntity)) {
                oldLocation = eventEntity.getLocation();
                oldDate = eventEntity.getDate();

                eventEntity.setLocation(event.getLocation());
                eventEntity.setDate(event.getDate());
                eventEntity.setTheme(this.themeService.getThemeById(event.getTheme()));

            } else if (event.getStatus().equals(EventStatus.COMPLETED) && eventEntity.getStatus().equals(EventStatus.UPCOMING)) {
                eventEntity.setStatus(event.getStatus());
                eventEntity.setTheme(this.themeService.getThemeById(event.getTheme()));

            } else {
                throw new IllegalArgumentException("Event status and information cannot be changed if the status is already COMPLETED or CANCELED." +
                        " Or the update object is the same as in the database");
            }

            // Save the updated event before informing the guests.
            EventDTO updatedEvent = new EventDTO(this.eventRepository.save(eventEntity));

            List<Guest> guestsThatAccepted = this.eventRepository.findAllGuestsThatAcceptedInvitationByEventId(eventEntity.getId());
            if (isCanceledOrUpdated) {
                // The code below assures that the guests that accepted the invitation to the event will be notified via an email of its cancellation.

                for (Guest guest : guestsThatAccepted) {
                    log.warn("""
                                    \nSubject: Event cancellation notification
                                    
                                    Good day {} {},
                                    
                                    Unfortunately, the event at {} on {} (which you accepted to attend) has been canceled.
                                    We apologize for any inconvenience this may have caused.
                                    
                                    If you have questions or concerns, please contact us.
                                    
                                    Best regards,
                                    Dinner Club Team
                                    """,
                            guest.getFirstName(),
                            guest.getLastName(),
                            eventEntity.getLocation(),
                            eventEntity.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(formatter)
                    );
                }
            } else {
                // The code below assures that the guests that accepted the invitation to the event will be notified via an email of the changes.

                for (Guest guest : guestsThatAccepted) {
                    String locationLine, dateLine;

                    assert oldLocation != null;
                    if (!oldLocation.equals(eventEntity.getLocation())) {
                        locationLine = String.format("The location has been changed from %s to %s.",
                                oldLocation, eventEntity.getLocation());
                    } else {
                        locationLine = String.format("The location has not been changed (%s).", eventEntity.getLocation());
                    }

                    assert oldDate != null;
                    if (oldDate.compareTo(eventEntity.getDate()) != 0) {
                        dateLine = String.format("The date has been changed from %s to %s.",
                                oldDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(formatter),
                                eventEntity.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(formatter));
                    } else {
                        dateLine = String.format("The date has not been changed (%s).",
                                eventEntity.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(formatter));
                    }

                    log.warn("""
                                    \nSubject: Event update notification
                                    
                                    Good day {} {},
                                    
                                    One of the events you will be attending has been updated.
                                    {}
                                    {}
                                    Please check the updated details.
                                    
                                    If you have questions or concerns, please contact us.
                                    
                                    Best regards,
                                    Dinner Club Team
                                    """,
                            guest.getFirstName(),
                            guest.getLastName(),
                            locationLine,
                            dateLine
                    );
                }
            }
            return updatedEvent;
        }
    }

    @Override
    public void deleteEvent(int id) {
        if (id > 0) {
            this.eventRepository.deleteById(id);
        }
    }
}
