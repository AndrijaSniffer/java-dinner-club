package com.ates.dinnerClub.util;

import com.ates.dinnerClub.classes.dto.event.EventDTO;
import com.ates.dinnerClub.classes.dto.invitation.InvitationDTO;
import com.ates.dinnerClub.services.IEventService;
import com.ates.dinnerClub.services.IInvitationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Component
public class EventReminderScheduler {
    private final IEventService eventService;
    private final IInvitationService invitationService;

    public EventReminderScheduler(IEventService eventService, IInvitationService invitationService) {
        this.eventService = eventService;
        this.invitationService = invitationService;
    }

    @Scheduled(cron = "0 0 * * * *") // Run every hour
    public void sendEventReminders() {
        // 1. Use OffsetDateTime for proper time zone handling

        // Note: We convert java.util.Date to OffsetDateTime here to ensure proper time zone handling.
        // This avoids issues with legacy Date objects and ensures consistency across the application.
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        OffsetDateTime start = now.plusHours(24);
        OffsetDateTime end = start.plusHours(1); // Simplified window calculation

        // 2. Directly use OffsetDateTime in repository calls
        List<EventDTO> events = eventService.getUpcomingEventsInTimeWindow(start, end);

        if (events.isEmpty()) {
            log.warn("No events found in the next 24-25 hour window");
            return; // Early exit
        }

        // 3. Use proper date formatting with time zone
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("dd.MM.yyyy 'at' HH:mm ")
                .withZone(ZoneId.of(ZoneOffset.systemDefault().toString()));

        // 4. Process invitations
        for (EventDTO event : events) {
            // Convert Date to OffsetDateTime
            OffsetDateTime eventOffsetDateTime = event.getDate().toInstant()
                    .atZone(ZoneId.of("UTC"))
                    .toOffsetDateTime();

            List<InvitationDTO> confirmedInvitations = invitationService.getInvitationsByEventIdAndTimeFrame(event.getId());

            for (InvitationDTO invitation : confirmedInvitations) {
                String guestName = "%s %s".formatted(
                        invitation.getGuest().getFirstName(),
                        invitation.getGuest().getLastName()
                );

                String eventTime = eventOffsetDateTime.format(formatter);

                log.warn("""
                        \nSubject: Event Reminder
                        
                        Hello {},
                        
                        This is a reminder for your event:
                        Location: {}
                        Date: {}
                        
                        Best regards,
                        Dinner Club Team
                        """, guestName, event.getLocation(), eventTime);
            }
        }
    }
}
