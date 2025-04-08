package com.ates.dinnerClub.services.implementations;

import com.ates.dinnerClub.classes.dto.event.CreateEventDTO;
import com.ates.dinnerClub.classes.dto.event.EventDTO;
import com.ates.dinnerClub.classes.dto.event.UpdateEventDTO;
import com.ates.dinnerClub.classes.dto.guest.GuestDTO;
import com.ates.dinnerClub.classes.enums.EventStatus;
import com.ates.dinnerClub.entities.Event;
import com.ates.dinnerClub.repositories.IEventRepo;
import com.ates.dinnerClub.services.IEventService;
import com.ates.dinnerClub.services.IThemeService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<GuestDTO> getGuestsByEventId(int id) {
        return this.eventRepository.findAllGuestsByEventId(id).stream().map(GuestDTO::new).toList();
    }

    @Override
    public EventDTO createEvent(CreateEventDTO event) {
        if (event == null || (event.getLocation() == null || event.getLocation().isEmpty()
                || event.getStatus() == null || event.getThemeId() <= 0)) {
            throw new IllegalArgumentException("Event data is invalid");
        } else {
            Event eventEntity = new Event();
            eventEntity.setLocation(event.getLocation());
            eventEntity.setStatus(event.getStatus());
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
            Event eventEntity = this.eventRepository.findById(event.getId()).orElseThrow();
            eventEntity.setLocation(event.getLocation());
            eventEntity.setStatus(event.getStatus());
            eventEntity.setDate(event.getDate());
            eventEntity.setTheme(this.themeService.getThemeById(event.getTheme()));
            return new EventDTO(this.eventRepository.save(eventEntity));
        }
    }

    @Override
    public void deleteEvent(int id) {
        if (id > 0) {
            this.eventRepository.deleteById(id);
        }
    }
}
