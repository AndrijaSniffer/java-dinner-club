package com.ates.dinnerClub.classes.dto.analytics;

import com.ates.dinnerClub.classes.enums.EventStatus;

import java.util.Date;

public record UpcomingEventsWithLowAttendance(
        int id,
        EventStatus status,
        String location,
        Date date,
        long eventAttendance
) {}
