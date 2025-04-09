package com.ates.dinnerClub.classes.dto.analytics;

public record FrequentNoShowOffendersRecord(
        int id,
        String firstName,
        String lastName,
        long acceptedEvents,
        long unattendedEvents
) {
}
