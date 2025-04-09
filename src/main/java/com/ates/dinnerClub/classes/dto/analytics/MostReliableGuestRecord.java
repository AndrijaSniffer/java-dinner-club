package com.ates.dinnerClub.classes.dto.analytics;

public record MostReliableGuestRecord(
        int id,
        String firstName,
        String lastName,
        long attendedEvents
) {}
