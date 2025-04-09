package com.ates.dinnerClub.classes.dto.analytics;

import java.util.Date;

public record UpcomingEventsWithLowAttendanceRecord(
        int id,
        String status,
        String location,
        Date date,
        long eventAttendance
) {}
