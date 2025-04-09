package com.ates.dinnerClub.services;

import com.ates.dinnerClub.classes.dto.analytics.FrequentNoShowOffendersRecord;
import com.ates.dinnerClub.classes.dto.analytics.MostReliableGuestRecord;
import com.ates.dinnerClub.classes.dto.analytics.ThemeLoyaltyRecord;
import com.ates.dinnerClub.classes.dto.analytics.UpcomingEventsWithLowAttendance;

import java.util.List;

public interface IAnalyticalService {
    List<MostReliableGuestRecord> getTop5MostReliableGuests();

    List<FrequentNoShowOffendersRecord> getFrequentNoShowOffenders();

    List<UpcomingEventsWithLowAttendance> getUpcomingEventsWithLowAttendance();

    List<ThemeLoyaltyRecord> themeLoyalty();
}
